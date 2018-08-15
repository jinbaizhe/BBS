package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Collection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    List getCollectionsByUserId(@Param("userId") int userId, @Param("beginIndex") int beginIndex, @Param("num") int num, @Param("order") String order);
    void insertCollection(@Param("collection") Collection collection);
    void deleteCollection(@Param("userId") int userId, @Param("postId") int postId);
    Collection getCollection(@Param("userId") int userId, @Param("postId") int postId);
}
