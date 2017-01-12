package com.dychy.service;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.UserPriRel;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserPrivInsRepository;
import com.dychy.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 */
public class UserPrivRelService implements IUserPrivInsService {
    private UserRepository userRepository;

    private PriInsRepository priInsRepository;

    private UserPrivInsRepository userPrivInsRepository;

    public UserPrivRelService(UserRepository userRepository, PriInsRepository priInsRepository, UserPrivInsRepository userPrivInsRepository) {
        this.userRepository = userRepository;
        this.priInsRepository = priInsRepository;
        this.userPrivInsRepository = userPrivInsRepository;
    }

    @Override
    public List<PrivilegeIns> getPrivsByUserId(String userid) {
        List<PrivilegeIns> privilegeInses = new ArrayList<PrivilegeIns>();
        List<UserPriRel> userPriRels = userPrivInsRepository.findByuserId(userid);
        for (UserPriRel u : userPriRels) {
            privilegeInses.add(priInsRepository.findByid(u.getPriInsId()));
        }
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
//        return userPrivInsRepository.findByuserIdAndpriInsId(userid, privid) != null;
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
