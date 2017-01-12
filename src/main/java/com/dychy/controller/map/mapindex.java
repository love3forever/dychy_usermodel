package com.dychy.controller.map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class mapindex {
    @PreAuthorize("hasAnyAuthority('root','map')")
    @RequestMapping("/map")
    public String mapIndex(ModelMap modelMap) {
        return "mapindex";
    }
}
