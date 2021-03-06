package com.parker.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @RequestMapping("/about")
    public ModelAndView getAboutPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("web/about");
        return modelAndView;
    }

    @RequestMapping("/404")
    public ModelAndView get404Page()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("web/404");
        return modelAndView;
    }

    @RequestMapping("/test")
    public ModelAndView getTestPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("web/test");
        return modelAndView;
    }
}
