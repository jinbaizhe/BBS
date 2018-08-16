package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.PostMapper;
import com.parker.bbs.pojo.Like;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.PostService;
import com.parker.bbs.util.Util;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("#{configProperties['redisPostLikeSetName']}")
    private String redisPostLikeName;

    @Value("#{configProperties['redisPostViewNumHashName']}")
    private String redisPostViewNumHashName;

    @Value("#{configProperties['redisPostViewNumKeyName']}")
    private String redisPostViewNumKeyName;

    @Override
    public Post getPostById(int id) {
        Post post = postMapper.getPostById(id);
        int postViewNum = addPostViewNum(id);
        post.setViewNum(postViewNum);
        return post;
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
        List<Post> posts = postMapper.getPostsBySubForumId(subForumId, beginIndex, num, order);
        for(Post post:posts){
            post.setViewNum(getPostViewNum(post.getId()));
        }
        return posts;
    }

    @Override
    public int getPostsNumBySubForumId(int subForumId) {
        return postMapper.getPostsNumBySubForumId(subForumId);
    }

    @Override
    public List getPostsByUserId(int userId) {
        String order="send_time desc";
        List<Post> posts = postMapper.getPostsByUserId(userId, order);
        for(Post post:posts){
            post.setViewNum(getPostViewNum(post.getId()));
        }
        return posts;
    }


    @Override
    public List getSearchResults(String keyWord, int currentPage, String order) {
        return null;
    }

    @Override
    public int getSearchResultNum(String keyWord, String order) {
        return 0;
    }

    @Override
    public int setPostTop(int postId) {
        Post post = postMapper.getPostById(postId);
        post.setTop(1);
        return postMapper.updatePost(post);
    }

    @Override
    public int unsetPostTop(int postId) {
        Post post = postMapper.getPostById(postId);
        post.setTop(0);
        return postMapper.updatePost(post);
    }

    @Override
    public int setPostEssential(int postId) {
        Post post = postMapper.getPostById(postId);
        post.setType(1);
        return postMapper.updatePost(post);
    }

    @Override
    public int unsetPostEssential(int postId) {
        Post post = postMapper.getPostById(postId);
        post.setType(0);
        return postMapper.updatePost(post);
    }

    @Override
    public int getPostLikeNum(int postId) {
        String name = redisPostLikeName+postId;
        return redisTemplate.opsForSet().size(name).intValue();
    }

    @Override
    public void setPostLike(int userId, int postId) {
        String name = redisPostLikeName+postId;
        redisTemplate.opsForSet().add(name, userId);
    }

    @Override
    public void unsetPostLike(int userId, int postId) {
        String name = redisPostLikeName+postId;
        redisTemplate.opsForSet().remove(name, userId);
    }

    @Override
    public boolean isUserLikePost(int userId, int postId) {
        String name = redisPostLikeName+postId;
        return redisTemplate.opsForSet().isMember(name, userId);
    }

    @Override
    public int addPostViewNum(int postId) {
        String name = redisPostViewNumKeyName + postId;
        return redisTemplate.opsForHash().increment(redisPostViewNumHashName, name, 1).intValue();
    }

    @Override
    public int getPostViewNum(int postId) {
        String name = redisPostViewNumKeyName + postId;
        int num = 0;
        String s = (String) redisTemplate.opsForHash().get(redisPostViewNumHashName, name);
        if (s != null){
            num = Integer.valueOf(s).intValue();
        }
        return num;
    }
}
