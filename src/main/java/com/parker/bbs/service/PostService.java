package com.parker.bbs.service;

import com.parker.bbs.pojo.Post;

import java.util.List;

public interface PostService {
    Post getPostById(int id);
    void insertPost(Post post);
    void updatePost(Post post);
    void deletePost(Post post);
    List getPostsBySubForumId(int subForumId, int page, int num, String order);
    int getPostsNumBySubForumId(int subForumId);
    List getPostsByUserId(int userid);
    void updatePostAllAttr(Post post);
    List getSearchResults(String keyWord, int currentPage, String order);
    int getSearchResultNum(String keyWord,String order);
}
