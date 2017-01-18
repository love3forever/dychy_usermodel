package com.dychy.service;

import com.dychy.model.Department;
import com.dychy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
@Service
public interface IUserDepRelService {
    List<User> getUsersBydepId(String depId);

    Department getDepartmentByUserId(String userId);

    boolean addUserToDepartment(String usrid, String depId);

    List<User> getUsersWithoutDep();

}
