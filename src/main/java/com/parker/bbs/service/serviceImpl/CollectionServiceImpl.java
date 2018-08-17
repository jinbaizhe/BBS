package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.CollectionMapper;
import com.parker.bbs.pojo.Collection;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.CollectionService;
import com.parker.bbs.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List getCollectionsByUserId(int userId, int currentPage, int totalItemsPerPage, String order) {
//        int beginIndex = (currentPage-1)*totalItemsPerPage;
        // TODO: 2018/8/15  以后修改
        int beginIndex = 0;
        totalItemsPerPage = Integer.MAX_VALUE;
        order = "star_time " +order;
        return collectionMapper.getCollectionsByUserId(userId, beginIndex, totalItemsPerPage, order);
    }

    @Override
    public void insertCollection(int userId, int postId) {
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
    public void deleteCollection(int userId, int postId) {
        collectionMapper.deleteCollection(userId, postId);
    }

    @Override
    public Collection getCollection(int userId, int postId) {
        return collectionMapper.getCollection(userId, postId);
    }
}
