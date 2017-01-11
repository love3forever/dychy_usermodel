package com.dychy.service;

import com.dychy.model.PrivilegeIns;

/**
 * Created by eclipse on 2017/1/11.
 */
public interface IPrivilegeInsService {
    PrivilegeIns getPrivByresId(String resid);
    PrivilegeIns getPrivByorId(String orid);

    boolean savePrivs(PrivilegeIns privilegeIns);

    boolean deletePrivsByresId(String resid);

    boolean deltePrivsByorId(String orid);

    boolean isPrivsExits(PrivilegeIns privilegeIns);
}
