package com.dychy.controller;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserPrivInsRepository;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserPrivRelService;
import com.dychy.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
public class indexTemplate {
    private UserRepository userRepository;

    private PriInsRepository priInsRepository;

    private UserPrivInsRepository userPrivInsRepository;

    public indexTemplate(UserRepository userRepository, PriInsRepository priInsRepository, UserPrivInsRepository userPrivInsRepository) {
        this.userRepository = userRepository;
        this.priInsRepository = priInsRepository;
        this.userPrivInsRepository = userPrivInsRepository;
    }

    public HashMap<String, Object> getModelMap() {
        HashMap<String, Object> indexMap = new HashMap<String, Object>();
        String[] firstPage = new String[]{
            "home", "dis", "dep", "res", "pri", "map"
        };
        List<String> page = new ArrayList<String>();
        Collections.addAll(page, firstPage);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username.equals("anonymousUser")) {
            return null;
        }
        UserService userService = new UserService(userRepository);
        User currentUser = userService.getUserByLoginName(username);
        indexMap.put("user", currentUser);
        UserPrivRelService userPrivRelService = new UserPrivRelService(userRepository, priInsRepository, userPrivInsRepository);
        List<PrivilegeIns> privs = userPrivRelService.getPrivsByUserId(currentUser.getId());

        List<String> urls = new ArrayList<String>();
        for (PrivilegeIns priv:
                privs) {
            if (page.contains(priv.getResId())) {
                urls.add(priv.getResId());
            }
        }
        indexMap.put("urls", urls);

        return indexMap;
    }

}
