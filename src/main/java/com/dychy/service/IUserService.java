package com.dychy.service;

import com.dychy.model.User;

/**
 * Created by eclipse on 2017/1/9.
 */
public interface IUserService {
    public User getUserByLoginName(String loginName) ;

    public boolean isUserExits(User newUser) ;

    public boolean saveUser(User user);

    public boolean deleteUserByloginName(String loginName) ;
}
