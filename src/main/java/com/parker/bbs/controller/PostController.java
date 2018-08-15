package com.parker.bbs.controller;

import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.FollowpostService;
import com.parker.bbs.service.PostService;
import com.parker.bbs.service.SubForumService;
import com.parker.bbs.util.Pager;
import com.parker.bbs.util.Util;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {

    @Value("#{configProperties['followpostsNumPerPage']}")
    private int followpostsNumPerPage;

    @Autowired
    private PostService postService;
    @Autowired
    private FollowpostService followpostService;
    @Autowired
    private SubForumService subForumService;

    @RequestMapping("/post")
    public ModelAndView browserPost(@RequestParam("postid") int postid, @RequestParam(value = "page", defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView();
        Post post=postService.getPostById(postid);
        int totalFollowpostsNum=followpostService.getFollowpostsNumByPostId(postid);
        List<Followpost> followposts=followpostService.getFollowpostsByPostId(postid, page, followpostsNumPerPage,"asc");
        Pager pager = new Pager(page,followpostsNumPerPage,totalFollowpostsNum);
        List pageList = pager.getPageList();
        modelAndView.addObject("post", post);
        modelAndView.addObject("followposts", followposts);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("pageList", pageList);
        modelAndView.setViewName("web/post");
        return modelAndView;


    }

    @RequiresUser
    @RequestMapping(value = "/posting", method = RequestMethod.GET)
    public ModelAndView getAddPostPage(@RequestParam("sfid") int subforumId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        Util.addReferURL(referURL,session);
        SubForum subForum=subForumService.getSubForumById(subforumId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("subForum", subForum);
        modelAndView.setViewName("web/posting");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/posting", method = RequestMethod.POST)
    public ModelAndView commitAddPost(@SessionAttribute(value = "user", required = false) User user, @RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("sfid") int subforumId)
    {
        postService.insertPost(title, content, subforumId, user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "帖子发表成功");
        modelAndView.addObject("message", "发表成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/updatePost", method = RequestMethod.GET)
    public ModelAndView getUpdatePostPage(@RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        Util.addReferURL(referURL, session);
        Post post=postService.getPostById(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", post);
        modelAndView.setViewName("web/updatePost");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public ModelAndView commitUpdatePost(@RequestParam("postid") int postId, @RequestParam("title") String title,@RequestParam("content") String content)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        postService.updatePost(postId, title, content);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "修改成功");
        modelAndView.addObject("title", "帖子修改成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequiresUser
    @RequestMapping("/deletePost")
    public ModelAndView commitDeletePost(@RequestParam("postid") int postId, @RequestHeader(value = "Referer", required = false)String referURL, HttpSession session)
    {
        // TODO: 2018/8/14 还需加入用户权限的判断
        Util.addReferURL(referURL, session);
        postService.deletePost(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "删除成功");
        modelAndView.addObject("title", "帖子删除成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

//    public ModelAndView searchPosts()
//    {
//        //读取web.xml获取ShowPostsPerPageNum参数
//        ServletContext servletContext =ServletActionContext.getServletContext();
//        final int ShowSearchResultsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowSearchResultsPerPageNum"));
//        if(searchKeyWord.equals(""))
//        {
//            search_info="请输入关键字";
//            return SUCCESS;
//        }
//        Map request=(Map)ActionContext.getContext().get("request");
//        posts=postService.getSearchResults(searchKeyWord,page,ShowSearchResultsPerPageNum,search_order);
//        Pager pager=new Pager(page,ShowSearchResultsPerPageNum,postService.getSearchResultNum(searchKeyWord,search_order));
//        request.put("pager",pager);
//        return SUCCESS;
//    }
}
