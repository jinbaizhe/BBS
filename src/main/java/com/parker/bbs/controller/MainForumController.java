package com.parker.bbs.controller;

import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.service.MainForumService;
import com.parker.bbs.service.SubForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainForumController {
    @Autowired
    private MainForumService mainForumService;
    @Autowired
    private SubForumService subForumService;

    @RequestMapping("/mainforum")
    public ModelAndView browserMainForum(@RequestParam(value = "mfid",defaultValue = "-1")int mainForumId)
    {
        ModelAndView modelAndView = new ModelAndView();
        Map<MainForum,List<SubForum>> map = new HashMap<>();
        if (mainForumId != -1)
        {
            MainForum mainForum = mainForumService.getMainForumById(mainForumId);
            map.put(mainForum, subForumService.getSubForumsByMainForumId(mainForumId));
            modelAndView.addObject("mainForum", mainForum);
            modelAndView.setViewName("web/mainForum");
        }
        else {
            List<MainForum> mainForums=mainForumService.getAllMainForums();
            for(MainForum mainForum:mainForums)
            {
                int id=mainForum.getId();
                map.put(mainForum,subForumService.getSubForumsByMainForumId(id));
            }
            modelAndView.setViewName("web/allMainForum");
        }
        modelAndView.addObject("mainForumMap",map);
        return modelAndView;
    }

    @RequestMapping("/getMainForumsData")
    @ResponseBody
    public List<MainForum> getMainForumsToJSON() {
        List<MainForum> mainForums = mainForumService.getAllMainForums();
        return mainForums;
    }
}
