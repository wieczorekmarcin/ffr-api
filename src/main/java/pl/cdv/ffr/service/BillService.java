package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.*;
import pl.cdv.ffr.repository.BillRepository;
import pl.cdv.ffr.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService extends BaseService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private  JwtUserDetailsService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    public List<Bill> findAllBills(HttpServletRequest request) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        List<Bill> bills = new ArrayList<>();
        if (isRentier(user)) {
            bills = user.getRentier().getBills();
        } else {
            bills = billRepository.findAll().stream()
                    .filter(b -> b.getTenat().getId() == user.getTenat().getId())
                    .collect(Collectors.toList());
        }
        return bills;
    }

    public Bill findBillById(HttpServletRequest request, String id) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        Bill bill;
        if (isRentier(user)) {
            bill = user.getRentier().getBills().stream()
                    .filter(b -> b.getId() == Long.parseLong(id))
                    .findFirst()
                    .get();
        } else {
            bill = billRepository.findAll().stream()
                    .filter(b -> b.getTenat().getId() == user.getTenat().getId())
                    .filter(b -> b.getId() == Long.parseLong(id))
                    .findFirst()
                    .get();
        }

        return bill;
    }

    public Bill createBill(HttpServletRequest request, Bill bill) {
        JwtUser u1 = userService.getUserInfo(request, tokenHeader);
        if (isRentier(u1)) {
            billRepository.save(bill);

            User u2 = userRepository.findById(u1.getId()).get();
            List<Bill> bills = u2.getRentier().getBills();
            bills.add(bill);
            userRepository.save(u2);

            return bill;
        }
        throw new UsernameNotFoundException("User " + u1.getEmail() + " is a tenat. He can't create property");
    }

    public Bill updateBill(HttpServletRequest request, Bill newBill, String id) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            return user.getRentier().getBills().stream()
                    .filter(bill -> bill.getId() == Long.parseLong(id))
                    .findFirst()
                    .map(property -> {
                        copyNonNullProperties(newBill, property);
                        return billRepository.save(property);
                    })
                    .orElseGet(() -> {
                        newBill.setId(Long.parseLong(id));
                        return billRepository.save(newBill);
                    });
        }
        throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't update bill");
    }


    public void deleteBill(HttpServletRequest request, String id) {
        JwtUser user = userService.getUserInfo(request, tokenHeader);
        if (isRentier(user)) {
            Bill bill = user.getRentier().getBills().stream()
                    .filter(p -> p.getId() == Long.parseLong(id))
                    .findFirst()
                    .get();

            billRepository.delete(bill);
        }
        throw new UsernameNotFoundException("User " + user.getEmail() + " is a tenat. He can't delete bill");
    }

}
