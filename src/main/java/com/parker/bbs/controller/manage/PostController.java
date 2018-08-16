package com.parker.bbs.controller.manage;

import com.parker.bbs.service.PostService;
import com.parker.bbs.util.Util;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller("postManageController")
@RequestMapping("/manage")
public class PostController {
    @Autowired
    private PostService postService;

//    @RequiresRoles(value = {"Admin", "SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/setPostTop")
    public ModelAndView setPostTop(@RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        postService.setPostTop(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "置顶帖子成功");
        modelAndView.addObject("message", "置顶成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

//    @RequiresRoles(value = {"Admin", "SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/unsetPostTop")
    public ModelAndView unsetPostTop(@RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        postService.unsetPostTop(postId);
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
        postService.setPostEssential(postId);
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
        postService.unsetPostEssential(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "帖子撤销加精成功");
        modelAndView.addObject("message", "撤销加精成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }
}
