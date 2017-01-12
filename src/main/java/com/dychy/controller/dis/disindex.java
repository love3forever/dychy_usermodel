package com.dychy.controller.dis;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class disindex {
    @RequestMapping("/dis")
    @PreAuthorize("hasAnyAuthority('root','dis')")
    public String mapIndex(ModelMap modelMap) {
        return "disIndex";
    }
}

