package com.dychy.controller.resource;

import com.dychy.controller.indexTemplate;
import com.dychy.model.*;
import com.dychy.service.impl.*;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

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

    @RequestMapping(value = "/res/addfile",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('root','res')")
    public String addFile(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc){
        System.out.println("File Description:"+desc);
        String fileName = null;
        if (!file.isEmpty()) {
            try {
                // 接受前端post的文件并转化为InputStream
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                InputStream inputStream = new ByteArrayInputStream(bytes);

                // 创建新的资源
                Resource fileResource = new Resource();
                fileResource.setCreatedTime(new Date());
                fileResource.setResDesc(fileName);
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User currentUser = userService.getUserByLoginName(username);
                fileResource.setOwnerId(currentUser.getId());
                fileResource.setResType(1);

                resourceService.saveFileRes(fileResource,inputStream);

                // 对新资源添加权限
                PrivilegeIns filePriv = new PrivilegeIns();
                filePriv.setDecInfo(fileResource.getResDesc());
                filePriv.setCreatedTime(new Date());
                filePriv.setResType(fileResource.getResType());
                filePriv.setResId(fileResource.getId());
                filePriv.setUserid(currentUser.getId());

                privilegeInsService.savePrivs(filePriv);
            } catch (Exception e) {
                return "redirect:/res";
            }
        } else {
            return "redirect:/res";
        }
        return "redirect:/res";
    }

    @RequestMapping(value = "/res/getfile/{fileid}",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('root','res')")
    public void getfile(@PathVariable("fileid") String id,HttpServletResponse response){
        try {
            // get your file as InputStream
            GridFSDBFile gridFSDBFile = resourceService.getFileRes(id);

            // copy it to response's OutputStream
            String filename = gridFSDBFile.getFilename();
            String[] files = filename.split("\\.",2);
            response.setContentType("application/"+files[1]+";charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename="+filename);
            FileCopyUtils.copy(gridFSDBFile.getInputStream(), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }


}
