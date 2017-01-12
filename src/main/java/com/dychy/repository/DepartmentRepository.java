package com.dychy.repository;

import com.dychy.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by eclipse on 2017/1/12.
 */
public interface DepartmentRepository extends MongoRepository<Department,String> {
    Department findBydepartmentName(String departmentName);
    Department findBydepartmentLeader(String departmentLeader);

    Department findBydepartmentParentID(String departmentParentID);
    Department findByid(String id);
}
