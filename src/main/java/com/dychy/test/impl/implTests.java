package com.dychy.test.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by eclipse on 1/26/17.
 * Use to test all impls
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DepartmentServiceTest.class, DepPrivRelServiceTest.class, PrivilegeInsServiceTest.class,UserDepRelServiceTest.class,UserPrivRelServiceTest.class,UserServiceTest.class})
public class implTests {
}
