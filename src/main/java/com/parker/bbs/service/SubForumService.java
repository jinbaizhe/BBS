package com.parker.bbs.service;

import com.parker.bbs.pojo.SubForum;
import java.util.List;

public interface SubForumService {
    List getSubForumsByMainForumId(int mainForumId);
    SubForum getSubForumById(int id);
    void insertSubForum(int mainForumId, String name, String info);
    void updateSubForum(int id, String name, String info);
    void deleteSubForum(int id);
}
