package com.dychy.service.impl;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.UserPriRel;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserDepRelRepository;
import com.dychy.repository.UserPrivInsRepository;
import com.dychy.repository.UserRepository;
import com.dychy.service.dao.IUserPrivInsService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 */
@Component
public class UserPrivRelService implements IUserPrivInsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriInsRepository priInsRepository;

    @Autowired
    private UserPrivInsRepository userPrivInsRepository;

    @Autowired
    private UserDepRelRepository userDepRelRepository;


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<PrivilegeIns> getPrivsByUserId(String userid) {
        // 用户权限包括自身权限和归属部门附带权限
        // 获取用户自身权限
        List<PrivilegeIns> privilegeInses = new ArrayList<PrivilegeIns>();
        BasicDBList basicDBList=new BasicDBList();
        basicDBList.add(new BasicDBObject("userId",userid));
        List<UserPriRel> userPriRels = new ArrayList<UserPriRel>();
        // 获取部门附带权限
        String depId;
        if (userDepRelRepository.findByuserId(userid) != null) {
            depId = userDepRelRepository.findByuserId(userid).getDeptId();
            basicDBList.add(new BasicDBObject("depId",depId));
        }
        DBObject obj = new BasicDBObject();
        obj.put("$or", basicDBList);
        Query query=new BasicQuery(obj);
        userPriRels = mongoTemplate.find(query, UserPriRel.class);

        for (UserPriRel u:
             userPriRels) {
            privilegeInses.add(priInsRepository.findByid(u.getPriInsId()));
        }

        Collections.sort(privilegeInses);

        return privilegeInses;
    }

    @Override
    public boolean saveUserPrivsRel(UserPriRel userPriRel) {
        userPrivInsRepository.save(userPriRel);
        System.out.println("UserPriRel:["+userPriRel.getId()+"] has been created");
        return true;
    }

    @Override
    public boolean deleteUserPrivsRelWithUserid(String userid) {
        return false;
    }

    @Override
    public boolean isUserHasPrivs(String userid, String privid) {
        List<PrivilegeIns> privs = getPrivsByUserId(userid);
        for (PrivilegeIns pri:
             privs) {
            if (pri.getResId().equals(privid)) {
                return true;
            }
        }
        return false;
    }
}
