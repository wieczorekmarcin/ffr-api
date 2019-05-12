package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Invoice;
import pl.cdv.ffr.model.JwtUser;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.repository.InvoiceRepository;
import pl.cdv.ffr.repository.PropertyRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService extends BaseService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    private  JwtUserDetailsService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    public List<Invoice> findAllPropertyInvoices(HttpServletRequest request, String property_ID) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        List<Invoice> invoices = new ArrayList<>();
        if (isRentier(user)) {
            user.getRentier().getProperties().forEach(p -> {
                if (p.getId() == Long.parseLong(property_ID)) {
                    p.getInvoices().forEach(b -> {
                        invoices.add(b);
                    });
                }
            });
        } else {
            Property tenatProperty = user.getTenat().getProperty();
            if (tenatProperty != null && tenatProperty.getId() == Long.parseLong(property_ID)) {
                tenatProperty.getInvoices().forEach(b -> {
                    invoices.add(b);
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
                        if (invoice.getId() == Long.parseLong(invoice_ID)) {
                            return invoice;
                        }
                    }
                }
            }
        } else {
            if (user.getTenat().getProperty().getId() == Long.parseLong(property_ID)) {
                for (Invoice invoice : user.getTenat().getProperty().getInvoices()) {
                    if (invoice.getId() == Long.parseLong(invoice_ID)) {
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
}
