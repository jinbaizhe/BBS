package com.parker.bbs.controller;

import com.parker.bbs.pojo.Collection;
import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.CollectionService;
import com.parker.bbs.service.FollowpostService;
import com.parker.bbs.service.PostService;
import com.parker.bbs.service.UserService;
import com.parker.bbs.util.Util;
import com.parker.bbs.util.VerifyCode;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    @Autowired
    private CollectionService collectionService;
    @Value("#{configProperties['collectionsNumPerPage']}")
    private int collectionsNumPerPage;

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
    public ModelAndView loginPage(@RequestHeader(value = "Referer", required = false)String referURL, HttpSession session){
        Util.addReferURL(referURL, session);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @RequiresGuest
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView loginUser(User user, @RequestParam(value = "autoLogin",defaultValue = "false") boolean isAutoLogin, @RequestParam(value = "verifyCode",defaultValue = "") String verifyCode, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        if(isAutoLogin){
            token.setRememberMe(true);
        }
        try{
            VerifyCode realVerifyCode = userService.getVerifyCode(session.getId());
            if (!verifyCode.equalsIgnoreCase(realVerifyCode.getCode()))
            {
                throw new Exception("验证码错误");
            }
            subject.login(token);
            User realUser = userService.getUserByUsername(user.getUsername());
            session.setAttribute("user", realUser);
            String referURL = (String)session.getAttribute("referURL");
            if (referURL!=null){
                modelAndView.setViewName("redirect:" + referURL);
            }else {
                modelAndView.setViewName("redirect:/mainforum.action");
            }
        }catch (Exception e){
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
    public ModelAndView settingPage(@SessionAttribute(value = "user",required = false) User user, @RequestParam(value = "type", defaultValue = "info")String type) {
        ModelAndView modelAndView = new ModelAndView();
        List<Post> posts =  postService.getPostsByUserId(user.getId());
        List<Followpost> followposts = followpostService.getFollowpostsByUserId(user.getId(), "asc");
        // TODO: 2018/8/15 收藏页面还需分页，暂未实现 ，以及collectionService还需修改
        List<Collection> collections = collectionService.getCollectionsByUserId(user.getId(), 1, collectionsNumPerPage, "desc");
        modelAndView.addObject("user", user);
        modelAndView.addObject("type", type);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("followposts", followposts);
        modelAndView.addObject("collections", collections);
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
        List<Collection> collections = collectionService.getCollectionsByUserId(real_user.getId(), 1, collectionsNumPerPage, "desc");
        modelAndView.addObject("type", type);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("followposts", followposts);
        modelAndView.addObject("collections", collections);
        modelAndView.addObject("user", real_user);
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public ModelAndView updateUserInfo(HttpSession session, @RequestParam("info")String info, @RequestParam("sex") String sex, @RequestParam("email") String email)
    {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)session.getAttribute("user");
        userService.updateUserInfo(user.getId(), info, sex, email);
        user = userService.getUserByid(user.getId());
        session.setAttribute("user", user);
        modelAndView.addObject("message_info", "资料修改成功");
        modelAndView.addObject("type", "info");
        modelAndView.setViewName("forward:/user/setting.action?type=info");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
    public ModelAndView updateUserPassword(HttpSession session, @RequestParam("oldpassword")String oldPassword, @RequestParam("password")String password, @RequestParam("repeatpassword")String repeatPassword)
    {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)session.getAttribute("user");
        String s = "密码修改成功";
        try {
            if (!password.equals(repeatPassword)){
                throw new Exception("两次密码输入不一致");
            }
            userService.updateUserPassword(user.getId(), oldPassword, password);
            user = userService.getUserByid(user.getId());
            session.setAttribute("user", user);
        } catch (Exception e) {
            s = e.getMessage();
        }
        modelAndView.addObject("message_password", s);
        modelAndView.addObject("type", "password");
        modelAndView.setViewName("forward:/user/setting.action?type=password");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/starPost")
    public ModelAndView starPost(@SessionAttribute(value = "user", required = false) User user, @RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        collectionService.insertCollection(user.getId(), postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "收藏成功");
        modelAndView.addObject("message", "收藏成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/unstarPost")
    public ModelAndView unstarPost(@SessionAttribute(value = "user", required = false) User user, @RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        collectionService.deleteCollection(user.getId(), postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "取消收藏成功");
        modelAndView.addObject("message", "取消收藏成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpSession session, HttpServletResponse response)
    {
        VerifyCode verifyCode = userService.getVerifyCode(session.getId());
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(verifyCode.getImageByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
