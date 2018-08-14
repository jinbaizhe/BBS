package com.parker.bbs.controller;

import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.FollowpostService;
import com.parker.bbs.service.PostService;
import com.parker.bbs.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private FollowpostService followpostService;

    @RequiresGuest
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView registerPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/register");
        return modelAndView;
    }

    @RequiresGuest
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerUser(User user){
        ModelAndView modelAndView = new ModelAndView();
        try{
            User real_user = userService.registerUser(user);
            if (real_user != null){
                modelAndView.addObject("message","注册成功");
            }else{
                modelAndView.addObject("message","注册失败，原因未知");
            }
        }catch (Exception e){
            modelAndView.addObject("message",e.getMessage());
        }
        modelAndView.setViewName("user/register");
        return modelAndView;
    }

    @RequiresGuest
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @RequiresGuest
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView loginUser(HttpSession session, User user, @RequestParam(value = "autoLogin",defaultValue = "false") boolean isAutoLogin){
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        if(isAutoLogin){
            token.setRememberMe(true);
        }
        try{
            subject.login(token);
            User realUser = userService.getUserByUsername(user.getUsername());
            session.setAttribute("user", realUser);
            modelAndView.setViewName("redirect:/mainforum.action");
        }catch (AuthenticationException e){
            modelAndView.addObject("message","登录失败："+e.getMessage());
            modelAndView.setViewName("user/login");
        }
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        //其实可以不用手动移除session里的user，因为subject.logout();会执行
        session.removeAttribute("user");
        subject.logout();
        modelAndView.setViewName("redirect:login.action");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/setting")
    public ModelAndView settingPage(@SessionAttribute(value = "user",required = false)User user, @RequestParam(value = "type", defaultValue = "info")String type) {
        ModelAndView modelAndView = new ModelAndView();
        List<Post> posts =  postService.getPostsByUserId(user.getId());
        List<Followpost> followposts = followpostService.getFollowpostsByUserId(user.getId(), "asc");
        modelAndView.addObject("user", user);
        modelAndView.addObject("type", type);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("followposts", followposts);
        modelAndView.setViewName("user/settingInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/userInfo")
    public ModelAndView visitPage(@SessionAttribute(value = "user",required = false)User user, @RequestParam("userid")int userid, @RequestParam(value = "type", defaultValue = "info")String type) {
        /**
         * 其它用户查看某一用户的信息
         */
        ModelAndView modelAndView = new ModelAndView();
        // TODO: 2018/8/11 用户相关信息的获取
        User visit_user = userService.getUserByid(userid);
        User real_user = visit_user;
        if (user!=null && visit_user.getId().equals(user.getId())){
            //当登录用户访问自己的用户资料页面时，转到用户自己的设置资料页面
            real_user = user;
            modelAndView.setViewName("user/settingInfo");
        }
        else{

            modelAndView.setViewName("user/userInfo");
        }
        List<Post> posts = postService.getPostsByUserId(real_user.getId());
        List<Followpost> followposts = followpostService.getFollowpostsByUserId(real_user.getId(), "desc");
        modelAndView.addObject("type", type);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("followposts", followposts);
        modelAndView.addObject("user", real_user);
        return modelAndView;
    }
}
