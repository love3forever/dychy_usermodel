package com.dychy.security.userdetails;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserService;
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
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        UserService userService = new UserService(userRepository);
        try {
            user = userService.getUserByLoginName(s);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if(user == null){
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
                List<PrivilegeIns> privs = userService.getPrivByUser(user);
                return new MyUserDetails(user, privs);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }
}
