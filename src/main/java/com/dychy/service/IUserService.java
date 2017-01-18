package com.dychy.service;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eclipse on 2017/1/9.
 */
@Service
public interface IUserService {
    User getUserByLoginName(String loginName) ;

    User getUserByEmail(String email);


    List<User> getAllUsers();

    boolean isUserExits(User newUser) ;

    boolean saveUser(User user);

    boolean deleteUserByloginName(String loginName) ;

    boolean deleteUserByemail(String email);

    boolean verifyPasswordWithloginName(String loginName, String password);

    boolean verifyPasswordWithEmail(String email, String password);

    void initDB();

    boolean addUser(User user);

    void clearDB();
}
