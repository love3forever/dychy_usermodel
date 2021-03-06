package com.dychy.repository;

import com.dychy.model.UserPriRel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 * 继承MongoRepository，自动实现一些对用户权限操作的简单方法，只需要方法名按照制定格式即可
 * findBy + 字段名
 */
@Repository
public interface UserPrivInsRepository extends MongoRepository<UserPriRel,String> {
    List<UserPriRel> findByuserId(String userid);

    List<UserPriRel> findBypriInsId(String priInsId);

    List<UserPriRel> findBydepId(String depId);

//    UserPriRel findByuserIdAndpriInsId(String userid, String priInsId);
}
