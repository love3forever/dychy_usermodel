package com.dychy.repository;

import com.dychy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eclipse on 2017/1/9.
 */
@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByusername(String loginName);

    User findByuserEmail(String email);

    User findByid(String uid);
}
