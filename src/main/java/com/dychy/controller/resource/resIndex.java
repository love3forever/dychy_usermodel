package com.dychy.controller.resource;

import com.dychy.controller.indexTemplate;
import com.dychy.model.*;
import com.dychy.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
@Controller
public class resIndex {
    @Autowired
    private UserPrivRelService userPrivRelService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;


    @Autowired
    private UserDepRelService userDepRelService;

    @Autowired
    private PrivilegeInsService privilegeInsService;


    @RequestMapping("/res")
    @PreAuthorize("hasAnyAuthority('root','res')")
    public String priIndex(ModelMap modelMap) {
        // 通用模板渲染
        indexTemplate template = new indexTemplate(userPrivRelService,userService);
        HashMap<String,Object> map = template.getModelMap();
        if (map == null)
            return "redirect:/login";
        modelMap.addAttribute("user", map.get("user"));
        modelMap.addAttribute("urls", map.get("urls"));


        // 获取当前用户可访问的所有资源

        // 获取用户自身拥有的资源
        User currentUser = (User) map.get("user");
        List<PrivilegeIns> ownPrivs = privilegeInsService.getPrivsByuserid(currentUser.getId());
        List<Resource> ownResource = new ArrayList<Resource>();
        for (PrivilegeIns p:
             ownPrivs) {
            ownResource.add(resourceService.getResbyid(p.getResId()));
        }
        Collections.sort(ownResource);
        modelMap.addAttribute("ownRes", ownResource);

        // 获取用户所在部门附属资源
        List<Resource> depResourece = new ArrayList<Resource>();
        Department department = userDepRelService.getDepartmentByUserId(currentUser.getId());
        if (department!=null){
            List<PrivilegeIns> depPrivs = privilegeInsService.getDepPrivs(department.getId());

            for (PrivilegeIns p :
                    depPrivs) {
                depResourece.add(resourceService.getResbyid(p.getResId()));
            }
        }
        Collections.sort(depResourece);
        modelMap.addAttribute("depRes", depResourece);
        return "res/resIndex";
    }
}
