package com.dychy.controller.login;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by eclipse on 2017/1/10.
 */
@Controller
public class Login {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(ModelMap model){
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginUser(User user) {
        if (userService.verifyPasswordWithloginName(user.getUsername(), user.getPassword())) {
            System.out.println(user.toString());
            userService.upDateLastlogin(user.getUsername());
        }
        else{
            System.out.println("abc");
        }
        return "redirect:/";
    }
}
