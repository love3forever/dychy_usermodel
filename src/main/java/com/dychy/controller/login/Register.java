package com.dychy.controller.login;

import com.dychy.model.User;
import com.dychy.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by eclipse on 2017/1/10.
 * 进行用户注册的控制
 */
@Controller
public class Register {
    @Autowired
    private UserService userService;

    // 访问url返回注册页面
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegistePage(ModelMap model){
        // 通过SecurityContextHolder.getContext().getAuthentication().getName()可以获取当前登陆用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        // 如果没有登陆，默认用户名是anonymousUser
        if (!username.equals("anonymousUser")) {
            // 如果已有用户登陆，则跳转到注销登陆页面
            return "redirect:/logout";
        }
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "register";
    }

    // 前端页面post表单数据到后端
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegiste(ModelMap model, @Valid User user, BindingResult result) {
        // 对前端post的数据进行字段值验证
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError  error:list){
                System.out.println(error.getCode()+"---"+error.getArguments()+"---"+error.getDefaultMessage());
            }
            return "register";
        }
        user.setCreatedTime(new Date());
        UUID uuid = UUID.randomUUID();
        user.setUserNum(uuid.toString());
        userService.saveUser(user);
        model.addAttribute("username",user.getUsername());
        // 完成登陆后，页面跳转到首页
        return "redirect:/";
    }

}
