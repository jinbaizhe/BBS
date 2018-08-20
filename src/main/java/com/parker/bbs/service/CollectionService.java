package com.parker.bbs.service;

import com.parker.bbs.pojo.Collection;

import java.util.List;

public interface CollectionService {
    List getCollectionsByUserId(Integer userid, Integer currentPage, Integer totalItemsPerPage, String order);
    void insertCollectionNeedLog(Integer userid, Integer postid);
    void deleteCollectionNeedLog(Integer userid, Integer postid);
    Collection getCollection(Integer userid, Integer postid);
}
