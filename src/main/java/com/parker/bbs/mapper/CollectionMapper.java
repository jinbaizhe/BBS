package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Collection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    List getCollectionsByUserId(@Param("userId") Integer userId, @Param("beginIndex") Integer beginIndex, @Param("num") Integer num, @Param("order") String order);
    void insertCollection(@Param("collection") Collection collection);
    void deleteCollection(@Param("userId") Integer userId, @Param("postId") Integer postId);
    Collection getCollection(@Param("userId") Integer userId, @Param("postId") Integer postId);
}
