package com.dychy.service;

import com.dychy.model.Department;
import com.dychy.repository.DepartmentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
public class DepartmentService implements IDepartmentService{
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentByid(String id) {
        return departmentRepository.findByid(id);
    }

    @Override
    public Department getDepartmentByname(String departmentName) {
        return departmentRepository.findBydepartmentName(departmentName);
    }

    @Override
    public Department getDepartmentByleader(String departmentLeader) {
        return departmentRepository.findBydepartmentLeader(departmentLeader);
    }

    @Override
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

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }
}
