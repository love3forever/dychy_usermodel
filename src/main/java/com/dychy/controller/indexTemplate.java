package com.dychy.controller;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.Resource;
import com.dychy.model.User;
import com.dychy.service.impl.UserPrivRelService;
import com.dychy.service.impl.UserService;
import com.sun.javafx.scene.control.behavior.TableRowBehavior;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
public class indexTemplate {
    private UserPrivRelService userPrivRelService;

    private UserService userService;

    public indexTemplate(UserPrivRelService userPrivRelService, UserService userService) {
        this.userPrivRelService = userPrivRelService;
        this.userService = userService;
    }

    public HashMap<String, Object> getModelMap() {
        HashMap<String, Object> indexMap = new HashMap<String, Object>();
        String[] firstPage = new String[]{
            "home", "dis", "dep", "res", "pri", "map"
        };
        List<String> page = new ArrayList<String>();
        Collections.addAll(page, firstPage);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username==null||username.equals("anonymousUser")) {
            return null;
        }
        User currentUser = userService.getUserByLoginName(username);
        indexMap.put("user", currentUser);
        List<Resource> res = userPrivRelService.getResourceByuserid(currentUser.getId());

        List<Resource> urls = new ArrayList<Resource>();
        for (Resource r:
                res) {
            if (r.getResType() == 0 && page.contains(r.getResURL())) {
                if (!urls.contains(r))
                    urls.add(r);
            }
        }
        indexMap.put("urls", urls);

        return indexMap;
    }

}
