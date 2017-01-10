package com.dychy.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/10.
 */
@Controller
public class Logout {
    @RequestMapping(value = "/logout")
    public String userLogout() {
        return "200";
    }
}


