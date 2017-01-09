package com.dychy.controller;

import com.dychy.model.User;
import com.dychy.service.UserRepository;
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
        User wangmeng = new User();
        wangmeng.setUserName("wangmeng");
        wangmeng.setLoginName("eclipsesv");
        wangmeng.setUserEmail("eclipse_sv@163.com");

        userRepository.save(wangmeng);
        User u = userRepository.findByuserEmail("eclipse_sv@163.com");
        System.out.println(u.getUserName());

        User u1 = userRepository.findByloginName("eclipsesv");
        System.out.println(u1.getUserName());
        return "index";
    }
}
