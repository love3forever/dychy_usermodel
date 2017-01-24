package com.dychy.controller.dep;

import com.dychy.controller.indexTemplate;
import com.dychy.model.Department;
import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import com.dychy.model.UserPriRel;
import com.dychy.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class depindex {
    @Autowired
    private UserService userService;

    @Autowired
    private UserPrivRelService userPrivRelService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserDepRelService userDepRelService;


    @Autowired
    private PrivilegeInsService privilegeInsService;

    @RequestMapping("/dep")
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String priIndex(ModelMap modelMap) {
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        Department department = new Department();
        HashMap<String, Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));
        modelMap.addAttribute("department", department);

        List<Department> allDepartment = departmentService.getAllDepartment();
        modelMap.addAttribute("allDep", allDepartment);
        return "dep/depIndex";
    }


    @RequestMapping(value = "/newdep", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String addDepartment(ModelMap modelMap, Department department) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String, Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 新增department
        department.setEffectiveTime(new Date());
        UUID uuid = UUID.randomUUID();
        department.setDepartmentNum(uuid.toString());
        department.setDepartmentStatus(1);
        departmentService.saveDepartment(department);

        // 完成deparment创建之后，增加对应的权限实例
        PrivilegeIns privilegeIns = new PrivilegeIns();
        privilegeIns.setResId(department.getId());
        privilegeIns.setCreatedTime(new Date());
        privilegeIns.setDecInfo(department.getDepartmentName());
        privilegeInsService.savePrivs(privilegeIns);

        // 权限实例增加之后，对root用户增加该权限实例的关联
        UserPriRel userPriRel = new UserPriRel();
        User user = (User) map.get("user");
        userPriRel.setUserId(user.getId());
        userPriRel.setPriInsId(privilegeIns.getId());
        userPriRel.setCreatedTime(new Date());

        userPrivRelService.saveUserPrivsRel(userPriRel);

        return "redirect:/dep";
    }


    @RequestMapping(value = "/dep/{depname}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String depInfo(@PathVariable String depname, ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String, Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 判断当前用户是否有访问权限
        User user = (User) map.get("user");

        Department department = departmentService.getDepartmentByname(depname);
        System.out.println(depname);
        if (department != null) {

            if (userPrivRelService.isUserHasPrivs(user.getId(), department.getId())) {
                List<User> depUsers = userDepRelService.getUsersBydepId(department.getId());
                modelMap.addAttribute("depusers", depUsers);
                modelMap.addAttribute("depname", department);
                // 查找当前没有部门归属的用户
                List<User> userWithoutDep = userDepRelService.getUsersWithoutDep();
                modelMap.addAttribute("avaUsers", userWithoutDep);
                return "dep/depinfo";
            } else
                return "403";
        }
        return "403";
    }


    @RequestMapping(value = "/dep/{depname}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String addUser2Dep(@PathVariable String depname, @RequestBody String[] addusers) {
        System.out.println(depname);
        String depid = departmentService.getDepartmentByname(depname).getId();

        for (String s:
             addusers) {
            System.out.println(s);
            String userid = userService.getUserByLoginName(s).getId();
            if(userDepRelService.addUserToDepartment(userid, depid)){

            }
        }
        return "redirect:/dep/"+depname;
    }
}

