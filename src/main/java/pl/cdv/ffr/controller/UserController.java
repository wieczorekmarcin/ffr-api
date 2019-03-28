package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.JwtUser;
import pl.cdv.ffr.model.User;
import pl.cdv.ffr.model.UserType;
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
    private  JwtUserDetailsService userService;

    @RequestMapping(path = "/userInfo", method = RequestMethod.GET)
    public JwtUser getUserInfo(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = userService.loadUserByUsername(username);
        return user;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<JwtUser> getAllUsersWithParams(@RequestParam(value = "status", required = false) UserType userType) {
        if (userType != null) {
            return userService.findUserByUserType(userType);
        } else {
            return userService.findAllUsers();
        }
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public JwtUser getUserById(@PathVariable("id") String id) {
        return userService.findUserById(id);
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user, @PathVariable("id") String id) {
        return userService.updateUser(user, id);
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }
}
