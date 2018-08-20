package com.parker.bbs.service;

import com.parker.bbs.pojo.Post;

import java.sql.Timestamp;
import java.util.List;

public interface PostService {
    Post getPostById(Integer id);
    void insertPostNeedLog(String title, String content, Integer subForumId, Integer userId);
    void updatePostNeedLog(Integer postId, String title, String content);
    void updatePostLastReplyTime(Integer postId, Timestamp lastReplyTime);
    void deletePostNeedLog(Integer postId);
    List getPostsBySubForumId(Integer subForumId, Integer page, Integer num, String order);
    Integer getPostsNumBySubForumId(Integer subForumId);
    List getPostsByUserId(Integer userId);
    Integer setPostTopNeedLog(Integer postId);
    Integer unsetPostTopNeedLog(Integer postId);
    Integer setPostEssentialNeedLog(Integer postId);
    Integer unsetPostEssentialNeedLog(Integer postId);
    Integer getPostLikeNum(Integer postId);
    void setPostLikeNeedLog(Integer userId, Integer postId);
    void unsetPostLikeNeedLog(Integer userId, Integer postId);
    boolean isUserLikePost(Integer userId, Integer postId);
    Integer addPostViewNum(Integer postId);
    Integer getPostViewNum(Integer postId);
    List<Post> getSearchPosts(String key, Integer currentPage, Integer totalItemsPerPage);
    Integer getSearchPostsNum(String key);
}
