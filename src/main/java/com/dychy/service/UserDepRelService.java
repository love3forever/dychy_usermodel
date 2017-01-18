package com.dychy.service;

import com.dychy.model.Department;
import com.dychy.model.User;
import com.dychy.model.UserDeptRel;
import com.dychy.repository.DepartmentRepository;
import com.dychy.repository.UserDepRelRepository;
import com.dychy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eclipse on 2017/1/12.
 */
@Component
public class UserDepRelService implements IUserDepRelService {
    @Autowired
    private UserDepRelRepository userDepRelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository depRepository;

//    public UserDepRelService(UserDepRelRepository userDepRelRepository, UserRepository userRepository, DepartmentRepository depRepository) {
//        this.userDepRelRepository = userDepRelRepository;
//        this.userRepository = userRepository;
//        this.depRepository = depRepository;
//    }

    @Override
    public List<User> getUsersBydepId(String depId) {
        List<UserDeptRel> userDeptRels = userDepRelRepository.findBydeptId(depId);
        List<User> users = new ArrayList<User>();
        for (UserDeptRel u:
             userDeptRels) {
            users.add(userRepository.findByid(u.getUserId()));
        }
        if (users.size() == 0) {
            return null;
        }
        return users;
    }

    @Override
    public Department getDepartmentByUserId(String userId) {
        UserDeptRel userdepRel = userDepRelRepository.findByuserId(userId);
        Department department = depRepository.findByid(userdepRel.getDeptId());
        return department;
    }

    @Override
    public boolean addUserToDepartment(String usrid, String depId) {
        if (userDepRelRepository.findByuserId(usrid) != null) {
            System.out.println("用户已经在别的部门");
            return false;
        }
        UserDeptRel userdepRel = new UserDeptRel();
        userdepRel.setUserId(usrid);
        userdepRel.setDeptId(depId);

        userDepRelRepository.save(userdepRel);
        System.out.println("UserDeptRel:["+userdepRel.getId()+"] has been created");
        return true;
    }

    @Override
    public List<User> getUsersWithoutDep() {
        List<User> allUsers = userRepository.findAll();
        List<User> userWithoutDep;
        List<UserDeptRel> userDeptRels = userDepRelRepository.findAll();
        for (UserDeptRel udr:
             userDeptRels) {
            User u = userRepository.findByid(udr.getUserId());
            allUsers.remove(u);
        }
        User u = userRepository.findByusername("root");
        allUsers.remove(u);
        userWithoutDep = allUsers;
        return userWithoutDep;
    }
}
