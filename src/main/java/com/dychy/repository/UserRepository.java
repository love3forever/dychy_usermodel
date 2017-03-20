package com.dychy.repository;

import com.dychy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eclipse on 2017/1/9.
 * 继承MongoRepository，自动实现一些对用户操作的简单方法，只需要方法名按照制定格式即可
 * findBy + 字段名
 */
@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByusername(String loginName);

    User findByuserEmail(String email);

    User findByid(String uid);
}
