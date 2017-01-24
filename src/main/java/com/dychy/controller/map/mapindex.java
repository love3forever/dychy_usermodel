package com.dychy.controller.map;

import com.dychy.controller.indexTemplate;
import com.dychy.service.impl.UserPrivRelService;
import com.dychy.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class mapindex {
    @Autowired
    private UserPrivRelService userPrivRelService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('root','map')")
    @RequestMapping("/map")
    public String mapIndex(ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        return "map/mapindex";
    }
}
