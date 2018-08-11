package com.parker.bbs.service;

import com.parker.bbs.pojo.MainForum;
import java.util.List;

public interface MainForumService {
    List getAllMainForums();
    MainForum getMainForumById(int id);
    MainForum addMainForum(MainForum mainForum);
    void updateMainForum(MainForum mainForum);
    void deleteMainForum(MainForum mainForum);
}
