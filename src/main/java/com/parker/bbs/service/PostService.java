package com.parker.bbs.service;

import com.parker.bbs.pojo.Post;

import java.util.List;

public interface PostService {
    Post getPostById(int id);
    void insertPost(String title, String content, int subForumId, int userId);
    void updatePost(int postId, String title, String content);
    void deletePost(Post post);
    void deletePost(int postId);
    List getPostsBySubForumId(int subForumId, int page, int num, String order);
    int getPostsNumBySubForumId(int subForumId);
    List getPostsByUserId(int userId);
    List getSearchResults(String keyWord, int currentPage, String order);
    int getSearchResultNum(String keyWord,String order);
}
