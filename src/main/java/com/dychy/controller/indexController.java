package com.dychy.controller;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/5.
 */
@Controller
public class indexController {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public String index(){
        UserService userService = new UserService(userRepository);
        User wangmeng = new User();
        wangmeng.setUserName("wangmeng");
        wangmeng.setLoginName("eclipsesv");
        wangmeng.setUserEmail("eclipse_sv@163.com");

        userService.saveUser(wangmeng);
        User u = userService.getUserByLoginName("eclipsesv");
        System.out.println(u.getUserName());


//        userRepository.findByuserEmail("eclipse_sv@163.com");
        return "index";
    }
}
