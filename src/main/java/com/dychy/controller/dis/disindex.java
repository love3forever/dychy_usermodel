package com.dychy.controller.dis;

import com.dychy.controller.indexTemplate;
import com.dychy.service.UserPrivRelService;
import com.dychy.service.UserService;
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
public class disindex {

    @Autowired
    private UserPrivRelService userPrivRelService;

    @Autowired
    private UserService userService;

    @RequestMapping("/dis")
    @PreAuthorize("hasAnyAuthority('root','dis')")
    public String disIndex(ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        return "dis/disIndex";
    }
}

