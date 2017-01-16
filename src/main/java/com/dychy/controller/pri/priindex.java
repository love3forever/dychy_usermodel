package com.dychy.controller.pri;

import com.dychy.controller.indexTemplate;
import com.dychy.model.Department;
import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import com.dychy.model.UserPriRel;
import com.dychy.repository.*;
import com.dychy.service.DepPrivRelService;
import com.dychy.service.UserDepRelService;
import com.dychy.service.UserPrivRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.context.Lifecycle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/pri")
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String priIndex(ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository,userDepRelRepository);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 获取部门列表和用户列表
        List<User> allUsers = new ArrayList<User>();
        List<Department> departments = new ArrayList<Department>();

        allUsers = userRepository.findAll();
        departments = departmentRepository.findAll();

        modelMap.addAttribute("allusers", allUsers);
        modelMap.addAttribute("alldeps", departments);

        return "privs/priIndex";
    }


    @RequestMapping(value = "/pri/dep/{depname}",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String grantPri2Dep(@PathVariable String depname, ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository,userDepRelRepository);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 获取部门已有权限和所有权限列表
        if (depname != null) {
            Department department = departmentRepository.findBydepartmentName(depname);

            if (department != null) {
                modelMap.addAttribute("depname", department);
                DepPrivRelService depPrivRelService = new DepPrivRelService(userPrivInsRepository,priInsRepository);
                List<PrivilegeIns> depPrivs = depPrivRelService.getPrivsByDepartmentId(department.getId());
                modelMap.addAttribute("depPrivs", depPrivs);

                List<PrivilegeIns> allPrivs = priInsRepository.findAll();
                modelMap.addAttribute("allPrivs", allPrivs);
            }
        }
        return "privs/pridep";
    }


    @RequestMapping(value = "/pri/dep/{depname}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String addPrivs2Dep(@PathVariable String depname, @RequestBody String[] addprivs) {
        System.out.println(depname);
        String depid = departmentRepository.findBydepartmentName(depname).getId();
        UserPrivRelService userPrivRelService = new UserPrivRelService(userRepository, priInsRepository, userPrivInsRepository, userDepRelRepository);

        for (String s:
             addprivs) {
            PrivilegeIns pri = priInsRepository.findByresId(s);
            UserPriRel depPriRel = new UserPriRel();
            depPriRel.setDepId(depid);
            depPriRel.setPriInsId(pri.getId());
            userPrivRelService.saveUserPrivsRel(depPriRel);
        }

        return "redirect:/dep/"+depname;
    }

    @RequestMapping("/pri/user/{username}")
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String grantPri2User(@PathVariable String username,ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userRepository, priInsRepository, userPrivInsRepository,userDepRelRepository);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));

        // 获取用户已有权限和所有权限列表
        if (username != null) {
            User userpriv = userRepository.findByusername(username);

            if (userpriv != null) {
                modelMap.addAttribute("username", userpriv);
                UserPrivRelService userPrivService = new UserPrivRelService(userRepository, priInsRepository, userPrivInsRepository, userDepRelRepository);
                List<PrivilegeIns> userPrivs = userPrivService.getPrivsByUserId(userpriv.getId());
                List<PrivilegeIns> allPrivs = priInsRepository.findAll();
                modelMap.addAttribute("userPrivs", userPrivs);
                modelMap.addAttribute("allPrivs", allPrivs);
            }
        }
        return "privs/priuser";
    }



    @RequestMapping(value = "/pri/user/{username}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','pri')")
    public String addPrivs2User(@PathVariable String username, @RequestBody String[] addprivs) {
        System.out.println(username);
        String userid = userRepository.findByusername(username).getId();
        UserPrivRelService userPrivRelService = new UserPrivRelService(userRepository, priInsRepository, userPrivInsRepository, userDepRelRepository);

        for (String s:
                addprivs) {
            PrivilegeIns pri = priInsRepository.findByresId(s);
            UserPriRel depPriRel = new UserPriRel();
            depPriRel.setUserId(userid);
            depPriRel.setPriInsId(pri.getId());
            userPrivRelService.saveUserPrivsRel(depPriRel);
        }

        return "redirect:/dep/"+username;
    }
}


