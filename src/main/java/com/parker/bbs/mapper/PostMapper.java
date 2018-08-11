package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    Post getPostById(int id);
    List getPostsBySubForumId(@Param(value = "subForumId") int subForumId, int currentPage, String order);
    int getPostsNumBySubForumId(int subForumId);
    Post insertPost(Post post);
    Post updatePost(Post post);
    void deletePost(Post post);
    List getPostsByUserId(int userid, String order);
    List getSearchResult(String keyWord, int currentPage, String order);
    int getSearchResultNum(String keyWord,String order);
}
