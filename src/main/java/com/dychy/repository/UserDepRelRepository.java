package com.dychy.repository;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.UserDeptRel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
public interface UserDepRelRepository extends MongoRepository<UserDeptRel,String> {
    List<UserDeptRel> findBydeptId(String deptId);
    UserDeptRel findByuserId(String userId);

}
