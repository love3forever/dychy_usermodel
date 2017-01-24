package com.dychy.service.dao;

import com.dychy.model.Department;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
@Service
public interface IDepartmentService {
    Department getDepartmentByid(String id);

    Department getDepartmentByname(String departmentName);
    Department getDepartmentByleader(String departmentLeader);

    Department getDepartmentByParentId(String departmentParentID);

    boolean saveDepartment(Department department);


    List<Department> getAllDepartment();

}
