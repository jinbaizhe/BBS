package com.parker.bbs.service.impl;

import com.parker.bbs.annotation.LogAnnotation;
import com.parker.bbs.mapper.CollectionMapper;
import com.parker.bbs.pojo.Collection;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.CollectionService;
import com.parker.bbs.util.OperationTarget;
import com.parker.bbs.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List getCollectionsByUserId(Integer userId, Integer currentPage, Integer totalItemsPerPage, String order) {
//        Integer beginIndex = (currentPage-1)*totalItemsPerPage;
        // TODO: 2018/8/15  以后修改
        Integer beginIndex = 0;
        totalItemsPerPage = Integer.MAX_VALUE;
        order = "star_time " +order;
        return collectionMapper.getCollectionsByUserId(userId, beginIndex, totalItemsPerPage, order);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.Collection, operationInfo = "收藏帖子")
    public void insertCollectionNeedLog(Integer userId, Integer postId) {
        Collection collection = new Collection();
        User user = new User();
        user.setId(userId);
        Post post = new Post();
        post.setId(postId);
        collection.setUser(user);
        collection.setPost(post);
        collection.setStarTime(new Timestamp(System.currentTimeMillis()));
        collectionMapper.insertCollection(collection);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Delete, operationTarget = OperationTarget.Collection, operationInfo = "取消收藏帖子")
    public void deleteCollectionNeedLog(Integer userId, Integer postId) {
        collectionMapper.deleteCollection(userId, postId);
    }

    @Override
    public Collection getCollection(Integer userId, Integer postId) {
        return collectionMapper.getCollection(userId, postId);
    }
}
