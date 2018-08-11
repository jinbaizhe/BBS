package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.pojo.User;
import com.parker.bbs.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceImplTest {
    @Autowired
    UserService userService;
    private Logger logger = LogManager.getLogger(this.getClass());

    @Before
    public void before(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userService.registerUser(user);
    }

    @Test
    @Transactional
    @Rollback
    public void getUserRoles() {
        User user = userService.getUserByUsername("test");
        userService.getUserRoles(user);
    }

    @Test
    @Transactional
    @Rollback
    public void getUserOperations() {
        User user = userService.getUserByUsername("test");
        userService.getUserOperations(user);
    }

    @Test
    @Transactional
    @Rollback
    public void validateUser() {
        User user = userService.getUserByUsername("test");
        User temp = userService.validateUser(user);
        Assert.assertNotNull(temp);
        user = new User();
        user.setUsername("test");
        user.setPassword("xxx");
        temp = userService.validateUser(user);
        Assert.assertNull(temp);
    }

}