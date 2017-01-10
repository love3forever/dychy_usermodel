package com.dychy.service;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import com.dychy.repository.UserRepository;

import java.util.List;

/**
 * Created by eclipse on 2017/1/9.
 */
public class UserService implements IUserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByLoginName(String loginName) {
        return userRepository.findByloginName(loginName);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByuserEmail(email);
    }

    public boolean isUserExits(User newUser) {
        return (userRepository.findByloginName(newUser.getLoginName()) != null) || (userRepository.findByuserEmail(newUser.getUserEmail()) != null);
    }

    public boolean saveUser(User user) {
        if (isUserExits(user))
            return false;
        userRepository.save(user);
        return true;
    }

    public boolean deleteUserByloginName(String loginName) {
        User target = userRepository.findByloginName(loginName);
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
        User target = userRepository.findByloginName(loginName);
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
    public List<PrivilegeIns> getPrivByUser(User user) {
        return null;
    }


}
