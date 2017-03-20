package com.dychy.controller.pri;

import com.dychy.controller.indexTemplate;
import com.dychy.model.*;
import com.dychy.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class priindex {
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserPrivRelService userPrivRelService;

    @Autowired
    private DepPrivRelService depPrivRelService;

    @Autowired
    private PrivilegeInsService privilegeInsService;

    @Autowired
    private ResourceService resourceService;

    // 权限管理首页
    @RequestMapping("/pri")
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String priIndex(ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 获取部门列表和用户列表
        List<User> allUsers = new ArrayList<User>();
        List<Department> departments = new ArrayList<Department>();

        allUsers = userService.getAllUsers();
        departments = departmentService.getAllDepartment();

        modelMap.addAttribute("allusers", allUsers);
        modelMap.addAttribute("alldeps", departments);

        return "privs/priIndex";
    }


    // 某个部门权限的列表
    @RequestMapping(value = "/pri/dep/{depname}",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String grantPri2Dep(@PathVariable String depname, ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 获取当前用户的所有权限和当前部门的已有权限
        if (depname != null) {
            Department department = departmentService.getDepartmentByname(depname);

            if (department != null) {
                modelMap.addAttribute("depname", department);

                List<PrivilegeIns> depPrivs = privilegeInsService.getDepPrivs(department.getId());
                modelMap.addAttribute("depPrivs", depPrivs);

                User u = (User) map.get("user");
                List<PrivilegeIns> allPrivs = privilegeInsService.getUserPrivs(u.getId());
                modelMap.addAttribute("allPrivs", allPrivs);
            }
        }
        return "privs/pridep";
    }

    // 为某个部门增加权限
    @RequestMapping(value = "/pri/dep/{depname}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','pri')")
    @ResponseBody
    public boolean addPrivs2Dep(@PathVariable String depname, @RequestBody String[] addprivs) {
        System.out.println(depname);
        String depid = departmentService.getDepartmentByname(depname).getId();

        for (String s:
             addprivs) {
            Resource resource = resourceService.getResbyid(s);
            PrivilegeIns privilegeIns = new PrivilegeIns();
            privilegeIns.setDecInfo(resource.getResDesc());
            privilegeIns.setCreatedTime(new Date());
            privilegeIns.setResType(resource.getResType());
            privilegeIns.setUserid(depid);
            privilegeIns.setResId(resource.getId());

            privilegeInsService.savePrivs(privilegeIns);
        }
        return true;
    }

    // 获取某个用户的权限列表
    @RequestMapping("/pri/user/{username}")
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String grantPri2User(@PathVariable String username,ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 获取用户已有权限和所有权限列表
        if (username != null) {
            User userpriv = userService.getUserByLoginName(username);
            User currentUser = (User) map.get("user");
            if (userpriv != null) {
                modelMap.addAttribute("username", userpriv);

                List<PrivilegeIns> userPrivs = privilegeInsService.getUserPrivs(userpriv.getId());
                List<PrivilegeIns> allPrivs = privilegeInsService.getUserPrivs(currentUser.getId());
                modelMap.addAttribute("userPrivs", userPrivs);
                modelMap.addAttribute("allPrivs", allPrivs);
            }
        }
        return "privs/priuser";
    }


    // 给某个用户增加权限
    @RequestMapping(value = "/pri/user/{username}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','pri')")
    @ResponseBody
    public boolean addPrivs2User(@PathVariable String username, @RequestBody String[] addprivs) {
        System.out.println(username);
        String userid = userService.getUserByLoginName(username).getId();

        for (String s:
                addprivs) {
            Resource resource = resourceService.getResbyid(s);
            PrivilegeIns privilegeIns = new PrivilegeIns();
            privilegeIns.setDecInfo(resource.getResDesc());
            privilegeIns.setCreatedTime(new Date());
            privilegeIns.setResType(resource.getResType());
            privilegeIns.setUserid(userid);
            privilegeIns.setResId(resource.getId());

            privilegeInsService.savePrivs(privilegeIns);
        }

        return true;
    }
}


