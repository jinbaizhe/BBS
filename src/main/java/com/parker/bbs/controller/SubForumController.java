package com.parker.bbs.controller;

import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.service.MainForumService;
import com.parker.bbs.service.PostService;
import com.parker.bbs.service.SubForumService;
import com.parker.bbs.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SubForumController {
    @Autowired
    private SubForumService subForumService;
    @Autowired
    private PostService postService;
    @Autowired
    private MainForumService mainForumService;
    @Value("#{configProperties['postsNumPerPage']}")
    private int postsNumPerPage;

    @RequestMapping("/subforum")
    public ModelAndView browserSubForum(@RequestParam(value = "sfid") int sfid, @RequestParam(value = "page",defaultValue = "1")int page)
    {
        ModelAndView modelAndView = new ModelAndView();
        SubForum subForum = subForumService.getSubForumById(sfid);
        MainForum mainForum = mainForumService.getMainForumById(subForum.getMainForum().getId());
        int totalPostsNum=postService.getPostsNumBySubForumId(sfid);
        Pager pager = new Pager(page, postsNumPerPage, totalPostsNum);
        List<Post> posts=postService.getPostsBySubForumId(sfid, page, postsNumPerPage ,"send_time desc");
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("subForum", subForum);
        modelAndView.addObject("mainForum", mainForum);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("web/subForum");
        return modelAndView;
    }
}
