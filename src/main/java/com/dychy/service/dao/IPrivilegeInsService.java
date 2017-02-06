package com.dychy.service.dao;

import com.dychy.model.PrivilegeIns;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eclipse on 2017/1/11.
 */
@Service
public interface IPrivilegeInsService {
    PrivilegeIns getPrivByresId(String resid);
    PrivilegeIns getPrivByorId(String orid);

    boolean savePrivs(PrivilegeIns privilegeIns);

    boolean deletePrivsByresId(String resid);

    boolean deltePrivsByorId(String orid);

    boolean isPrivsExits(PrivilegeIns privilegeIns);

    List<PrivilegeIns> getAllPrivs();

    List<PrivilegeIns> getPrivsByuserid(String userid);
}
