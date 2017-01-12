package com.dychy.controller.dep;

import com.dychy.controller.indexTemplate;
import com.dychy.model.Department;
import com.dychy.repository.DepartmentRepository;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserPrivInsRepository;
import com.dychy.repository.UserRepository;
import com.dychy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

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
        return "depIndex";
    }


    @RequestMapping(value = "/newdep",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','dep')")
    public String addDepartment(ModelMap modelMap, Department department) {
        DepartmentService departmentService = new DepartmentService(departmentRepository);
        department.setEffectiveTime(new Date());
        UUID uuid = UUID.randomUUID();
        department.setDepartmentNum(uuid.toString());
        department.setDepartmentStatus(1);
        departmentService.saveDepartment(department);

        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));
        return "redirect:/dep";
    }
}
