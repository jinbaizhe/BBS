//package com.parker.bbs.controller;
//
//import com.parker.bbs.pojo.Post;
//import com.parker.bbs.service.PostService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class PostController {
//    @Autowired
//    private PostService postService;
//
//    @RequestMapping("/post")
//    public String browserPost(@RequestParam("postid") int postid) {
//        Post post=postService.getPostById(postid);
//        int totalFollowpostsNum=followpostService.getFollowpostsNumByPostId(postid);
//
//        followposts=followpostService.getFollowpostsByPostId(postid,page, ShowFollowpostsPerPageNum,order);
//
//        Pager pager=new Pager(page,ShowFollowpostsPerPageNum,totalFollowpostsNum);
//
//        Map session=ActionContext.getContext().getSession();
//        User user=(User) session.get("user");
//        if(user!=null)
//            collection=userService.getCollection(user.getId(),postid);
//        return SUCCESS;
//    }
//
//    public String getAddPostPage()
//    {
//        if(subforumid==0)
//            return ERROR;
//        subForum=subForumService.getSubForumById(subforumid);
//        return SUCCESS;
//    }
//
//    public String commitAddPost()
//    {
//        if(post==null||post.getTitle().equals("")||post.getContent().equals(""))
//            return ERROR;
//        post.setSubForum(subForumService.getSubForumById(subforumid));
//        Map session=ActionContext.getContext().getSession();
//        post.setUser((User)session.get("user"));
//        postService.createPost(post);
//        return SUCCESS;
//    }
//
//    public String getUpdatePostPage()
//    {
//        post=postService.getPostById(postid);
//        return SUCCESS;
//    }
//
//    public String commitUpdatePost()
//    {
//        postService.updatePost(post);
//        post=postService.getPostById(post.getId());
//        return SUCCESS;
//    }
//
//    public String commitDeletePost()
//    {
//        post=postService.getPostById(postid);
//        postService.deletePost(post);
//        return SUCCESS;
//    }
//
//    public String searchPosts()
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
//}
