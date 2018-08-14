package com.parker.bbs.controller;

import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.FollowpostService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FollowpostController {
    @Autowired
    private FollowpostService followpostService;

    @RequiresUser
    @RequestMapping(value = "/commitFollowpost", method = RequestMethod.POST)
    public ModelAndView commitAddFollowpost(@SessionAttribute(value = "sessionUser", required = false)User user, @RequestParam(value = "content",required = false  ) String content, @RequestParam(value = "postid",required = false) int postid)
    {
        ModelAndView modelAndView = new ModelAndView();
        int userid = user.getId();
        Followpost followpost = new Followpost();
        followpost.setContent(content);
        followpostService.insertFollowpost(followpost, postid, userid);
        modelAndView.addObject("title", "回帖发表成功");
        modelAndView.addObject("content", "发表成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/updateFollowpost", method = RequestMethod.GET)
    public ModelAndView getUpdateFollowpostPage(@RequestParam("followpostid") int followpostid)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        ModelAndView modelAndView = new ModelAndView();
        Followpost followpost=followpostService.getFollowpostById(followpostid);
        modelAndView.addObject("followpost", followpost);
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/updateFollowpost", method = RequestMethod.POST)
    public ModelAndView commitUpdateFollowpost(@RequestParam("followpostid") int followpostid, @RequestParam("content")String content)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        ModelAndView modelAndView = new ModelAndView();
        Followpost followpost = followpostService.getFollowpostById(followpostid);
        followpost.setContent(content);
        followpostService.updateFollowpost(followpost);
        modelAndView.addObject("title", "回帖修改成功");
        modelAndView.addObject("content", "修改成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/deleteFollowpost", method = RequestMethod.GET)
    public ModelAndView commitDeleteFollowpost(@RequestParam("followpostid") int followpostid)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        ModelAndView modelAndView = new ModelAndView();
        Followpost followpost = new Followpost();
        followpost.setId(followpostid);
        followpostService.deleteFollowpost(followpost);
        modelAndView.addObject("title", "回帖删除成功");
        modelAndView.addObject("content", "删除成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }
}
