package com.dychy.controller.dep;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class depindex {
    @RequestMapping("/dep")
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String index(ModelMap modelMap) {
        return "depIndex";
    }
}
