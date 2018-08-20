package com.parker.bbs.controller.manage;

import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.service.MainForumService;
import com.parker.bbs.util.Util;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("mainForumManageController")
@RequestMapping("/manage")
public class MainForumController {
    @Autowired
    private MainForumService mainForumService;

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/index")
    public ModelAndView getIndexPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manage/index");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/mainForum")
    public ModelAndView getAllMainForumPage()
    {
        List<MainForum> mainForums=mainForumService.getAllMainForums();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mainForums", mainForums);
        modelAndView.setViewName("manage/mainForum");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/addMainForum",method = RequestMethod.GET)
    public ModelAndView getAddMainForumPage(@RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manage/addMainForum");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/addMainForum",method = RequestMethod.POST)
    public ModelAndView commitAddMainForum(@RequestParam("name") String name, @RequestParam("info") String info)
    {
        mainForumService.insertMainForumNeedLog(name, info);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "主版块添加成功");
        modelAndView.addObject("message", "添加成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/updateMainForum")
    public ModelAndView getUpdateMainForumPage(@RequestParam("mfid")int mainForumId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        MainForum mainForum = mainForumService.getMainForumById(mainForumId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mainForum", mainForum);
        modelAndView.setViewName("manage/updateMainForum");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/updateMainForum", method = RequestMethod.POST)
    public ModelAndView commitUpdateMainForum(@RequestParam("mfid") int mainForumId, @RequestParam("name") String name, @RequestParam("info") String info)
    {
        mainForumService.updateMainForumNeedLog(mainForumId, name, info);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "主版块修改成功");
        modelAndView.addObject("message", "修改成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/deleteMainForum")
    public ModelAndView commitDeleteMainForum(@RequestParam("mfid") int mainForumId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        mainForumService.deleteMainForumNeedLog(mainForumId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "主版块删除成功");
        modelAndView.addObject("message", "删除成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }


}
