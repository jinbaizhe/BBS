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
}
