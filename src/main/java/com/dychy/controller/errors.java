package com.dychy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/11.
 */
@Controller
public class errors {
    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }

    @RequestMapping("/404")
    public String notfound() {
        return "404";
    }
}
