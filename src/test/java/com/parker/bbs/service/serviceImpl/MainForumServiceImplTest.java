package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.service.MainForumService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MainForumServiceImplTest {

    @Autowired
    private MainForumService mainForumService;

    @Test
    public void getAllMainForums() {
        List<MainForum> list = mainForumService.getAllMainForums();
        Assert.assertNotEquals(0,list.size());
        MainForum mainForum = list.get(0);
        Assert.assertNotNull(mainForum.getCreateTime());
        Assert.assertNotNull(mainForum.getName());
    }

    @Test
    public void getMainForumById() {
        MainForum mainForum = mainForumService.getMainForumById(2);
        Assert.assertNotNull(mainForum.getCreateTime());
        Assert.assertNotNull(mainForum.getName());
    }

    @Test
    public void addMainForum() {
    }

    @Test
    public void updateMainForum() {
    }

    @Test
    public void deleteMainForum() {
    }
}