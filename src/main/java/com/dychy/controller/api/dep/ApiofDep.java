package com.dychy.controller.api.dep;

import com.dychy.model.Department;
import com.dychy.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by eclipse on 2017/2/9.
 */
@RestController
public class ApiofDep {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/api/dep")
    public List<Department> getDeps() {
        return departmentService.getAllDepartment();
    }
}
