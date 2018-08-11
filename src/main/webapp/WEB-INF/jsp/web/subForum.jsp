<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <%--<title><c:out value="${subForum.name}"></c:out></title>--%>
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
                    <a href="mainforum.action?mfid=<c:out value="${mainForum.id}"></c:out>"><c:out value="${mainForum.name}"></c:out></a>
                </li>
                <li class="breadcrumb-item active">
                    <c:out value="${subForum.name}"></c:out>
                </li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <h3><c:out value="${subForum.name}"></c:out></h3>
            <p><c:out value="${subForum.info}"></c:out></p>
            <div class="float-right mb-sm-2">
                <a class="btn btn-primary" href="/posting.action?subforumid=<c:out value="${subForum.id}"></c:out>">发帖</a>
            </div>
            <div>
                <c:choose>
                    <c:when test="order=='postsend'">
                        <a class="btn btn-primary btn-sm" href="/subforum.action?sfid=<c:out value="${subForum.id}"></c:out>&order=lastfollowpost">按最后回复时间排序</a>
                        <a class="btn btn-primary btn-sm active" href="/subforum.action?sfid=<c:out value="${subForum.id}"></c:out>&order=postsend">按发帖时间排序</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-primary btn-sm active" href="/subforum.action?sfid=<c:out value="${subForum.id}"></c:out>&order=lastfollowpost">按最后回复时间排序</a>
                        <a class="btn btn-primary btn-sm" href="/subforum.action?sfid=<c:out value="${subForum.id}"></c:out>&order=postsend">按发帖时间排序</a>
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
                    <c:set var="lastfollowpostsendtime" value="${temp[1]}"></c:set>
                    <tr>
                        <th scope="row">
                            <c:if test="${post.type==1}">
                                [<span style="color: indigo">精华</span>]
                            </c:if>
                            <c:if test="${post.top==1}">
                                [<span style="color: red">置顶</span>]
                            </c:if>
                            <a href="post.action?postid=<c:out value="${post.id}"></c:out>">
                                <c:out value="${post.title}"></c:out>

                            </a>
                        </th>
                        <td><c:out value="${post.user.username}"></c:out></td>
                        <td><fmt:formatDate value="${post.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                        <%--<td><fmt:formatDate value="${lastfollowpostsendtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>--%>
                        <%--<td><c:out value="${post.followposts.size}" default="0"></c:out>/<c:out value="${post.viewNum}"></c:out></td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <%--<div class="col-sm-12">--%>
            <%--<nav aria-label="Page navigation example" class="my-sm-3">--%>
                <%--<ul class="pagination justify-content-center">--%>
                    <%--<c:if test='#request.pager.isFirstPage!=true'>--%>
                        <%--<li class="page-item">--%>
                            <%--<a class="page-link" href="subforum.action?sfid=<c:out value="${subForum.id}"></c:out>&page=1">--%>
                                <%--首页--%>
                            <%--</a>--%>
                        <%--</li>--%>
                        <%--<li class="page-item">--%>
                            <%--<a class="page-link" href="subforum.action?sfid=<c:out value="${subForum.id}"></c:out>&page=<c:out value="${request.pager.getCurrentPage-1}"></c:out>" aria-label="Previous">--%>
                                <%--<span aria-hidden="true">&laquo;</span>--%>
                                <%--<span class="sr-only">Previous</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                    <%--</c:if>--%>
                    <%--<c:forEach items='#request.pager.getPageList' var="item">--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="#item==#request.pager.getCurrentPage">--%>
                                <%--<li class="page-item active">--%>
                                    <%--<a class="page-link" href=""><c:out value="${item}"></c:out></a>--%>
                                <%--</li>--%>
                            <%--</c:when>--%>
                            <%--<c:when test="#item=='...'">--%>
                                <%--<li class="page-item">--%>
                                    <%--<div class="page-link">--%>
                                        <%--<c:out value="${item}"></c:out>--%>
                                    <%--</div>--%>
                                <%--</li>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--<li class="page-item">--%>
                                    <%--<a class="page-link" href="subforum.action?sfid=<c:out value="${subForum.id}"></c:out>&page=<c:out value="${item}"></c:out>"><c:out value="${item}"></c:out></a>--%>
                                <%--</li>--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</c:forEach>--%>
                    <%--<c:if test='#request.pager.isLastPage!=true'>--%>
                        <%--<li class="page-item">--%>
                            <%--<a class="page-link" href="subforum.action?sfid=<c:out value="${subForum.id}"></c:out>&page=<c:out value="${request.pager.getCurrentPage+1}"></c:out>" aria-label="Next">--%>
                                <%--<span aria-hidden="true">&raquo;</span>--%>
                                <%--<span class="sr-only">Next</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                    <%--</c:if>--%>
                <%--</ul>--%>
            <%--</nav>--%>
        <%--</div>--%>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<%@include file="foot.jsp"%>
</body>
</html>