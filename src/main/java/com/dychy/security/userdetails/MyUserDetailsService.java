package com.dychy.security.userdetails;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.Resource;
import com.dychy.model.User;
import com.dychy.service.impl.ResourceService;
import com.dychy.service.impl.UserPrivRelService;
import com.dychy.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eclipse on 2017/1/10.
 */
@Service("MyUserDetailsImpl")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserPrivRelService userPrivRelService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.getUserByLoginName(s);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if(user == null){
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
                List<Resource> res = userPrivRelService.getResourceByuserid(user.getId());
                return new MyUserDetails(user, res);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }

    public void updateLogininfo(String username) {
        userService.upDateLastlogin(username);
    }
}
