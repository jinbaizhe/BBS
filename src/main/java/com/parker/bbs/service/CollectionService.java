package com.parker.bbs.service;

import com.parker.bbs.pojo.Collection;

import java.util.List;

public interface CollectionService {
    List getCollectionsByUserId(int userid, int currentPage, int totalItemsPerPage, String order);
    void insertCollection(int userid, int postid);
    void deleteCollection(int userid, int postid);
    Collection getCollection(int userid, int postid);
}
