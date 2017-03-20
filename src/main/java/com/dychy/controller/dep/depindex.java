package com.dychy.controller.dep;

import com.dychy.controller.indexTemplate;
import com.dychy.model.*;
import com.dychy.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by eclipse on 2017/1/12.
 * 这里主要是对部门管理中的页面进行控制
 */
@Controller
public class depindex {
    // 这里带有@Autowired注解的都是和数据库操作相关的东西
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

    @Autowired
    private ResourceService resourceService;

    // RequestMapping中参数为实际访问中的路由地址
    // PreAuthorize中hasAnyAuthority可以自动控制当前用户是否拥有所选权限
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

        // 完成deparment创建之后，增加对应的资源
        Resource resource = new Resource();
        resource.setResDesc(department.getDepartmentName());
        resource.setResType(0);
        resource.setResURL("dep/" + department.getDepartmentName());
        resource.setCreatedTime(new Date());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.getUserByLoginName(username);
        resource.setOwnerId(u.getId());
        resource.setId(department.getId());
        resource.setDepId(department.getId());


        resourceService.saveRes(resource);

        // department resource创建之后，增加root用户和owner对department的权限以及部门自身对部门的权限
        if (!username.equals("root")){
            PrivilegeIns privilegeIns = new PrivilegeIns();
            privilegeIns.setCreatedTime(new Date());
            privilegeIns.setResId(resource.getId());
            privilegeIns.setUserid(u.getId());
            privilegeIns.setResType(resource.getResType());
            privilegeIns.setDecInfo(resource.getResDesc());
            privilegeInsService.savePrivs(privilegeIns);
        }
        // 增加root用户对其权限
        User root = userService.getUserByLoginName("root");
        PrivilegeIns privilegeIns = new PrivilegeIns();
        privilegeIns.setCreatedTime(new Date());
        privilegeIns.setResId(resource.getId());
        privilegeIns.setUserid(root.getId());
        privilegeIns.setResType(resource.getResType());
        privilegeIns.setDecInfo(resource.getResDesc());
        privilegeInsService.savePrivs(privilegeIns);

        // 增加部门自身权限
        PrivilegeIns depPrivs = new PrivilegeIns();
        depPrivs.setCreatedTime(new Date());
        depPrivs.setResId(resource.getId());
        depPrivs.setUserid(department.getId());
        depPrivs.setResType(resource.getResType());
        depPrivs.setDecInfo(resource.getResDesc());
        privilegeInsService.savePrivs(depPrivs);



        return "redirect:/dep";
    }

    // 在RequestMapping中url以{xx}形式存在的，是可以将xx作为参数作为url一部分的
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

            if (privilegeInsService.isUserHasPriv(user.getId(), department.getId())) {
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
    @ResponseBody
    public boolean addUser2Dep(@PathVariable String depname, @RequestBody String[] addusers) {
        System.out.println(depname);
        Department department = departmentService.getDepartmentByname(depname);
        String depid = department.getId();

        for (String s:
             addusers) {
            System.out.println(s);
            String userid = userService.getUserByLoginName(s).getId();
            if(userDepRelService.addUserToDepartment(userid, depid)){
                return true;
            }
        }
        return false;
    }
}

