package com.dychy.service;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eclipse on 2017/1/9.
 */
public class UserService implements IUserService{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByLoginName(String loginName) {
        return userRepository.findByloginName(loginName);
    }

    public boolean isUserExits(User newUser) {
        return (userRepository.findByloginName(newUser.getLoginName()) != null) || (userRepository.findByuserEmail(newUser.getUserEmail()) != null);
    }

    public boolean saveUser(User user){
        if(isUserExits(user))
            return false;
        userRepository.save(user);
        return true;
    }

    public boolean deleteUserByloginName(String loginName) {
        return false;
    }
}
