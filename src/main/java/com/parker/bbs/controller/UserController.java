package com.parker.bbs.controller;

import com.parker.bbs.pojo.User;
import com.parker.bbs.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView registerPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/register");
        return modelAndView;
    }

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

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView loginUser(User user, @RequestParam(value = "autoLogin",defaultValue = "false")boolean isAutoLogin){
        ModelAndView modelAndView = new ModelAndView();

//        User realUser = userService.validateUser(user);
//        if (realUser != null){
//            modelAndView.addObject("message","登录成功");
//        }else{
//            modelAndView.addObject("message","登录失败");
//        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        if(isAutoLogin){
            token.setRememberMe(true);
        }
        try{
            subject.login(token);
            User realUser = userService.getUserByUsername(user.getUsername());
            modelAndView.addObject("user", realUser);
            modelAndView.setViewName("redirect:/mainforum.action");
        }catch (AuthenticationException e){
            modelAndView.addObject("message","登录失败："+e.getMessage());
            modelAndView.setViewName("user/login");
        }
        return modelAndView;
    }
    @RequestMapping(value = "logout")
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:login.action");
        return modelAndView;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("web/index");
        return modelAndView;
    }

    @RequestMapping(value = "/setting")
    @RequiresUser
    public ModelAndView settingPage(@SessionAttribute("user")User user) {
        ModelAndView modelAndView = new ModelAndView();

        // TODO: 2018/8/11 用户相关信息的获取


        modelAndView.setViewName("user/userInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/visit")
    public ModelAndView visitPage() {
        /**
         * 其它用户查看某一用户的信息
         */
        ModelAndView modelAndView = new ModelAndView();
        // TODO: 2018/8/11 用户相关信息的获取

        modelAndView.setViewName("user/userInfo");
        return modelAndView;
    }
}
