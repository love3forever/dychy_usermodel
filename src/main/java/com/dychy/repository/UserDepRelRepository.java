package com.dychy.repository;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.UserDeptRel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 * 继承MongoRepository，自动实现一些对用户部门关系操作的简单方法，只需要方法名按照制定格式即可
 * findBy + 字段名
 */
@Repository
public interface UserDepRelRepository extends MongoRepository<UserDeptRel,String> {
    List<UserDeptRel> findBydeptId(String deptId);
    UserDeptRel findByuserId(String userId);
}
