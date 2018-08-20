package com.parker.bbs.controller;

import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.FollowpostService;
import com.parker.bbs.util.Util;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class FollowpostController {
    @Autowired
    private FollowpostService followpostService;

    @RequiresUser
    @RequestMapping(value = "/commitFollowpost", method = RequestMethod.POST)
    public ModelAndView commitAddFollowpost(@SessionAttribute(value = "user", required = false)User user,
                                            @RequestParam(value = "content",required = false  ) String content,
                                            @RequestParam(value = "postid",required = false) int postid,
                                            @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        ModelAndView modelAndView = new ModelAndView();
        int userId = user.getId();
        followpostService.insertFollowpostNeedLog(content, postid, userId);
        modelAndView.addObject("title", "回帖发表成功");
        modelAndView.addObject("content", "发表成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/updateFollowpost", method = RequestMethod.GET)
    public ModelAndView getUpdateFollowpostPage(@RequestParam("followpostid") int followpostid,
                                                @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        Util.addReferURL(referURL, session);
        ModelAndView modelAndView = new ModelAndView();
        Followpost followpost=followpostService.getFollowpostById(followpostid);
        modelAndView.addObject("followpost", followpost);
        modelAndView.setViewName("web/updateFollowpost");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/updateFollowpost", method = RequestMethod.POST)
    public ModelAndView commitUpdateFollowpost(@RequestParam("followpostid") int followpostid, @RequestParam("content")String content)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        ModelAndView modelAndView = new ModelAndView();
        followpostService.updateFollowpostNeedLog(followpostid, content);
        modelAndView.addObject("title", "回帖修改成功");
        modelAndView.addObject("content", "修改成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/deleteFollowpost", method = RequestMethod.GET)
    public ModelAndView commitDeleteFollowpost(@RequestParam("followpostid") int followpostid, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        Util.addReferURL(referURL, session);
        ModelAndView modelAndView = new ModelAndView();
        Followpost followpost = new Followpost();
        followpost.setId(followpostid);
        followpostService.deleteFollowpostNeedLog(followpostid);
        modelAndView.addObject("title", "回帖删除成功");
        modelAndView.addObject("content", "删除成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }
}
