package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.PostMapper;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public Post getPostById(int id) {
        return postMapper.getPostById(id);
    }

    @Override
    public void insertPost(Post post) {
        postMapper.insertPost(post);
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    @Override
    public void deletePost(Post post) {
        postMapper.deletePost(post);
    }

    @Override
    public List getPostsBySubForumId(int subForumId, int page, int num, String order) {
        int beginIndex = (page-1)*num;
        //page*num等价于(page-1)*num+num
        int endIndex = page*num;
        return postMapper.getPostsBySubForumId(subForumId, beginIndex, endIndex, order);
    }

    @Override
    public int getPostsNumBySubForumId(int subForumId) {
        return postMapper.getPostsNumBySubForumId(subForumId);
    }

    @Override
    public List getPostsByUserId(int userid) {
        String order="send_time desc";
        return postMapper.getPostsByUserId(userid, order);
    }

    @Override
    public void updatePostAllAttr(Post post) {
        postMapper.updatePost(post);
    }

    @Override
    public List getSearchResults(String keyWord, int currentPage, String order) {
        return null;
    }

    @Override
    public int getSearchResultNum(String keyWord, String order) {
        return 0;
    }
}
