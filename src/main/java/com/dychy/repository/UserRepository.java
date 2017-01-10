package com.dychy.repository;

import com.dychy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by eclipse on 2017/1/9.
 */
public interface UserRepository extends MongoRepository<User,String> {
    User findByusername(String loginName);

    User findByuserEmail(String email);

}
