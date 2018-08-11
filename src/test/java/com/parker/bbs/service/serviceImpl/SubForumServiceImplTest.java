package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.SubForumMapper;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.service.SubForumService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SubForumServiceImplTest {

    @Autowired
    private SubForumService subForumService;
    @Autowired
    private SubForumMapper subForumMapper;

    @Test
    public void getSubForumsByMainForumId() {
//        List<SubForum> list =subForumService.getSubForumsByMainForumId(2);
        List<SubForum> list =subForumMapper.getSubForumsByMainForumId(2);
        Assert.assertNotEquals(0,list.size());
        SubForum subForum = list.get(0);
        Assert.assertNotNull(subForum);
        Assert.assertNotNull(subForum.getMainForum().getId());
    }

    @Test
    public void getSubForumById() {
//        SubForum subForum = subForumService.getSubForumById(21);
        SubForum subForum = subForumMapper.getSubForumById(21);
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