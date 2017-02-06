package com.dychy.service.impl;

import com.dychy.model.PrivilegeIns;
import com.dychy.repository.PriInsRepository;
import com.dychy.service.dao.IPrivilegeInsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
        System.out.println("PrivilegeIns:["+privilegeIns.getId()+"] has created");
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
            this.repository.delete(getPrivByorId(orid) );
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
}
