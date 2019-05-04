package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Bill;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.model.PropertyStatus;
import pl.cdv.ffr.service.BillService;
import pl.cdv.ffr.service.PropertyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    BillService billService;

    @RequestMapping(path = "/properties", method = RequestMethod.GET)
    public List<Property> getAllProperties(HttpServletRequest request, @RequestParam(value = "status", required = false) PropertyStatus propertyStatus) {
        if (propertyStatus != null) {
            return propertyService.findPropertysByStatus(request, propertyStatus);
        } else {
            return propertyService.findAllProperties(request);
        }
    }

    @RequestMapping(path = "/properties/{property_ID}", method = RequestMethod.GET)
    public Property getPropertyById(HttpServletRequest request, @PathVariable("property_ID") String property_ID) {
        return propertyService.findPropertyById(request, property_ID);
    }

    @RequestMapping(path = "/properties", method = RequestMethod.POST)
    public Property createProperty(HttpServletRequest request, @RequestBody Property property) {
        return propertyService.createProperty(request, property);
    }

    @RequestMapping(path = "/properties/{property_ID}", method = RequestMethod.PUT)
    public Property updateProperty(HttpServletRequest request, @RequestBody Property property, @PathVariable("property_ID") String property_ID) {
        return propertyService.updateProperty(request, property, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}", method = RequestMethod.DELETE)
    public void deleteProperty(HttpServletRequest request, @PathVariable("property_ID") String property_ID) {
        propertyService.deleteProperty(request, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills", method = RequestMethod.GET)
    public List<Bill> getAllPropertyBills(HttpServletRequest request, @PathVariable("property_ID") String property_ID) {
        return billService.findAllPropertyBills(request, property_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills/{bill_ID}", method = RequestMethod.GET)
    public Bill getPropertyBillById(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("bill_ID") String bill_ID) {
        return billService.findPropertyBillById(request, property_ID, bill_ID);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills", method = RequestMethod.POST)
    public Bill createPropertyBill(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @RequestBody Bill bill) {
        return billService.createPropertyBill(request, property_ID, bill);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills/{bill_ID}", method = RequestMethod.PUT)
    public Bill updatePropertyBill(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("bill_ID") String bill_ID, @RequestBody Bill bill) {
        return billService.updatePropertyBill(request, property_ID, bill_ID, bill);
    }

    @RequestMapping(path = "/properties/{property_ID}/bills/{bill_ID}", method = RequestMethod.DELETE)
    public void deletePropertyBill(HttpServletRequest request, @PathVariable("property_ID") String property_ID, @PathVariable("bill_ID") String bill_ID) {
        billService.deletePropertyBill(request, property_ID, bill_ID);
    }
}