package com.dychy.service;

import com.dychy.model.PrivilegeIns;
import com.dychy.repository.PriInsRepository;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Created by eclipse on 2017/1/11.
 */
public class PrivilegeInsService implements IPrivilegeInsService {
    private PriInsRepository repository;

    public PrivilegeInsService(PriInsRepository repository) {
        this.repository = repository;
    }

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
}
