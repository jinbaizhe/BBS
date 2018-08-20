package com.parker.bbs.service.impl;

import com.parker.bbs.annotation.LogAnnotation;
import com.parker.bbs.mapper.PostMapper;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.PostService;
import com.parker.bbs.util.OperationTarget;
import com.parker.bbs.util.OperationType;
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
    public Post getPostById(Integer id) {
        Post post = postMapper.getPostById(id);
        Integer postViewNum = addPostViewNum(id);
        post.setViewNum(postViewNum);
        return post;
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.Post, operationInfo = "发表帖子")
    public void insertPostNeedLog(String title, String content, Integer subForumId, Integer userId) {
        Post post = new Post();
        SubForum subForum = new SubForum();
        subForum.setId(subForumId);
        User user = new User();
        user.setId(userId);
        post.setTitle(title);
        post.setContent(content);
        post.setSubForum(subForum);
        post.setUser(user);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setSendTime(timestamp);
        post.setUpdateTime(timestamp);
        post.setLastReplyTime(timestamp);
        post.setViewNum(0);
        post.setTop(0);
        post.setType(0);
        postMapper.insertPost(post);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.Post, operationInfo = "修改帖子")
    public void updatePostNeedLog(Integer postId, String title, String content) {
        Post post = postMapper.getPostById(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        postMapper.updatePost(post);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Delete, operationTarget = OperationTarget.Post, operationInfo = "删除帖子")
    public void deletePostNeedLog(Integer postId) {
        Post post = new Post();
        post.setId(postId);
        postMapper.deletePost(post);
    }


    @Override
    public List getPostsBySubForumId(Integer subForumId, Integer page, Integer num, String order) {
        if (order.equals("postsendtime")){
            order = "send_time desc";
        }else{
            order = "last_reply_time desc";
        }
        Integer beginIndex = (page-1)*num;
        List<Post> posts = postMapper.getPostsBySubForumId(subForumId, beginIndex, num, order);
        for(Post post:posts){
            post.setViewNum(getPostViewNum(post.getId()));
        }
        return posts;
    }

    @Override
    public Integer getPostsNumBySubForumId(Integer subForumId) {
        return postMapper.getPostsNumBySubForumId(subForumId);
    }

    @Override
    public List getPostsByUserId(Integer userId) {
        String order="send_time desc";
        List<Post> posts = postMapper.getPostsByUserId(userId, order);
        for(Post post:posts){
            post.setViewNum(getPostViewNum(post.getId()));
        }
        return posts;
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.Post, operationInfo = "置顶帖子")
    public Integer setPostTopNeedLog(Integer postId) {
        Post post = postMapper.getPostById(postId);
        post.setTop(1);
        return postMapper.updatePost(post);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.Post, operationInfo = "撤销置顶帖子")
    public Integer unsetPostTopNeedLog(Integer postId) {
        Post post = postMapper.getPostById(postId);
        post.setTop(0);
        return postMapper.updatePost(post);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.Post, operationInfo = "加精帖子")
    public Integer setPostEssentialNeedLog(Integer postId) {
        Post post = postMapper.getPostById(postId);
        post.setType(1);
        return postMapper.updatePost(post);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.Post, operationInfo = "撤销加精帖子")
    public Integer unsetPostEssentialNeedLog(Integer postId) {
        Post post = postMapper.getPostById(postId);
        post.setType(0);
        return postMapper.updatePost(post);
    }

    @Override
    public Integer getPostLikeNum(Integer postId) {
        String name = redisPostLikeName+postId;
        return redisTemplate.opsForSet().size(name).intValue();
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.Like, operationInfo = "点赞帖子")
    public void setPostLikeNeedLog(Integer userId, Integer postId) {
        String name = redisPostLikeName+postId;
        redisTemplate.opsForSet().add(name, userId);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Delete, operationTarget = OperationTarget.Like, operationInfo = "取消点赞帖子")
    public void unsetPostLikeNeedLog(Integer userId, Integer postId) {
        String name = redisPostLikeName+postId;
        redisTemplate.opsForSet().remove(name, userId);
    }

    @Override
    public boolean isUserLikePost(Integer userId, Integer postId) {
        String name = redisPostLikeName+postId;
        return redisTemplate.opsForSet().isMember(name, userId);
    }

    @Override
    public Integer addPostViewNum(Integer postId) {
        String name = redisPostViewNumKeyName + postId;
        return redisTemplate.opsForHash().increment(redisPostViewNumHashName, name, 1).intValue();
    }

    @Override
    public Integer getPostViewNum(Integer postId) {
        String name = redisPostViewNumKeyName + postId;
        Integer num = 0;
        String s = (String) redisTemplate.opsForHash().get(redisPostViewNumHashName, name);
        if (s != null){
            num = Integer.valueOf(s).intValue();
        }
        return num;
    }

    @Override
    public void updatePostLastReplyTime(Integer postId, Timestamp lastReplyTime) {
        postMapper.updatePostLastReplyTime(postId, lastReplyTime);
    }

    @Override
    public List<Post> getSearchPosts(String key, Integer currentPage, Integer totalItemsPerPage) {
        key = "%" + key + "%";
        Integer beginIndex = (currentPage-1)*totalItemsPerPage;
        String order = "id asc";
        return postMapper.getSearchPosts(key, beginIndex, totalItemsPerPage, order);
    }

    @Override
    public Integer getSearchPostsNum(String key) {
        key = "%" + key + "%";
        return postMapper.getSearchPostsNum(key);
    }
}
