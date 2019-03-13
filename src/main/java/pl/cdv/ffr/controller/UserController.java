package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.JwtUser;
import pl.cdv.ffr.service.JwtUserDetailsService;
import pl.cdv.ffr.utils.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private  JwtUserDetailsService userService;

    @RequestMapping(path = "/userInfo", method = RequestMethod.GET)
    public JwtUser getUserInfo(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<JwtUser> getAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public JwtUser getUserById(@PathVariable("id") String id) {
        return userService.findUserById(id);
    }

    @RequestMapping(path = "/rentiers", method = RequestMethod.GET)
    public List<JwtUser> getAllRentiers() {
        return userService.findAllRentiers();
    }

    @RequestMapping(path = "/tenats", method = RequestMethod.GET)
    public List<JwtUser> getAllTenats() {
        return userService.findAllTenats();
    }

}
