package com.dychy.service;

import com.dychy.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by eclipse on 2017/1/9.
 */
public interface UserRepository extends MongoRepository<User,String> {
    User findByloginName(String loginName);

    User findByuserEmail(String email);

}
