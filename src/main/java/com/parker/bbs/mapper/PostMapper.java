package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    Post getPostById(@Param("id") int id);
    List getPostsBySubForumId(@Param(value = "subForumId") int subForumId, @Param("beginIndex") int beginIndex, @Param("endIndex")int endIndex, @Param("order") String order);
    int getPostsNumBySubForumId(@Param("subForumId") int subForumId);
    int insertPost(@Param("post") Post post);
    int updatePost(@Param("post") Post post);
    int deletePost(@Param("post") Post post);
    List getPostsByUserId(@Param("userid") int userid, @Param("order") String order);
    List getSearchResult(@Param("keyWord") String keyWord, @Param("currentPage") int currentPage, @Param("order") String order);
    int getSearchResultNum(@Param("keyWord") String keyWord, @Param("order") String order);
}
