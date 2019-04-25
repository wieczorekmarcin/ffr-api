package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Bill;
import pl.cdv.ffr.service.BillService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BillController {

    @Autowired
    BillService billService;

    @RequestMapping(path = "/bills", method = RequestMethod.GET)
    public List<Bill> getAllBills() {
        return billService.findAllBills();
    }

    @RequestMapping(path = "/bills/{id}", method = RequestMethod.GET)
    public Bill getBill(@PathVariable("id") String id) {
        return billService.findBillById(id);
    }

    @RequestMapping(path = "/bills", method = RequestMethod.POST)
    public Bill createBill(@RequestBody Bill property) {
        return billService.createBill(property);
    }

    @RequestMapping(path = "/bills/{id}", method = RequestMethod.PUT)
    public Bill updateBill(@RequestBody Bill property, @PathVariable("id") String id) {
        return billService.updateBill(property, id);
    }

    @RequestMapping(path = "/bills/{id}", method = RequestMethod.DELETE)
    public void deleteBill(@PathVariable("id") String id) {
        billService.deleteBill(id);
    }
}
