package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.SubForumMapper;
import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.service.SubForumService;
import com.parker.bbs.util.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SubForumServiceImplTest {

    @Autowired
    private SubForumService subForumService;

    @Before
    public void before(){
        SubForum subForum = new SubForum();
        String name = "test专区";
        String info = "testinfo";
        int mainForumId = 1;
        subForumService.insertSubForum(mainForumId, name, info);
    }


    @Test
    public void getSubForumsByMainForumId() {
        List<SubForum> list =subForumService.getSubForumsByMainForumId(1);
        Assert.assertNotEquals(0,list.size());
        SubForum subForum = list.get(0);
        Assert.assertNotNull(subForum);
        Assert.assertNotNull(subForum.getMainForum().getId());
    }

    @Test
    public void getSubForumById() {
        SubForum subForum = subForumService.getSubForumById(1);
        Assert.assertNotNull(subForum);
        Assert.assertNotNull(subForum.getMainForum().getId());
    }

    @Test
    @Transactional
    @Rollback
    public void insertSubForum() {

    }

    @Test
    @Transactional
    @Rollback
    public void updateSubForum() {
    }

    @Test
    @Transactional
    @Rollback
    public void deleteSubForum() {
    }
}