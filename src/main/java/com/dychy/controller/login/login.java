package com.dychy.controller.login;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by eclipse on 2017/1/10.
 */
@Controller
public class login {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(ModelMap model){
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(ModelMap model,User user) {
        UserService userService = new UserService(userRepository);
        if (userService.verifyPasswordWithloginName(user.getLoginName(), user.getPassword())) {
            System.out.println(user.toString());
        }
        else{
            System.out.println("ERROR");
        }
        model.addAttribute("user", new User());
        return "index";
    }
}
