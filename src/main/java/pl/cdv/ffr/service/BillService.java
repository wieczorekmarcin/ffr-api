package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Bill;
import pl.cdv.ffr.model.JwtUser;
import pl.cdv.ffr.model.Property;
import pl.cdv.ffr.repository.BillRepository;
import pl.cdv.ffr.repository.PropertyRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService extends BaseService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    private  JwtUserDetailsService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    public List<Bill> findAllPropertyBills(HttpServletRequest request, String property_ID) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        List<Bill> bills = new ArrayList<>();
        if (isRentier(user)) {
            user.getRentier().getProperties().forEach(p -> {
                if (p.getId() == Long.parseLong(property_ID)) {
                    p.getBills().forEach(b -> {
                        bills.add(b);
                    });
                }
            });
            return bills;
        } else {
            throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't get property with id" +  property_ID + ". Try to get Bills by using getBillsForTenat method.");
        }
    }

    public Bill findPropertyBillById(HttpServletRequest request, String property_ID, String bill_ID) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            for (Property property : user.getRentier().getProperties()) {
                if (property.getId() == Long.parseLong(property_ID)) {
                    for (Bill bill : property.getBills()) {
                        if (bill.getId() == Long.parseLong(bill_ID)) {
                            return bill;
                        }
                    }
                }
            }
        } else {
            if (user.getTenat().getProperty().getId() == Long.parseLong(property_ID)) {
                for (Bill bill : user.getTenat().getProperty().getBills()) {
                    if (bill.getId() == Long.parseLong(bill_ID)) {
                        return bill;
                    }
                }
            }
        }
        throw new UsernameNotFoundException("User " + user.getEmail() + " has no property with id:" + property_ID + " or bill with id:" + bill_ID);
    }

    public Bill createPropertyBill(HttpServletRequest request, String property_ID, Bill bill) {
        JwtUser u1 = userService.getUserInfo(request, tokenHeader);
        if (isRentier(u1)) {
            billRepository.save(bill);

            Property property = u1.getRentier().getProperties().stream()
                    .filter(p -> p.getId() == Long.parseLong(property_ID))
                    .findFirst()
                    .get();

            List<Bill> bills = property.getBills();
            bills.add(bill);
            property.setBills(bills);

            propertyRepository.save(property);

            return bill;
        } else {
            throw new UsernameNotFoundException("User " + u1.getEmail() + " is a tenat. He can't create property");
        }
    }

    public Bill updatePropertyBill(HttpServletRequest request, String property_ID, String bill_ID, Bill newBill) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            Property property = user.getRentier().getProperties().stream()
                    .filter(p -> p.getId() == Long.parseLong(property_ID))
                    .findFirst()
                    .get();

            return  property.getBills().stream()
                    .filter(b -> b.getId() == Long.parseLong(bill_ID))
                    .findFirst()
                    .map(bill -> {
                        copyNonNullProperties(newBill, bill);
                        return billRepository.save(bill);
                    })
                    .orElseGet(() -> {
                        newBill.setId(Long.parseLong(bill_ID));
                        return billRepository.save(newBill);
                    });
        } else {
            throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't update property");
        }
    }

    public void deletePropertyBill(HttpServletRequest request, String property_ID, String bill_ID) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            Bill bill = null;
            for (Property property : user.getRentier().getProperties()) {
                if (property.getId() == Long.parseLong(property_ID)) {
                    for (Bill b : property.getBills()) {
                        if (b.getId() == Long.parseLong(bill_ID)) {
                            bill = b;
                        }
                    }
                }
            }
            if (bill != null) {
                billRepository.delete(bill);
            }
        } else {
            throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't delete bill");
        }
    }
}
