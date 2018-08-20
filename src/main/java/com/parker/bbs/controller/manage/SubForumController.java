package com.parker.bbs.controller.manage;

import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.service.MainForumService;
import com.parker.bbs.service.SubForumService;
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

@Controller("subForumManageController")
@RequestMapping("/manage")
public class SubForumController {
    @Autowired
    private SubForumService subForumService;
    @Autowired
    private MainForumService mainForumService;

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/subForum")
    public ModelAndView getSubForumsPage(@RequestParam("mfid")int mainForumId)
    {
        MainForum mainForum = mainForumService.getMainForumById(mainForumId);
        List<SubForum> subForums = subForumService.getSubForumsByMainForumId(mainForumId);
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("mainForum", mainForum);
        modelAndView.addObject("subForums", subForums);
        modelAndView.setViewName("manage/subForum");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/addSubForum", method = RequestMethod.GET)
    public ModelAndView getAddSubForumPage(@RequestParam("mfid") int mainForumId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        MainForum mainForum = mainForumService.getMainForumById(mainForumId);
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("mainForum", mainForum);
        modelAndView.setViewName("manage/addSubForum");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/addSubForum",method = RequestMethod.POST)
    public ModelAndView commitAddSubForum(@RequestParam("mfid") int mainForumId, @RequestParam("name") String name, @RequestParam("info") String info)
    {
        subForumService.insertSubForumNeedLog(mainForumId, name, info);
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("title", "子版块添加成功");
        modelAndView.addObject("message", "添加成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/updateSubForum",method = RequestMethod.GET)
    public ModelAndView getUpdateSubForumPage(@RequestParam("sfid") int subForumId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        SubForum subForum = subForumService.getSubForumById(subForumId);
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("subForum", subForum);
        modelAndView.setViewName("manage/updateSubForum");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/updateSubForum",method = RequestMethod.POST)
    public ModelAndView commitUpdateSubForum(@RequestParam("sfid") int subForumId, @RequestParam("name") String name, @RequestParam("info") String info)
    {
        subForumService.updateSubForumNeedLog(subForumId, name, info);
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("title", "子版块修改成功");
        modelAndView.addObject("message", "修改成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresRoles(value = {"Admin","SuperAdmin"}, logical = Logical.OR)
    @RequestMapping("/deleteSubForum")
    public ModelAndView commitDeleteSubForum(@RequestParam("sfid") int subForumId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL, session);
        subForumService.deleteSubForumNeedLog(subForumId);
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("title", "子版块删除成功");
        modelAndView.addObject("message", "删除成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }
}
