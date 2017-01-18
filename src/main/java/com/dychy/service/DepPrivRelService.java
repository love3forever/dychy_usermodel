package com.dychy.service;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.UserPriRel;
import com.dychy.repository.PriInsRepository;
import com.dychy.repository.UserPrivInsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eclipse on 2017/1/16.
 */
@Component
public class DepPrivRelService implements IDepPrivRelService {
    @Autowired
    private UserPrivInsRepository userPrivInsRepository;

    @Autowired
    private PriInsRepository priRepostoty;

//    public DepPrivRelService(UserPrivInsRepository userPrivInsRepository, PriInsRepository priRepostoty) {
//        this.userPrivInsRepository = userPrivInsRepository;
//        this.priRepostoty = priRepostoty;
//    }

    @Override
    public List<PrivilegeIns> getPrivsByDepartmentId(String depId) {
        List<UserPriRel> userPriRels = userPrivInsRepository.findBydepId(depId);
        List<PrivilegeIns> privilegeInses = new ArrayList<PrivilegeIns>();
        for (UserPriRel s:
             userPriRels) {
            PrivilegeIns priv = priRepostoty.findByid(s.getPriInsId());
            privilegeInses.add(priv);
        }
        return privilegeInses;
    }

    @Override
    public boolean savePrivsWithDep(String depId,String resId) {
        PrivilegeIns priv = priRepostoty.findByresId(resId);
        if (priv != null) {
            UserPriRel userPriRel = new UserPriRel();
            userPriRel.setDepId(depId);
            userPriRel.setPriInsId(priv.getId());
            userPrivInsRepository.save(userPriRel);
            return true;
        }
        return false;
    }
}
