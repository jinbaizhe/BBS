package com.parker.bbs.service.impl;

import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.service.MainForumService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MainForumServiceImplTest {

    @Autowired
    private MainForumService mainForumService;

    @Before
    public void before(){
        MainForum mainForum = new MainForum();
        String name = "test篮球场";
        String info = "test篮球场简介";
        mainForumService.insertMainForumNeedLog(name, info);
    }

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
        List<MainForum> list = mainForumService.getAllMainForums();
        Assert.assertNotEquals(0,list.size());
        MainForum mainForum_temp = list.get(0);
        MainForum mainForum = mainForumService.getMainForumById(mainForum_temp.getId());
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