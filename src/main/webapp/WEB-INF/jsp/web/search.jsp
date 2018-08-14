<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2018/3/23
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fnt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <h3>搜索关键字：<strong><c:out value="searchKeyWord"/></strong></h3>
            <h4 class="my-sm-4 text-center text-danger"><c:out value="search_info"/></h4>
            <c:if test="${posts.size()==0}">
                <h4 class="my-sm-4 text-center">无搜索结果</h4>
            </c:if>
            <c:forEach items="${posts}" var="post">
                <div class="card my-sm-3">
                    <div class="card-header">
                        <span class="mx-sm-2">
                            版块：<strong><c:out value="${post.subForum.mainForum.name}"/></strong>>><strong><c:out value="${post.subForum.name}"/></strong>
                        </span>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">帖子标题：<a href="/post.action?postid=<c:out value="${post.id}"/>"><c:out value="${post.title}"/></a></h5>
                        <div class=" ml-sm-3">
                            <p class="card-text">
                                <c:out value="${post.content}"></c:out>
                            </p>
                        </div>
                    </div>
                    <div class="card-footer">
                        <span class="mx-sm-2">
                            发帖者：<c:out value="${post.user.username}"></c:out>
                        </span>
                        <span class="mx-sm-2">
                            发表时间：<fnt:formatDate value="${post.sendTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        </span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <nav aria-label="Page navigation example" class="my-sm-3">
                <ul class="pagination justify-content-center">
                    <c:if test='${pager.firstPage!=true}'>
                        <li class="page-item">
                            <a class="page-link" href="searchPosts.action?searchKeyWord=<c:out value="${searchKeyWord}"/>&page=1">
                                首页
                            </a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="searchPosts.action?searchKeyWord=<c:out value="${searchKeyWord}"/>&page=<c:out value="${pager.currentPage-1}"/>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach items='${pager.pageList}' var="item">
                        <c:choose>
                            <c:when test="${item==pager.currentPage}">
                                <li class="page-item active">
                                    <a class="page-link" href=""><c:out value="${item}"/></a>
                                </li>
                            </c:when>
                            <c:when test="${item=='...'}">
                                <li class="page-item">
                                    <div class="page-link">
                                        <c:out value="${item}"></c:out>
                                    </div>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="searchPosts.action?searchKeyWord=<c:out value="${searchKeyWord}"/>&page=<c:out value="${item}"/>"><c:out value="${item}"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test='${pager.lastPage!=true}'>
                        <li class="page-item">
                            <a class="page-link" href="searchPosts.action?searchKeyWord=<c:out value="${searchKeyWord}"/>&page=<c:out value="${pager.currentPage+1}"/>" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>
