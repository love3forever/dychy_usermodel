package com.dychy.model;

import com.sun.tools.javac.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by eclipse on 2017/1/5.
 */
public interface UserRepository extends MongoRepository<User, String> {
    public User findByusername(String username);
}
