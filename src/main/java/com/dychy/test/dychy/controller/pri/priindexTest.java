package com.dychy.test.dychy.controller.pri;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/** 
* priindex Tester. 
* 
* @author eclipsesv 
* @since <pre>Jan 25, 2017</pre> 
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class priindexTest { 

    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 
    
        /** 
    * 
    * Method: priIndex(ModelMap modelMap) 
    * 
    */ 
    @Test
    public void testPriIndex() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: grantPri2Dep(@PathVariable String depname, ModelMap modelMap) 
    * 
    */ 
    @Test
    public void testGrantPri2Dep() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: addPrivs2Dep(@PathVariable String depname, @RequestBody String[] addprivs) 
    * 
    */ 
    @Test
    public void testAddPrivs2Dep() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: grantPri2User(@PathVariable String username, ModelMap modelMap) 
    * 
    */ 
    @Test
    public void testGrantPri2User() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: addPrivs2User(@PathVariable String username, @RequestBody String[] addprivs) 
    * 
    */ 
    @Test
    public void testAddPrivs2User() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        
    } 
