package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Bill;
import pl.cdv.ffr.service.BillService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BillController {

    @Autowired
    BillService billService;

    @RequestMapping(path = "/bills", method = RequestMethod.GET)
    public List<Bill> getAllBills(HttpServletRequest request) {
        return billService.findAllBills(request);
    }

    @RequestMapping(path = "/bills/{id}", method = RequestMethod.GET)
    public Bill getBill(HttpServletRequest request, @PathVariable("id") String id) {
        return billService.findBillById(request, id);
    }

    @RequestMapping(path = "/bills", method = RequestMethod.POST)
    public Bill createBill(HttpServletRequest request, @RequestBody Bill property) {
        return billService.createBill(request, property);
    }

    @RequestMapping(path = "/bills/{id}", method = RequestMethod.PUT)
    public Bill updateBill(HttpServletRequest request, @RequestBody Bill property, @PathVariable("id") String id) {
        return billService.updateBill(request, property, id);
    }

    @RequestMapping(path = "/bills/{id}", method = RequestMethod.DELETE)
    public void deleteBill(HttpServletRequest request, @PathVariable("id") String id) {
        billService.deleteBill(request, id);
    }
}
