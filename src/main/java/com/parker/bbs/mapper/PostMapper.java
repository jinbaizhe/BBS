package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PostMapper {
    Post getPostById(@Param("id") Integer id);
    List getPostsBySubForumId(@Param(value = "subForumId") Integer subForumId, @Param("beginIndex") Integer beginIndex, @Param("num")Integer endIndex, @Param("order") String order);
    Integer getPostsNumBySubForumId(@Param("subForumId") Integer subForumId);
    Integer insertPost(@Param("post") Post post);
    Integer updatePost(@Param("post") Post post);
    Integer updatePostLastReplyTime(@Param("postId") Integer postId, @Param("lastReplyTime") Timestamp lastReplyTime);
    Integer deletePost(@Param("post") Post post);
    List getPostsByUserId(@Param("userid") Integer userid, @Param("order") String order);
    List<Post> getSearchPosts(@Param("key") String key,@Param("beginIndex") Integer beginIndex, @Param("num") Integer num, @Param("order") String order);
    Integer getSearchPostsNum(@Param("key") String key);
}
