package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.*;
import pl.cdv.ffr.service.*;
import pl.cdv.ffr.utils.RaportUtil;
import pl.cdv.ffr.utils.ftp.FTPHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    BillService billService;

    @Autowired
    TenatService tenatService;

    @Autowired
    AlertService alertService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    FTPHelper ftpHelper;

    @RequestMapping(path = "/properties", method = RequestMethod.GET)
    public List<Property> getAllProperties(HttpServletRequest request, @RequestParam(value = "status", required = false) PropertyStatus propertyStatus) {
        if (propertyStatus != null) {
            return propertyService.findPropertysByStatus(request, propertyStatus);
        } else {
            return propertyService.findAllProperties(request);
        }
    }

    @RequestMapping(path = "/properties", method = RequestMethod.POST)
    public Property createProperty(HttpServletRequest request, @RequestBody Property property) {
        return propertyService.createProperty(request, property);
    }

    @RequestMapping(path = "/properties/{property_ID}", method = RequestMethod.GET)
    public Property getPropertyById(HttpServletRequest request, @PathVariable("property_ID") String property_ID) {
        return propertyService.findPropertyById(request, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}", method = RequestMethod.PUT)
    public Property updateProperty(HttpServletRequest request, @RequestBody Property property, @PathVariable("property_ID") String property_ID) {
        return propertyService.updateProperty(request, property, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}", method = RequestMethod.DELETE)
    public void deleteProperty(HttpServletRequest request, @PathVariable("property_ID") String property_ID) {
        propertyService.deleteProperty(request, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/alerts", method = RequestMethod.GET)
    public List<Alert> getAllPropertyAlerts(HttpServletRequest request, @PathVariable("property_ID") String property_ID) {
        return alertService.findAllPropertyAlerts(request, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/alerts", method = RequestMethod.POST)
    public Alert createPropertyAlert(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @RequestBody Alert alert) {
        return alertService.createPropertyAlert(request, property_ID, alert);
    }

    @RequestMapping(path = "/properties/{property_ID}/alerts/{alert_ID}", method = RequestMethod.GET)
    public Alert getPropertyAlertById(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("alert_ID") String alert_ID) {
        return alertService.findPropertyAlertById(request, property_ID, alert_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/alerts/{alert_ID}", method = RequestMethod.PUT)
    public Alert updatePropertyAlert(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("alert_ID") String bill_ID, @RequestBody Alert alert) {
        return alertService.updatePropertyAlert(request, property_ID, bill_ID, alert);
    }

    @RequestMapping(path = "/properties/{property_ID}/alerts/{alert_ID}", method = RequestMethod.DELETE)
    public void deletePropertyAlert(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("alert_ID") String alert_ID) {
        alertService.deletePropertyAlert(request, property_ID, alert_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills", method = RequestMethod.GET)
    public List<Bill> getAllPropertyBills(HttpServletRequest request, @PathVariable("property_ID") String property_ID) {
        return billService.findAllPropertyBills(request, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills", method = RequestMethod.POST)
    public Bill createPropertyBill(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @RequestBody Bill bill) {
        return billService.createPropertyBill(request, property_ID, bill);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills/{bill_ID}", method = RequestMethod.GET)
    public Bill getPropertyBillById(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("bill_ID") String bill_ID) {
        return billService.findPropertyBillById(request, property_ID, bill_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills/{bill_ID}", method = RequestMethod.PUT)
    public Bill updatePropertyBill(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("bill_ID") String bill_ID, @RequestBody Bill bill) {
        return billService.updatePropertyBill(request, property_ID, bill_ID, bill);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills/{bill_ID}", method = RequestMethod.DELETE)
    public void deletePropertyBill(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("bill_ID") String bill_ID) {
        billService.deletePropertyBill(request, property_ID, bill_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills/calculate", method = RequestMethod.GET)
    public CalculateResponse calculate(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @RequestParam(value = "status", required = true) String status, @RequestParam(value = "billType", required = true) BillType billType, @RequestParam(value = "rate", required = true) String rate) {
        return billService.calculate(request, property_ID, billType, status, rate);
    }

    @RequestMapping(value = "/properties/{property_ID}/bills/{bill_ID}/invoice", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateInvoice(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("bill_ID") String bill_ID) throws IOException {

        Bill bill = billService.findPropertyBillById(request, property_ID, bill_ID);
        Property property = propertyService.findPropertyById(request, property_ID);
        Tenat tenat = tenatService.findAllTenats().stream()
                .filter(t -> t.getProperty().getId() == Long.parseLong(property_ID))
                .findFirst()
                .get();

        ByteArrayInputStream bis = RaportUtil.generateInvoice(tenat, property, bill);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=invoice_" + new SimpleDateFormat("yyyy-MM-dd:MM:ss").format(new Date()) + ".pdf");

        Date now = new Date();
        String fileUrl = "";

        try {
            fileUrl = ftpHelper.createAndSaveDecodedFile(bis, "pdf", now, "invoices");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Invoice invoice = new Invoice();
        invoice.setDate(new Date().toString());
        invoice.setInvoiceType(InvoiceType.NOT_PAID);
        invoice.setInvoiceUrl(fileUrl);

        invoiceService.createPropertyInvoice(request, property_ID, invoice);

        ResponseEntity<InputStreamResource> inputStreamResourceResponseEntity = ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

        return inputStreamResourceResponseEntity;
    }

    @RequestMapping(path = "/properties/{property_ID}/invoices", method = RequestMethod.GET)
    public List<Invoice> getAllPropertyInvoices(HttpServletRequest request, @PathVariable("property_ID") String property_ID) {
        return invoiceService.findAllPropertyInvoices(request, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/invoices", method = RequestMethod.POST)
    public Invoice createPropertyInvoices(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @RequestBody Invoice invoice) {
        return invoiceService.createPropertyInvoice(request, property_ID, invoice);
    }

    @RequestMapping(path = "/properties/{property_ID}/invoices/{invoice_ID}", method = RequestMethod.GET)
    public Invoice getPropertyInvoiceById(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("invoice_ID") String invoice_ID) {
        return invoiceService.findPropertyInvoiceById(request, property_ID, invoice_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/invoices/{invoice_ID}", method = RequestMethod.PUT)
    public Invoice updatePropertyInvoice(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("invoice_ID") String invoice_ID, @RequestBody Invoice invoice) {
        return invoiceService.updatePropertyInvoice(request, property_ID, invoice_ID, invoice);
    }

    @RequestMapping(path = "/properties/{property_ID}/invoices/{invoice_ID}", method = RequestMethod.DELETE)
    public void deletePropertyInvoices(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("invoice_ID") String invoice_ID) {
        invoiceService.deletePropertyInvoice(request, property_ID, invoice_ID);
    }
}