package com.dychy.controller.resource;

import com.dychy.controller.indexTemplate;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserPrivInsRepository;
import com.dychy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class resIndex {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriInsRepository priInsRepository;

    @Autowired
    private UserPrivInsRepository userPrivInsRepository;

    @RequestMapping("/res")
    @PreAuthorize("hasAnyAuthority('root','res')")
    public String priIndex(ModelMap modelMap) {
//        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository);
//        modelMap = template.getModelMap();
//        if (modelMap == null) {
//            return "redirect:/login";
//        }
        return "resIndex";
    }
}
