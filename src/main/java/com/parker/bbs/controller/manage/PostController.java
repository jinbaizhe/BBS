package com.parker.bbs.controller.manage;

import com.parker.bbs.pojo.Post;
import com.parker.bbs.service.PostService;
import com.parker.bbs.util.Pager;
import com.parker.bbs.util.Util;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller("postManageController")
@RequestMapping("/manage")
public class PostController {
    @Autowired
    private PostService postService;
    @Value("#{configProperties[managePostPerPageNum]}")
    private int managePostPerPageNum;

    @RequiresRoles(value = {"Admin", "SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/post")
    public ModelAndView getPostPage(@RequestParam(value = "mfid",required = false) Integer mainForumId, @RequestParam(value = "sfid",required = false) Integer subForumId, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "search",defaultValue = "")String searchKey, @RequestParam(value = "type",required = false)String type)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Post> posts=null;
        int totalPostsNum = 0;
        if ((type!=null) && (subForumId!=null) && type.equals("browse")){
            posts  = postService.getPostsBySubForumId(subForumId, page, managePostPerPageNum, "postsendtime");
            modelAndView.addObject("mainForumId", mainForumId);
            modelAndView.addObject("subForumId", subForumId);
        }else if ((type!=null) && type.equals("search")){
            posts  = postService.getSearchPosts(searchKey, page, managePostPerPageNum);
            totalPostsNum = postService.getSearchPostsNum(searchKey);
            modelAndView.addObject("message", "搜索关键字："+searchKey);
        }else {
            posts  = new ArrayList<>();
        }
        Pager pager = new Pager(page, managePostPerPageNum, totalPostsNum);
        modelAndView.addObject("title", "帖子管理");
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("page", page);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("search", searchKey);
        modelAndView.setViewName("manage/post");
        return modelAndView;
    }



    @RequiresRoles(value = {"Admin", "SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/setPostTop")
    public ModelAndView setPostTop(@RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        postService.setPostTopNeedLog(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "置顶帖子成功");
        modelAndView.addObject("message", "置顶成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin", "SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/unsetPostTop")
    public ModelAndView unsetPostTop(@RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        postService.unsetPostTopNeedLog(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "撤销置顶帖子成功");
        modelAndView.addObject("message", "撤销置顶成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin", "SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/setPostEssential")
    public ModelAndView setPostEssential(@RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        postService.setPostEssentialNeedLog(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "帖子加精成功");
        modelAndView.addObject("message", "加精成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin", "SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/unsetPostEssential")
    public ModelAndView unsetPostEssential(@RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        postService.unsetPostEssentialNeedLog(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "帖子撤销加精成功");
        modelAndView.addObject("message", "撤销加精成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }
}
