package com.parker.bbs.service;

import com.parker.bbs.pojo.MainForum;
import java.util.List;

public interface MainForumService {
    List getAllMainForums();
    MainForum getMainForumById(Integer id);
    void insertMainForumNeedLog(String name, String info);
    void updateMainForumNeedLog(Integer id, String name, String info);
    void deleteMainForumNeedLog(Integer id);
}
