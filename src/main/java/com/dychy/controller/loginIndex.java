package com.dychy.controller;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/10.
 */
@Controller
public class loginIndex {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "200";
    }
}
