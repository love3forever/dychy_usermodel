package com.dychy.controller.pri;

import com.dychy.controller.indexTemplate;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserDepRelRepository;
import com.dychy.repository.UserPrivInsRepository;
import com.dychy.repository.UserRepository;
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
public class priindex {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriInsRepository priInsRepository;

    @Autowired
    private UserPrivInsRepository userPrivInsRepository;

    @Autowired
    private UserDepRelRepository userDepRelRepository;

    @RequestMapping("/pri")
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String priIndex(ModelMap modelMap) {
        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository,userDepRelRepository);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));
        return "priIndex";
    }
}

