package com.parker.bbs.service;

import com.parker.bbs.pojo.MainForum;
import java.util.List;

public interface MainForumService {
    List getAllMainForums();
    MainForum getMainForumById(int id);
    void insertMainForum(String name, String info);
    void updateMainForum(int id, String name, String info);
    void deleteMainForum(MainForum mainForum);
    void deleteMainForum(int id);
}
