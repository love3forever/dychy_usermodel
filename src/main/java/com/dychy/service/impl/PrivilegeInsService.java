package com.dychy.service.impl;

import com.dychy.model.Department;
import com.dychy.model.PrivilegeIns;
import com.dychy.model.UserDeptRel;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserDepRelRepository;
import com.dychy.repository.UserRepository;
import com.dychy.service.dao.IPrivilegeInsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 */
@Component
public class PrivilegeInsService implements IPrivilegeInsService {
    @Autowired
    private PriInsRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserDepRelRepository userDepRelRepository;

    @Override
    public PrivilegeIns getPrivByresId(String resid) {
        return this.repository.findByresId(resid);
    }

    @Override
    public PrivilegeIns getPrivByorId(String orid) {
        return this.repository.findByorId(orid);
    }

    @Override
    public boolean savePrivs(PrivilegeIns privilegeIns) {
//        if (isPrivsExits(privilegeIns))
//            return false;
        this.repository.save(privilegeIns);
        System.out.println("PrivilegeIns:[" + privilegeIns.getId() + "] has created");
        return true;
    }

    @Override
    public boolean deletePrivsByresId(String resid) {
        if (getPrivByresId(resid) != null) {
            this.repository.delete(getPrivByresId(resid));
            return true;
        }
        return false;
    }

    @Override
    public boolean deltePrivsByorId(String orid) {
        if (getPrivByorId(orid) != null) {
            this.repository.delete(getPrivByorId(orid));
            return true;
        }
        return false;
    }

    @Override
    public boolean isPrivsExits(PrivilegeIns privilegeIns) {
        return getPrivByorId(privilegeIns.getOrId()) != null || getPrivByresId(privilegeIns.getResId()) != null;
    }

    @Override
    public List<PrivilegeIns> getAllPrivs() {
        return repository.findAll();
    }

    @Override
    public List<PrivilegeIns> getPrivsByuserid(String userid) {
        return null;
    }

    @Override
    public boolean isUserHasPriv(String uid, String pid) {
        // 判断用户是否拥有权限
        List<PrivilegeIns> privilegeIns = mongoTemplate.find(new Query(Criteria.where("userid").is(uid).and("resId").is(pid)), PrivilegeIns.class);
        // 判断用户所在部门是否拥有权限
        UserDeptRel userDeptRel = userDepRelRepository.findByuserId(uid);
        if (userDeptRel != null) {
            List<PrivilegeIns> depPrivs = mongoTemplate.find(new Query(Criteria.where("userid").is(userDeptRel.getDeptId()).and("resId").is(pid)), PrivilegeIns.class);
            privilegeIns.addAll(depPrivs);
        }
        return !privilegeIns.isEmpty();
    }

    @Override
    public List<PrivilegeIns> getDepPrivs(String depid) {
        return mongoTemplate.find(new Query(Criteria.where("userid").is(depid)), PrivilegeIns.class);
    }

    @Override
    public List<PrivilegeIns> getUserPrivs(String uid) {
        // 获取用户权限
        List<PrivilegeIns> privilegeIns = mongoTemplate.find(new Query(Criteria.where("userid").is(uid)), PrivilegeIns.class);
        // 判断用户所在部门是否拥有权限
        UserDeptRel userDeptRel = userDepRelRepository.findByuserId(uid);
        if (userDeptRel != null) {
            List<PrivilegeIns> depPrivs = mongoTemplate.find(new Query(Criteria.where("userid").is(userDeptRel.getDeptId())), PrivilegeIns.class);
            privilegeIns.addAll(depPrivs);
        }
        return privilegeIns;
    }
}
