package com.dychy.controller;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
//    @PreAuthorize("hasAnyRole('admin', 'user')")
    public String index(ModelMap modelMap){
        UserService userService = new UserService(userRepository);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByLoginName(username);
        modelMap.addAttribute("user", currentUser);
        return "main";
    }

    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "200";
    }
}
