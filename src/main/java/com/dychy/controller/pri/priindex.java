package com.dychy.controller.pri;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class priindex {
    @RequestMapping("/pri")
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String priIndex(ModelMap modelMap) {
        return "priIndex";
    }
}

