package com.parker.bbs.service.impl;

import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.service.FollowpostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FollowpostServiceImplTest {

    @Autowired
    private FollowpostService followpostService;

    private void testFollowpostAttr(Followpost followpost){
        Assert.assertNotNull(followpost);
        Assert.assertNotNull(followpost.getPost());
        Assert.assertNotNull(followpost.getId());
        Assert.assertNotNull(followpost.getUser());
        Assert.assertNotNull(followpost.getSendTime());
        Assert.assertNotNull(followpost.getUpdateTime());
    }

    @Test
    public void getFollowpostById() {
        Followpost followpost = followpostService.getFollowpostById(1);
        testFollowpostAttr(followpost);
    }

    @Test
    public void getFollowpostsByPostId() {
        List<Followpost> list = followpostService.getFollowpostsByPostId(1,1, 5, "asc");
        Assert.assertNotEquals(0, list.size());
        Followpost followpost = list.get(0);
        testFollowpostAttr(followpost);
    }

    @Test
    public void getFollowpostsNumByPostId() {
        Assert.assertNotEquals(new Integer(0), followpostService.getFollowpostsNumByPostId(1));
    }

    @Test
    public void insertFollowpost() {
        String content = "test followpost";
        followpostService.insertFollowpostNeedLog(content, 1, 1);
    }

    @Test
    public void updateFollowpost() {
        for (int i=0; i<30;i++) {
            insertFollowpost();
        }
    }

    @Test
    public void deleteFollowpost() {
    }

    @Test
    public void getFollowpostsByUserId() {
        List<Followpost> list = followpostService.getFollowpostsByUserId(1, "");
        Assert.assertNotEquals(0, list.size());
        Followpost followpost = list.get(0);
        testFollowpostAttr(followpost);
    }
}