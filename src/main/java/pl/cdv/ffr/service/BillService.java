package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Bill;
import pl.cdv.ffr.repository.BillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BillService extends BaseService{

    @Autowired
    BillRepository billRepository;

    public List<Bill> findAllBills() {
        return billRepository.findAll();
    }

    public Bill findBillById(String id) {
        Optional<Bill> byId = billRepository.findById(Long.parseLong(id));
        return byId.get();
    }

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill updateBill(Bill newBill, String id) {
        return billRepository.findById(Long.parseLong(id))
                .map(bill -> {
                    copyNonNullProperties(newBill, bill);
                    return billRepository.save(bill);
                })
                .orElseGet(() -> {
                    newBill.setId(Long.parseLong(id));
                    return billRepository.save(newBill);
                });
    }


    public void deleteBill(String id) {
        billRepository.deleteById(Long.parseLong(id));
    }

}
