package com.dychy.controller;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import com.dychy.model.UserPriRel;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserDepRelRepository;
import com.dychy.repository.UserPrivInsRepository;
import com.dychy.repository.UserRepository;
import com.dychy.service.PrivilegeInsService;
import com.dychy.service.UserPrivRelService;
import com.dychy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eclipse on 2017/1/10.
 */
@Controller
public class loginIndex {
    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeInsService privilegeInsService;

    @Autowired
    private UserPrivRelService userPrivRelService;

    @RequestMapping("/")
    public String index(ModelMap modelMap){
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        return "main";
    }

    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "redirect:/";
    }

    @RequestMapping("/addprivs")
    public String add(ModelMap modelMap) {
        User u = userService.getUserByLoginName("root");

        String[] privs = new String[]{
                "root", "home", "dis", "dep", "res", "pri", "map"
        };

        for (String s:
             privs) {
            System.out.println("---------create PrivilegeIns---------");
            PrivilegeIns privilegeIns = new PrivilegeIns();
            privilegeIns.setResId(s);
            privilegeInsService.savePrivs(privilegeIns);
            System.out.println("---------privilegeIns---------"+"[id]:"+privilegeIns.getId());

            System.out.println("---------create UserPriRel---------");
            UserPriRel userPriRel = new UserPriRel();
            userPriRel.setPriInsId(privilegeIns.getId());
            userPriRel.setUserId(u.getId());
            userPriRel.setCreatedTime(new Date());
            userPrivRelService.saveUserPrivsRel(userPriRel);
            System.out.println("---------UserPriRel---------"+"[id]:"+userPriRel.getId());

        }



        return "redirect:/login";
    }
}
