<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>主板块管理</title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<%@ include file="slideBar.jsp"%>

<div class="content-wrapper">

    <div class="container">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/manage/index.action">后台管理系统</a>
            </li>
            <li class="breadcrumb-item active">主版块管理</li>
        </ol>

        <div class="row">
            <div class="col-sm-3">
                <a class="btn btn-primary" href="/manage/addMainForum.action">增加主版块</a>
            </div>
        </div>

        <div class="row">
            <c:forEach items="${mainForums}" var="forum" varStatus="forumStatus">
                <div class="col-sm-4 my-sm-3">
                    <div class="card">
                        <div class="card-header"><c:out value="${forum.name}"/></div>
                        <div class="card-body">
                            <ul><c:out value="${forum.info}"/></ul>
                            <ul>
                                <a class="btn" href="/manage/subForum.action?mfid=<c:out value="${forum.id}"/>">查看子版块</a>
                                <a class="btn" href="/manage/updateMainForum.action?mfid=<c:out value="${forum.id}"/>">修改版块</a>
                                <a class="btn" href="/manage/deleteMainForum.action?mfid=<c:out value="${forum.id}"/>">删除版块</a>
                            </ul>
                        </div>
                    </div>
                </div>
                <c:if test="${(forumStatus.index+1)%3==0}">
                </div>
                <div class="row">
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/manage/footer.jsp"%>
<%@include file="/WEB-INF/jsp/manage/foot.jsp"%>
</body>
</html>