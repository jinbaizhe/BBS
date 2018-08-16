<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title><c:out value="${subForum.name}"/></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="mainforum.action">全部</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="mainforum.action?mfid=<c:out value="${mainForum.id}"/>"><c:out value="${mainForum.name}"/></a>
                </li>
                <li class="breadcrumb-item active">
                    <c:out value="${subForum.name}"/>
                </li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <h3><c:out value="${subForum.name}"/></h3>
            <p><c:out value="${subForum.info}"/></p>
            <div class="float-right mb-sm-2">
                <a class="btn btn-primary" href="/posting.action?sfid=<c:out value="${subForum.id}"/>">发帖</a>
            </div>
            <div>
                <c:choose>
                    <c:when test="${order=='postsendtime'}">
                        <a class="btn btn-primary btn-sm" href="/subforum.action?sfid=<c:out value="${subForum.id}"/>&order=lastreplytime">按最后回复时间排序</a>
                        <a class="btn btn-primary btn-sm active" href="/subforum.action?sfid=<c:out value="${subForum.id}"/>&order=postsendtime">按发帖时间排序</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-primary btn-sm active" href="/subforum.action?sfid=<c:out value="${subForum.id}"/>&order=lastreplytime">按最后回复时间排序</a>
                        <a class="btn btn-primary btn-sm" href="/subforum.action?sfid=<c:out value="${subForum.id}"/>&order=postsendtime">按发帖时间排序</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th scope="col">标题</th>
                    <th scope="col">作者</th>
                    <th scope="col">发帖时间</th>
                    <th scope="col">最后回复时间</th>
                    <th scope="col">回复/查看</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${posts}" var="post">
                    <tr>
                        <th scope="row">
                            <c:if test="${post.type==1}">
                                [<span style="color: indigo">精华</span>]
                            </c:if>
                            <c:if test="${post.top==1}">
                                [<span style="color: red">置顶</span>]
                            </c:if>
                            <a href="post.action?postid=<c:out value="${post.id}"/>">
                                <c:out value="${post.title}"/>

                            </a>
                        </th>
                        <td><a href="/user/userInfo.action?userid=<c:out value="${post.user.id}"/>"><c:out value="${post.user.username}"/></a></td>
                        <td><fmt:formatDate value="${post.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${post.lastReplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><c:out value="${post.followposts.size()}"/>/<c:out value="${post.viewNum}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <nav aria-label="Page navigation example" class="my-sm-3">
                <ul class="pagination justify-content-center">
                    <c:if test='${pager.firstPage!=true}'>
                        <li class="page-item">
                            <a class="page-link" href="subforum.action?sfid=<c:out value="${subForum.id}"/>&page=1&order=<c:out value="${order}"/>">
                                首页
                            </a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="subforum.action?sfid=<c:out value="${subForum.id}"/>&page=<c:out value="${pager.currentPage-1}"/>&order=<c:out value="${order}"/>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach items='${pager.pageList}' var="item">
                        <c:choose>
                            <c:when test="${item==pager.currentPage.toString()}">
                                <li class="page-item active">
                                    <a class="page-link" href=""><c:out value="${item}"/></a>
                                </li>
                            </c:when>
                            <c:when test="${item=='...'}">
                                <li class="page-item">
                                    <div class="page-link">
                                        <c:out value="${item}"/>
                                    </div>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="subforum.action?sfid=<c:out value="${subForum.id}"/>&page=<c:out value="${item}"/>&order=<c:out value="${order}"/>"><c:out value="${item}"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test='${pager.lastPage!=true}'>
                        <li class="page-item">
                            <a class="page-link" href="subforum.action?sfid=<c:out value="${subForum.id}"/>&page=<c:out value="${pager.currentPage+1}"/>&order=<c:out value="${order}"/>" aria-label="Next">
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

<jsp:include page="footer.jsp"/>
<%@include file="foot.jsp"%>
</body>
</html>