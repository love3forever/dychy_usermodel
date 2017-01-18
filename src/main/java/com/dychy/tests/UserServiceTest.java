package com.dychy.tests;

import com.dychy.model.User;
import com.dychy.repository.UserRepository;
import com.dychy.service.UserService;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by eclipse on 2017/1/9.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    private UserService userService;


    @BeforeClass
    public void initDB() {

    }

    @After
    public void clearDB() {

    }

    @Test
    public void TestgetUserByEmail(){
//        userService = new UserService(userRepository);
//        userService.initDB();
//
//        User target = new User();
//        target.setUsername("eclipse");
//        target.setUserEmail("eclipse_sv@163.com");
//        target.setNickname("wangmeng");
//        userService.saveUser(target);
//
//        User test = userService.getUserByEmail("eclipse_sv@163.com");
//        assertTrue(test.getUsername().equals("eclipse"));
//
//        userService.clearDB();
    }


}
