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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    public ModelAndView browserSubForum(@RequestParam(value = "sfid") int sfid, @RequestParam(value = "page",defaultValue = "1")int page, @RequestParam(value = "order", defaultValue = "lastreplytime")String order)
    {
        ModelAndView modelAndView = new ModelAndView();
        SubForum subForum = subForumService.getSubForumById(sfid);
        MainForum mainForum = mainForumService.getMainForumById(subForum.getMainForum().getId());
        int totalPostsNum=postService.getPostsNumBySubForumId(sfid);
        Pager pager = new Pager(page, postsNumPerPage, totalPostsNum);
        //暂时默认按发帖时间排序
        List<Post> posts=postService.getPostsBySubForumId(sfid, page, postsNumPerPage , order);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("subForum", subForum);
        modelAndView.addObject("mainForum", mainForum);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("web/subForum");
        return modelAndView;
    }

    @RequestMapping("/getSubForumsData")
    @ResponseBody
    public List<SubForum> getSubForumsToJSON(@RequestParam(value = "mfid", required = false) Integer mainForumId) {
        if (mainForumId!=null) {
            List<SubForum> subForums = subForumService.getSubForumsByMainForumId(mainForumId);
            return subForums;
        }else {
            return new ArrayList();
        }
    }
}
