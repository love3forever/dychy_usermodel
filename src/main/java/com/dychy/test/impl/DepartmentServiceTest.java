package com.dychy.test.impl;

import com.dychy.model.Department;
import com.dychy.service.impl.DepartmentService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/** 
* DepartmentService Tester. 
* 
* @author eclipsesv 
* @since <pre>Jan 25, 2017</pre> 
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @Before
    public void before() throws Exception {
        Department department = new Department();
        department.setDepartmentName("luoning");
        department.setEffectiveTime(new Date());
    }

    
    @After
    public void after() throws Exception {

    } 
    
        /** 
    * 
    * Method: getDepartmentByid(String id) 
    * 
    */ 
    @Test
    public void testGetDepartmentByid() throws Exception { 
        //TODO: Test goes here...
    } 
    
        /** 
    * 
    * Method: getDepartmentByname(String departmentName) 
    * 
    */ 
    @Test
    public void testGetDepartmentByname() throws Exception { 
        //TODO: Test goes here...
    } 
    
        /** 
    * 
    * Method: getDepartmentByleader(String departmentLeader) 
    * 
    */ 
    @Test
    public void testGetDepartmentByleader() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: getDepartmentByParentId(String departmentParentID) 
    * 
    */ 
    @Test
    public void testGetDepartmentByParentId() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: saveDepartment(Department department) 
    * 
    */ 
    @Test
    public void testSaveDepartment() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: getAllDepartment() 
    * 
    */ 
    @Test
    public void testGetAllDepartment() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        
    } 
