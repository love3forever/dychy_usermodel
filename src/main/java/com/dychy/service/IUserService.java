package com.dychy.service;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;

import java.util.List;

/**
 * Created by eclipse on 2017/1/9.
 */
public interface IUserService {
    User getUserByLoginName(String loginName) ;

    User getUserByEmail(String email);

    boolean isUserExits(User newUser) ;

    boolean saveUser(User user);

    boolean deleteUserByloginName(String loginName) ;

    boolean deleteUserByemail(String email);

    boolean verifyPasswordWithloginName(String loginName, String password);

    boolean verifyPasswordWithEmail(String email, String password);

    void initDB();

    boolean addUser(User user);

    void clearDB();


    List<PrivilegeIns> getPrivByUser(User user);
}
