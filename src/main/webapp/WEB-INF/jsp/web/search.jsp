<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2018/3/23
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>搜索</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-6" style="margin-left: auto;margin-right: auto">
            <h3>搜索关键字：<strong><s:property value="searchKeyWord"></s:property></strong></h3>
            <h4 class="my-sm-4 text-center text-danger"><s:property value="search_info"></s:property></h4>
            <s:if test="posts.size==0">
                <h4 class="my-sm-4 text-center">无搜索结果</h4>
            </s:if>
            <s:iterator value="posts" var="post">
                <div class="card my-sm-3">
                    <div class="card-header">
                        <span class="mx-sm-2">
                            版块：<strong><s:property value="#post.subForum.mainForum.name"></s:property></strong>>><strong><s:property value="#post.subForum.name"></s:property></strong>
                        </span>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">帖子标题：<a href="/post.action?postid=<s:property value="#post.id"></s:property>"><s:property value="#post.title"></s:property></a></h5>
                        <div class=" ml-sm-3">
                            <p class="card-text">
                                <s:property value="#post.content" escapeHtml="false"></s:property>
                            </p>
                        </div>
                    </div>
                    <div class="card-footer">
                        <span class="mx-sm-2">
                            发帖者：<s:property value="#post.user.username"></s:property>
                        </span>
                        <span class="mx-sm-2">
                            发表时间：<s:date name="#post.sendTime" format="yyyy-MM-dd hh:mm:ss"></s:date>
                        </span>
                    </div>
                </div>
            </s:iterator>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <nav aria-label="Page navigation example" class="my-sm-3">
                <ul class="pagination justify-content-center">
                    <s:if test='#request.pager.isFirstPage!=true'>
                        <li class="page-item">
                            <a class="page-link" href="searchPosts.action?searchKeyWord=<s:property value="searchKeyWord"></s:property>&page=1">
                                首页
                            </a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="searchPosts.action?searchKeyWord=<s:property value="searchKeyWord"></s:property>&page=<s:property value="#request.pager.getCurrentPage-1"></s:property>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </s:if>
                    <s:iterator value='#request.pager.getPageList' var="item">
                        <s:if test="#item==#request.pager.getCurrentPage">
                            <li class="page-item active">
                                <a class="page-link" href=""><s:property value="#item"></s:property></a>
                            </li>
                        </s:if>
                        <s:elseif test="#item=='...'">
                            <li class="page-item">
                                <div class="page-link">
                                    <s:property value="#item"></s:property>
                                </div>
                            </li>
                        </s:elseif>
                        <s:else>
                            <li class="page-item">
                                <a class="page-link" href="searchPosts.action?searchKeyWord=<s:property value="searchKeyWord"></s:property>&page=<s:property value="#item"></s:property>"><s:property value="#item"></s:property></a>
                            </li>
                        </s:else>
                    </s:iterator>
                    <s:if test='#request.pager.isLastPage!=true'>
                        <li class="page-item">
                            <a class="page-link" href="searchPosts.action?searchKeyWord=<s:property value="searchKeyWord"></s:property>&page=<s:property value="#request.pager.getCurrentPage+1"></s:property>" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </s:if>
                </ul>
            </nav>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>
