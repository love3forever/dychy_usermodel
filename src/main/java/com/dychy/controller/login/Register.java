package com.dychy.controller.login;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eclipse on 2017/1/10.
 */
@Controller
public class Register {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegistePage(ModelMap model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        if (!username.equals("anonymousUser")) {
            return "redirect:/logout";
        }
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegiste(ModelMap model,User user) {
        UserService userService = new UserService(userRepository);
        user.setCreatedTime(new Date());
        UUID uuid = UUID.randomUUID();
        user.setUserNum(uuid.toString());
        userService.saveUser(user);
        model.addAttribute("username",user.getUsername());
        return "index";
    }

}