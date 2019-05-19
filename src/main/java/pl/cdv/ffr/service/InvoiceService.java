package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.*;
import pl.cdv.ffr.repository.InvoiceRepository;
import pl.cdv.ffr.repository.PropertyRepository;
import pl.cdv.ffr.utils.PdfGenaratorUtil;
import pl.cdv.ffr.utils.ftp.FTPHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InvoiceService extends BaseService {
    @Autowired
    PropertyService propertyService;

    @Autowired
    BillService billService;

    @Autowired
    TenatService tenatService;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    private  JwtUserDetailsService userService;

    @Autowired
    PdfGenaratorUtil pdfGenaratorUtil;

    @Autowired
    FTPHelper ftpHelper;

    @Value("${jwt.header}")
    private String tokenHeader;

    public List<Invoice> findAllPropertyInvoices(HttpServletRequest request, String property_ID) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        List<Invoice> invoices = new ArrayList<>();
        if (isRentier(user)) {
            user.getRentier().getProperties().forEach(p -> {
                if (p.getId() == Long.parseLong(property_ID)) {
                    p.getInvoices().forEach(b -> {
                        if (b.isVisible()) {
                            invoices.add(b);
                        }
                    });
                }
            });
        } else {
            Property tenatProperty = user.getTenat().getProperty();
            if (tenatProperty != null && tenatProperty.getId() == Long.parseLong(property_ID)) {
                tenatProperty.getInvoices().forEach(b -> {
                    if (b.isVisible()) {
                        invoices.add(b);
                    }
                });
            }
        }
        return invoices;
    }

    public Invoice findPropertyInvoiceById(HttpServletRequest request, String property_ID, String invoice_ID) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            for (Property property : user.getRentier().getProperties()) {
                if (property.getId() == Long.parseLong(property_ID)) {
                    for (Invoice invoice : property.getInvoices()) {
                        if (invoice.getId() == Long.parseLong(invoice_ID) && invoice.isVisible()) {
                            return invoice;
                        }
                    }
                }
            }
        } else {
            if (user.getTenat().getProperty().getId() == Long.parseLong(property_ID)) {
                for (Invoice invoice : user.getTenat().getProperty().getInvoices()) {
                    if (invoice.getId() == Long.parseLong(invoice_ID) && invoice.isVisible()) {
                        return invoice;
                    }
                }
            }
        }
        throw new UsernameNotFoundException("User " + user.getEmail() + " has no property with id:" + property_ID + " or invoice with id:" + invoice_ID);
    }

    public Invoice createPropertyInvoice(HttpServletRequest request, String property_ID, Invoice invoice) {
        JwtUser u1 = userService.getUserInfo(request, tokenHeader);
        if (isRentier(u1)) {
            invoiceRepository.save(invoice);

            Property property = u1.getRentier().getProperties().stream()
                    .filter(p -> p.getId() == Long.parseLong(property_ID))
                    .findFirst()
                    .get();

            List<Invoice> invoices = property.getInvoices();
            invoices.add(invoice);
            property.setInvoices(invoices);

            propertyRepository.save(property);

            return invoice;
        } else {
            throw new UsernameNotFoundException("User " + u1.getEmail() + " is a tenat. He can't create invoice");
        }
    }

    public Invoice updatePropertyInvoice(HttpServletRequest request, String property_ID, String invoice_ID, Invoice newInvoice) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            Property property = user.getRentier().getProperties().stream()
                    .filter(p -> p.getId() == Long.parseLong(property_ID))
                    .findFirst()
                    .get();

            return  property.getInvoices().stream()
                    .filter(b -> b.getId() == Long.parseLong(invoice_ID))
                    .findFirst()
                    .map(invoice -> {
                        copyNonNullProperties(newInvoice, invoice);
                        return invoiceRepository.save(invoice);
                    })
                    .orElseGet(() -> {
                        newInvoice.setId(Long.parseLong(invoice_ID));
                        return invoiceRepository.save(newInvoice);
                    });
        } else {
            throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't update invoice");
        }
    }

    public void deletePropertyInvoice(HttpServletRequest request, String property_ID, String invoice_ID) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            Invoice invoice = null;
            for (Property property : user.getRentier().getProperties()) {
                if (property.getId() == Long.parseLong(property_ID)) {
                    for (Invoice b : property.getInvoices()) {
                        if (b.getId() == Long.parseLong(invoice_ID)) {
                            invoice = b;
                        }
                    }
                }
            }
            if (invoice != null) {
                invoiceRepository.delete(invoice);
            }
        } else {
            throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't delete invoice");
        }
    }

    public String generateInvoice(HttpServletRequest request, String property_ID, String bill_ID) {
        Bill bill = billService.findPropertyBillById(request, property_ID, bill_ID);
        Property property = propertyService.findPropertyById(request, property_ID);
        Tenat tenat = tenatService.findAllTenats().stream()
                .filter(t -> t.getProperty().getId() == Long.parseLong(property_ID))
                .findFirst()
                .get();
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        Rentier rentier = user.getRentier();
        String propertyPrice = property.getPrice().replace("zł", "");

        BigDecimal coldWater = new BigDecimal(bill.getColdWater().getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal commonPart = new BigDecimal(bill.getCommonPart().getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal electricity = new BigDecimal(bill.getElectricity().getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal heating = new BigDecimal(bill.getHeating().getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal hotWater = new BigDecimal(bill.getHotWater().getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal repairFund = new BigDecimal(bill.getRepairFund().getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal trash = new BigDecimal(bill.getTrash().getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);;

        BigDecimal propertyPriceTaxed = new BigDecimal(propertyPrice).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal propertyPriceTax = propertyPriceTaxed.multiply(new BigDecimal(0.23)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal propertyPriceNonTaxed = propertyPriceTaxed.subtract(propertyPriceTax).setScale(2, BigDecimal.ROUND_HALF_UP);


        BigDecimal mediaSumTaxed = coldWater.add(commonPart).add(electricity).add(heating).add(hotWater).add(repairFund).add(trash).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal mediaTax = mediaSumTaxed.multiply(new BigDecimal(0.23)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal mediaSumNonTaxed = mediaSumTaxed.subtract(mediaTax).setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal sumTax = mediaTax.add(propertyPriceTax).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal sumTaxed = propertyPriceTaxed.add(mediaSumTaxed).setScale(2, BigDecimal.ROUND_HALF_UP);;
        BigDecimal sumNonTaxed = sumTaxed.subtract(sumTax).setScale(2, BigDecimal.ROUND_HALF_UP);;

        String fileUrl = "";
        InputStream is = null;

        Map<String,Object> data = new HashMap<>();
        data.put("rentier", rentier);
        data.put("tenat", tenat);
        data.put("property", property);
        data.put("bill", bill);
        data.put("mediaSumTaxed", mediaSumTaxed.toString());
        data.put("mediaSumNonTaxed", mediaSumNonTaxed.toString());
        data.put("propertySumTaxed", propertyPriceTaxed.toString());
        data.put("propertySumNonTaxed", propertyPriceNonTaxed.toString());
        data.put("sumTax", sumTax.toString());
        data.put("sumTaxed", sumTaxed.toString());
        data.put("sumNonTaxed", sumNonTaxed.toString());
        data.put("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        data.put("dueDate", new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
        try {
            is = pdfGenaratorUtil.createPdf("template", data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fileUrl = ftpHelper.createAndSaveDecodedFile(is, "pdf", "invoice", "invoices");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Invoice invoice = new Invoice();
        invoice.setDate(new Date().toString());
        invoice.setInvoiceType(InvoiceType.NOT_PAID);
        invoice.setInvoiceUrl(fileUrl);

        createPropertyInvoice(request, property_ID, invoice);

        return fileUrl;
    }
}
