package com.parker.bbs.service;

import com.parker.bbs.pojo.SubForum;
import java.util.List;

public interface SubForumService {
    List getSubForumsByMainForumId(int mainForumId);
    SubForum getSubForumById(int id);
    void insertSubForum(SubForum subForum);
    void updateSubForum(SubForum subForum);
    void deleteSubForum(SubForum subForum);
}
