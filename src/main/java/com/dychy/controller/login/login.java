package com.dychy.controller.login;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/10.
 */
@Controller
public class login {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/login")
    public String index(){
        UserService userService = new UserService(userRepository);

        return "index";
    }
}
