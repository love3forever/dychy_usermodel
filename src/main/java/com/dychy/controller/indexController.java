package com.dychy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/5.
 */
@Controller
public class indexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
