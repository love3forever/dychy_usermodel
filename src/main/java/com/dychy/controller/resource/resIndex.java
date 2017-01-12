package com.dychy.controller.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class resIndex {

    @RequestMapping("/res")
    @PreAuthorize("hasAnyAuthority('root','res')")
    public String index(Model model) {
        return "resIndex";
    }
}
