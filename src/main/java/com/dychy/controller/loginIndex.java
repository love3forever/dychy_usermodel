package com.dychy.controller;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.Resource;
import com.dychy.model.User;
import com.dychy.model.UserPriRel;
import com.dychy.service.impl.PrivilegeInsService;
import com.dychy.service.impl.ResourceService;
import com.dychy.service.impl.UserPrivRelService;
import com.dychy.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;

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

    @Autowired
    private ResourceService resourceService;

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

    @RequestMapping("/initdb")
    public String add(ModelMap modelMap) {
        User root = new User();
        root.setUsername("root");
        root.setNickname("管理员");
        root.setPassword("abc@123");
        root.setUserEmail("eclipse_sv@163.com");
        root.setCreatedTime(new Date());

        userService.saveUser(root);
        System.out.println("-----User:-----" + root.getUsername() + " has saved!");


        String[] privs = new String[]{
                "root", "home", "dis", "dep", "resource", "pri", "map"
        };

        String[] desc = new String[]{
                "管理员", "首页", "讨论组", "部门管理", "资源管理", "权限管理", "地图管理"
        };


        for (int i = 0; i < privs.length; i++) {
            System.out.println("---------creating Resource---------");
            Resource res = new Resource();
            res.setOwnerId(root.getId());
            res.setCreatedTime(new Date());
            res.setResURL(privs[i]);
            res.setResType(0);
            res.setResDesc(desc[i]);

            resourceService.saveRes(res);
            System.out.println("---------saved Resource---------");

            System.out.println("---------creating privilege---------");
            PrivilegeIns privilegeIns = new PrivilegeIns();
            privilegeIns.setUserid(root.getId());
            privilegeIns.setResId(res.getId());
            privilegeIns.setCreatedTime(new Date());
            privilegeIns.setResType(res.getResType());
            privilegeIns.setDecInfo(res.getResDesc());
            privilegeIns.setCanExcute(true);
            privilegeIns.setCanRead(true);
            privilegeIns.setCanWrite(true);

            privilegeInsService.savePrivs(privilegeIns);
            System.out.println("---------saved privilege---------");
        }

        return "redirect:/login";
    }
}
