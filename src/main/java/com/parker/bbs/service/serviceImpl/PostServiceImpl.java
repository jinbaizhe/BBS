package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.PostMapper;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.PostService;
import com.parker.bbs.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public Post getPostById(int id) {
        return postMapper.getPostById(id);
    }

    @Override
    public void insertPost(String title, String content, int subForumId, int userId) {
        Post post = new Post();
        SubForum subForum = new SubForum();
        subForum.setId(subForumId);
        User user = new User();
        user.setId(userId);
        post.setTitle(title);
        post.setContent(content);
        post.setSubForum(subForum);
        post.setUser(user);
        post.setSendTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        post.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        post.setViewNum(0);
        post.setTop(0);
        post.setType(0);
        postMapper.insertPost(post);
    }

    @Override
    public void updatePost(int postId, String title, String content) {
        Post post = postMapper.getPostById(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        postMapper.updatePost(post);
    }

    @Override
    public void deletePost(int postId) {
        Post post = new Post();
        post.setId(postId);
        postMapper.deletePost(post);
    }

    @Override
    public void deletePost(Post post) {
        postMapper.deletePost(post);
    }

    @Override
    public List getPostsBySubForumId(int subForumId, int page, int num, String order) {
        int beginIndex = (page-1)*num;
        //page*num等价于(page-1)*num+num
        int endIndex = page*num;
        return postMapper.getPostsBySubForumId(subForumId, beginIndex, endIndex, order);
    }

    @Override
    public int getPostsNumBySubForumId(int subForumId) {
        return postMapper.getPostsNumBySubForumId(subForumId);
    }

    @Override
    public List getPostsByUserId(int userId) {
        String order="send_time desc";
        return postMapper.getPostsByUserId(userId, order);
    }


    @Override
    public List getSearchResults(String keyWord, int currentPage, String order) {
        return null;
    }

    @Override
    public int getSearchResultNum(String keyWord, String order) {
        return 0;
    }
}
