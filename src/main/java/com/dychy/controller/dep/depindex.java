package com.dychy.controller.dep;

import com.dychy.controller.indexTemplate;
import com.dychy.model.Department;
import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import com.dychy.model.UserPriRel;
import com.dychy.repository.*;
import com.dychy.service.DepartmentService;
import com.dychy.service.PrivilegeInsService;
import com.dychy.service.UserDepRelService;
import com.dychy.service.UserPrivRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class depindex {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriInsRepository priInsRepository;

    @Autowired
    private UserPrivInsRepository userPrivInsRepository;

    @Autowired
    private UserDepRelRepository userDepRelRepository;

    @RequestMapping("/dep")
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String priIndex(ModelMap modelMap) {
        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository);
        Department department = new Department();
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));
        modelMap.addAttribute("department", department);

        DepartmentService departmentService = new DepartmentService(departmentRepository);
        List<Department> allDepartment = departmentRepository.findAll();
        modelMap.addAttribute("allDep", allDepartment);
        return "dep/depIndex";
    }


    @RequestMapping(value = "/newdep",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String addDepartment(ModelMap modelMap, Department department) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 新增department
        DepartmentService departmentService = new DepartmentService(departmentRepository);
        department.setEffectiveTime(new Date());
        UUID uuid = UUID.randomUUID();
        department.setDepartmentNum(uuid.toString());
        department.setDepartmentStatus(1);
        departmentService.saveDepartment(department);

        // 完成deparment创建之后，增加对应的权限实例
        PrivilegeIns privilegeIns = new PrivilegeIns();
        privilegeIns.setResId(department.getId());
        PrivilegeInsService privilegeInsService = new PrivilegeInsService(priInsRepository);
        privilegeInsService.savePrivs(privilegeIns);

        // 权限实例增加之后，对root用户增加该权限实例的关联
        UserPriRel userPriRel = new UserPriRel();
        User user = (User) map.get("user");
        userPriRel.setUserId(user.getId());
        userPriRel.setPriInsId(privilegeIns.getId());
        userPriRel.setCreatedTime(new Date());
        UserPrivRelService userPrivRelService = new UserPrivRelService(userRepository, priInsRepository, userPrivInsRepository);
        userPrivRelService.saveUserPrivsRel(userPriRel);

        return "redirect:/dep";
    }


    @RequestMapping(value = "/dep/{depname}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String depInfo(@PathVariable String depname, ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 判断当前用户是否有访问权限
        User user = (User) map.get("user");
        DepartmentService departmentService = new DepartmentService(departmentRepository);
        Department department = departmentService.getDepartmentByname(depname);
        System.out.println(depname);
        if (department != null) {
            UserPrivRelService userPrivRelService = new UserPrivRelService(userRepository, priInsRepository, userPrivInsRepository);
            if (userPrivRelService.isUserHasPrivs(user.getId(), department.getId())) {
                UserDepRelService userDepRelService = new UserDepRelService(userDepRelRepository, userRepository, departmentRepository);
                List<User> depUsers = userDepRelService.getUsersBydepId(department.getId());
                modelMap.addAttribute("depusers", depUsers);
                modelMap.addAttribute("avaUsers", new ArrayList<User>());
                return "dep/depinfo";
            }
            else
                return "403";
        }
        return "403";
    }
}
