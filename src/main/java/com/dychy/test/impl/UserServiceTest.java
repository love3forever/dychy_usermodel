package com.dychy.test.impl;

import com.dychy.model.User;
import com.dychy.service.impl.UserService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/** 
* UserService Tester. 
* 
* @author eclipsesv 
* @since <pre>Jan 25, 2017</pre> 
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Before
    public void before() throws Exception {
        User user = new User();
        user.setUsername("wangyao");
        user.setUserEmail("wangyao@163.com");
        user.setPassword("abc@123");
        user.setNickname("wy");
        user.setCreatedTime(new Date());
        userService.saveUser(user);
    } 
    
    @After
    public void after() throws Exception {
        userService.deleteUserByloginName("wangyao");
    } 
    
        /** 
    * 
    * Method: getUserByLoginName(String loginName) 
    * 
    */ 
    @Test
    public void testGetUserByLoginName() throws Exception {
        //TODO: Test goes here...
        User u = userService.getUserByLoginName("wangyao");
        assertTrue(u.getUsername().equals("wangyao"));
    } 
    
        /** 
    * 
    * Method: getUserByEmail(String email) 
    * 
    */ 
    @Test
    public void testGetUserByEmail() throws Exception { 
    //TODO: Test goes here...
        User u = userService.getUserByEmail("wangyao@163.com");
        assertTrue(u.getUsername().equals("wangyao"));
    } 
    
        /** 
    * 
    * Method: getAllUsers() 
    * 
    */ 
    @Test
    public void testGetAllUsers() throws Exception { 
        //TODO: Test goes here...
        List<User> users = new ArrayList<User>();
        users = userService.getAllUsers();
        for (User u :
                users) {
            if (u.getUsername().equals("wangyao")) {
                assertTrue(true);
            }
        }
    } 
    
        /** 
    * 
    * Method: isUserExits(User newUser) 
    * 
    */ 
    @Test
    public void testIsUserExits() throws Exception { 
        //TODO: Test goes here...
        User u = new User();
        u.setUsername("wangyao");
        assertTrue(userService.isUserExits(u));
    } 
    
        /** 
    * 
    * Method: saveUser(User user) 
    * 
    */ 
    @Test
    public void testSaveUser() throws Exception { 
        //TODO: Test goes here...
        User u = new User();
        u.setUsername("wangyao");
        assertTrue(userService.saveUser(u)==false);
    } 
    
        /** 
    * 
    * Method: deleteUserByloginName(String loginName) 
    * 
    */ 
    @Test
    public void testDeleteUserByloginName() throws Exception { 
        //TODO: Test goes here...

    } 
    
        /** 
    * 
    * Method: deleteUserByemail(String email) 
    * 
    */ 
    @Test
    public void testDeleteUserByemail() throws Exception { 
        //TODO: Test goes here...
    } 
    
        /** 
    * 
    * Method: verifyPasswordWithloginName(String loginName, String password) 
    * 
    */ 
    @Test
    public void testVerifyPasswordWithloginName() throws Exception { 
        //TODO: Test goes here...
        assertTrue(userService.verifyPasswordWithloginName("wangyao","abc@123"));
    } 
    
        /** 
    * 
    * Method: verifyPasswordWithEmail(String email, String password) 
    * 
    */ 
    @Test
    public void testVerifyPasswordWithEmail() throws Exception { 
        //TODO: Test goes here...
        assertTrue(userService.verifyPasswordWithEmail("wangyao@163.com","abc@123"));
    } 
    
        /** 
    * 
    * Method: initDB() 
    * 
    */ 
    @Test
    public void testInitDB() throws Exception { 
        //TODO: Test goes here...
    } 
    
        /** 
    * 
    * Method: addUser(User user) 
    * 
    */ 
    @Test
    public void testAddUser() throws Exception { 
        //TODO: Test goes here...
        User u = new User();
        u.setUsername("wangyao");
        assertFalse(userService.addUser(u));
    } 
    
        /** 
    * 
    * Method: clearDB() 
    * 
    */ 
    @Test
    public void testClearDB() throws Exception { 
        //TODO: Test goes here...
    } 
    
        /** 
    * 
    * Method: upDateLastlogin(String username) 
    * 
    */ 
    @Test
    public void testUpDateLastlogin() throws Exception { 
        //TODO: Test goes here...
    } 
    
        
    } 
