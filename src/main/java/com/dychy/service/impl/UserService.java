package com.dychy.service.impl;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.dao.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by eclipse on 2017/1/9.
 */
@Component
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    public User getUserByLoginName(String loginName) {
        return userRepository.findByusername(loginName);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByuserEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isUserExits(User newUser) {
        return (userRepository.findByusername(newUser.getUsername()) != null) || (userRepository.findByuserEmail(newUser.getUserEmail()) != null);
    }

    public boolean saveUser(User user) {
        if (isUserExits(user))
            return false;
        userRepository.save(user);
        return true;
    }

    public boolean deleteUserByloginName(String loginName) {
        User target = userRepository.findByusername(loginName);
        if (target == null)
            return false;
        userRepository.delete(target);
        return true;
    }

    @Override
    public boolean deleteUserByemail(String email) {
        User target = userRepository.findByuserEmail(email);
        if (target == null)
            return false;
        userRepository.delete(target);
        return true;
    }

    @Override
    public boolean verifyPasswordWithloginName(String loginName, String password) {
        User target = userRepository.findByusername(loginName);
        return target != null && target.getPassword().equals(password);
    }

    @Override
    public boolean verifyPasswordWithEmail(String email, String password) {
        User target = userRepository.findByuserEmail(email);
        return target != null && target.getPassword().equals(password);
    }

    @Override
    public void initDB() {
        userRepository.deleteAll();
    }

    @Override
    public boolean addUser(User user) {
        if (!isUserExits(user)) {
            userRepository.save(user);
            System.out.println();
            return true;
        }
        return false;
    }

    @Override
    public void clearDB() {
        userRepository.deleteAll();
        System.out.println("Clear db");
    }

    @Override
    public void upDateLastlogin(String username) {
        User target = userRepository.findByusername(username);
        mongoTemplate.upsert(new Query(Criteria.where("username").is(username)), new Update().set("lastLoginTime", new Date()).set("loginTimes",target.getLoginTimes()+1), User.class);
    }

}
