package com.dychy.service;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;

import java.util.List;

/**
 * Created by eclipse on 2017/1/9.
 */
public interface IUserService {
    public User getUserByLoginName(String loginName) ;

    public User getUserByEmail(String email);

    public boolean isUserExits(User newUser) ;

    public boolean saveUser(User user);

    public boolean deleteUserByloginName(String loginName) ;

    public boolean deleteUserByemail(String email);

    public boolean verifyPasswordWithloginName(String loginName, String password);

    public boolean verifyPasswordWithEmail(String email, String password);

    public void initDB();

    public boolean addUser(User user);

    public void clearDB();


    public List<PrivilegeIns> getPrivByUser(User user);
}
