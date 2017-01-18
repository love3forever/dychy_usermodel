package com.dychy.service;

import com.dychy.model.Department;
import com.dychy.model.PrivilegeIns;
import com.dychy.model.UserPriRel;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserDepRelRepository;
import com.dychy.repository.UserPrivInsRepository;
import com.dychy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

//    public UserPrivRelService(UserRepository userRepository, PriInsRepository priInsRepository, UserPrivInsRepository userPrivInsRepository, UserDepRelRepository userDepRelRepository) {
//        this.userRepository = userRepository;
//        this.priInsRepository = priInsRepository;
//        this.userPrivInsRepository = userPrivInsRepository;
//        this.userDepRelRepository = userDepRelRepository;
//    }

    @Override
    public List<PrivilegeIns> getPrivsByUserId(String userid) {
        // 用户权限包括自身权限和归属部门附带权限
        // 获取用户自身权限
        List<PrivilegeIns> privilegeInses = new ArrayList<PrivilegeIns>();
        List<UserPriRel> userPriRels = userPrivInsRepository.findByuserId(userid);
        for (UserPriRel u : userPriRels) {
            privilegeInses.add(priInsRepository.findByid(u.getPriInsId()));
        }

        // 获取部门附带权限
        if (userDepRelRepository.findByuserId(userid) != null) {
            String depId = userDepRelRepository.findByuserId(userid).getDeptId();
            List<UserPriRel> depPriRels = userPrivInsRepository.findBydepId(depId);
            for (UserPriRel u : depPriRels) {
                privilegeInses.add(priInsRepository.findByid(u.getPriInsId()));
            }
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
