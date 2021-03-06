package com.parker.bbs.service.impl;

import com.parker.bbs.pojo.Post;
import com.parker.bbs.service.PostService;
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
public class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Before
    public void before(){
        String content = "test post content";
        String title = "test post title";
        postService.insertPostNeedLog(title, content, 1, 1);
    }

    @Test
    public void getPostById() {
        Post post = postService.getPostById(1);
        Assert.assertNotNull(post);
        Assert.assertNotNull(post.getSendTime());
        Assert.assertNotNull(post.getSubForum());
        Assert.assertNotNull(post.getUser());
    }

    @Test
    public void insertPost() {
    }

    @Test
    public void updatePost() {
    }

    @Test
    public void deletePost() {
    }

    @Test
    public void getPostsBySubForumId() {
    }

    @Test
    public void getPostsNumBySubForumId() {
    }

    @Test
    public void getPostsByUserId() {
        List<Post> list = postService.getPostsByUserId(1);
        Assert.assertNotEquals(0, list.size());
        Post post = list.get(0);
        Assert.assertNotNull(post);
        Assert.assertNotNull(post.getSendTime());
        Assert.assertNotNull(post.getSubForum());
        Assert.assertNotNull(post.getUser());
    }

    @Test
    public void updatePostAllAttr() {
    }
}