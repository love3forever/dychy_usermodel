package com.dychy.service;

import com.dychy.model.PrivilegeIns;

import java.util.List;

/**
 * Created by eclipse on 2017/1/16.
 */
public interface IDepPrivRelService {
    List<PrivilegeIns> getPrivsByDepartmentId(String depId);

    boolean savePrivsWithDep(String depId,String resId);
}
