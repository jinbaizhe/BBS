package com.parker.bbs.controller;

import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.FollowpostService;
import com.parker.bbs.service.PostService;
import com.parker.bbs.service.SubForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FollowpostService followpostService;
    @Autowired
    private SubForumService subForumService;

    @RequestMapping("/post")
    public ModelAndView browserPost(@RequestParam("postid") int postid) {
        ModelAndView modelAndView = new ModelAndView();
        Post post=postService.getPostById(postid);
        int totalFollowpostsNum=followpostService.getFollowpostsNumByPostId(postid);
        List<Followpost> followposts=followpostService.getFollowpostsByPostId(postid);
        modelAndView.addObject("post", post);
        modelAndView.addObject("followposts", followposts);
        modelAndView.setViewName("web/post");
        return modelAndView;


    }

    @RequestMapping(value = "/posting", method = RequestMethod.GET)
    public ModelAndView getAddPostPage(@RequestParam("sfid") int subforumId)
    {
        SubForum subForum=subForumService.getSubForumById(subforumId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("subForum", subForum);
        modelAndView.setViewName("web/posting");
        return modelAndView;
    }

    @RequestMapping(value = "/posting", method = RequestMethod.POST)
    public ModelAndView commitAddPost(@SessionAttribute("user") User user, Post post, @RequestParam("sfid") int subforumId)
    {
//        if(post==null||post.getTitle().equals("")||post.getContent().equals(""))
//            return ERROR;
        post.setSubForum(subForumService.getSubForumById(subforumId));
        post.setUser(user);
        postService.insertPost(post);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "帖子发表成功");
        modelAndView.addObject("message", "发表成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.GET)
    public ModelAndView getUpdatePostPage(@RequestParam("postid") int postId)
    {
        Post post=postService.getPostById(postId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", post);
        modelAndView.setViewName("web/updatePost");
        return modelAndView;
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public ModelAndView commitUpdatePost(Post post)
    {
        postService.updatePost(post);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "修改成功");
        modelAndView.addObject("title", "帖子修改成功");
        modelAndView.setViewName("web/operationStatus");
        return modelAndView;
    }

    public ModelAndView commitDeletePost(@RequestParam("postid") int postId)
    {
        Post post=postService.getPostById(postId);
        postService.deletePost(post);
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
