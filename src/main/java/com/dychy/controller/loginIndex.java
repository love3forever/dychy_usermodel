package com.dychy.controller;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import com.dychy.model.UserPriRel;
import com.dychy.repository.PriInsRepository;
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
import java.util.List;

/**
 * Created by eclipse on 2017/1/10.
 */
@Controller
public class loginIndex {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriInsRepository priInsRepository;

    @Autowired
    private UserPrivInsRepository userPrivInsRepository;


    @RequestMapping("/")
    public String index(ModelMap modelMap){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username.equals("anonymousUser")) {
            return "redirect:/login";
        }
        UserService userService = new UserService(userRepository);
        User currentUser = userService.getUserByLoginName(username);
        modelMap.addAttribute("user", currentUser);
        UserPrivRelService userPrivRelService = new UserPrivRelService(userRepository, priInsRepository, userPrivInsRepository);
        List<PrivilegeIns> privs = userPrivRelService.getPrivsByUserId(currentUser.getId());

        List<String> urls = new ArrayList<String>();
        for (PrivilegeIns priv:
             privs) {
            urls.add(priv.getResId());
        }

        modelMap.addAttribute("urls", urls);

        return "main";
    }

    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "200";
    }

    @RequestMapping("/addprivs")
    public String add(ModelMap modelMap) {
//        // 给用户和权限进行初始化设置
//        UserService userService = new UserService(userRepository);
//        PrivilegeInsService privilegeInsService = new PrivilegeInsService(priInsRepository);
//
//        UserPrivRelService userPrivRelService = new UserPrivRelService(userRepository, priInsRepository, userPrivInsRepository);
//
//        User u = userService.getUserByLoginName("root");
//
//        String[] privs = new String[]{
//                "root", "home", "dis", "dep", "res", "pri", "map"
//        };
//
//        for (String s:
//             privs) {
//            System.out.println("---------create PrivilegeIns---------");
//            PrivilegeIns privilegeIns = new PrivilegeIns();
//            privilegeIns.setResId(s);
//            privilegeInsService.savePrivs(privilegeIns);
//            System.out.println("---------privilegeIns---------"+"[id]:"+privilegeIns.getId());
//
//            System.out.println("---------create UserPriRel---------");
//            UserPriRel userPriRel = new UserPriRel();
//            userPriRel.setPriInsId(privilegeIns.getId());
//            userPriRel.setUserId(u.getId());
//            userPriRel.setCreatedTime(new Date());
//            userPrivRelService.saveUserPrivsRel(userPriRel);
//            System.out.println("---------UserPriRel---------"+"[id]:"+userPriRel.getId());
//
//        }

        return "redirect:/login";
    }
}
