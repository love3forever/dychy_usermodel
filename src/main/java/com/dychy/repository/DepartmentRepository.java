package com.dychy.repository;

import com.dychy.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eclipse on 2017/1/12.
 * 继承MongoRepository，自动实现一些对部门操作的简单方法，只需要方法名按照制定格式即可
 * findBy + 字段名
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    Department findBydepartmentName(String departmentName);

    Department findBydepartmentLeader(String departmentLeader);

    Department findBydepartmentParentID(String departmentParentID);

    Department findByid(String id);
}
