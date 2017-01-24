package com.dychy.service.impl;

import com.dychy.model.Department;
import com.dychy.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.Oneway;
import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
@Component
public class DepartmentService{

    private DepartmentRepository departmentRepository;

    @Autowired
    DepartmentService(DepartmentRepository repository) {
        this.departmentRepository = repository;
    }

    public Department getDepartmentByid(String id) {
        return departmentRepository.findByid(id);
    }


    public Department getDepartmentByname(String departmentName) {
        return departmentRepository.findBydepartmentName(departmentName);
    }


    public Department getDepartmentByleader(String departmentLeader) {
        return departmentRepository.findBydepartmentLeader(departmentLeader);
    }

    public Department getDepartmentByParentId(String departmentParentID) {
        return departmentRepository.findBydepartmentParentID(departmentParentID);
    }

    @PreAuthorize("hasAnyAuthority('root','dep')")
    public boolean saveDepartment(Department department) {
        if (getDepartmentByname(department.getDepartmentName()) != null) {
            System.out.println("Department:["+department.getDepartmentName()+"] has been created before");
            return false;
        }
        departmentRepository.save(department);
        System.out.println("Department:["+department.getDepartmentName()+"] has been created");
        return true;
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }
}
