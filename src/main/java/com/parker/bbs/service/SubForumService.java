package com.parker.bbs.service;

import com.parker.bbs.pojo.SubForum;
import java.util.List;

public interface SubForumService {
    List getSubForumsByMainForumId(Integer mainForumId);
    SubForum getSubForumById(Integer id);
    void insertSubForumNeedLog(Integer mainForumId, String name, String info);
    void updateSubForumNeedLog(Integer id, String name, String info);
    void deleteSubForumNeedLog(Integer id);
}
