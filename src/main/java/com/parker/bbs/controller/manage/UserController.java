package com.parker.bbs.controller.manage;

import com.parker.bbs.pojo.User;
import com.parker.bbs.service.UserService;
import com.parker.bbs.util.Pager;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("userManageController")
@RequestMapping("/manage")
public class UserController {
    @Autowired
    private UserService userService;
    @Value("#{configProperties['manageUserPerPageNum']}")
    private int manageUserPerPageNum;

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/user")
    public ModelAndView getUserPage(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "search",defaultValue = "")String searchKey)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users;
        if (searchKey.equals("")) {
           users  = userService.getUsersExceptAdminAndSuperAdmin(page, manageUserPerPageNum);
            modelAndView.addObject("search", "");
        }else {
            users = userService.getSearchUsers(searchKey, page, manageUserPerPageNum);
            modelAndView.addObject("message", "搜索关键字："+searchKey);
            modelAndView.addObject("search", searchKey);
        }
        int totalUsersNum=userService.getUsersNumExceptAdminAndSuperAdmin();
        Pager pager=new Pager(page,manageUserPerPageNum,totalUsersNum);
        modelAndView.addObject("users", users);
        modelAndView.addObject("page", page);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("title", "查看用户");
        modelAndView.addObject("type", "user");
        modelAndView.setViewName("manage/user");
        return modelAndView;
    }




    @RequiresRoles(value = "SuperAdmin")
    @RequestMapping(value = "/admin")
    public ModelAndView getAdminPage(@RequestParam(value = "page", defaultValue = "1") int page)
    {
        List<User> users=userService.getAdmins(page, manageUserPerPageNum);
        int totalUsersNum=userService.getAdminsNum();
        Pager pager=new Pager(page,manageUserPerPageNum,totalUsersNum);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.addObject("page", page);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("title", "查看管理员");
        modelAndView.addObject("type", "admin");
        modelAndView.setViewName("manage/user");
        return modelAndView;
    }

    @RequiresRoles(value = "SuperAdmin")
    @RequestMapping(value = "/setUserAdmin")
    public ModelAndView setUserAdmin(@RequestParam("userid") int userId)
    {
        userService.setUserAdminNeedLog(userId);
        User user = userService.getUserByid(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "设置管理员[用户名：" + user.getUsername() + "]成功");
        modelAndView.setViewName("forward:/manage/user.action");
        return modelAndView;
    }

    @RequiresRoles(value = "SuperAdmin")
    @RequestMapping(value = "/unsetUserAdmin")
    public ModelAndView unsetUserAdmin(@RequestParam("userid") int userId)
    {
        userService.unsetUserAdminNeedLog(userId);
        User user = userService.getUserByid(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "撤销管理员[用户名：" + user.getUsername() + "]成功");
        modelAndView.setViewName("forward:/manage/admin.action");
        return modelAndView;
    }
}
