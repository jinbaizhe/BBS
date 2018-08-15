<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>子版块管理</title>
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
            <li class="breadcrumb-item">
                <a href="/manage/mainForum.action">主板块管理</a>
            </li>
            <li class="breadcrumb-item active">子版块管理</li>
        </ol>

        <div class="row">
            <div class="col-sm-12">
                <h4 class="text-center">所属主板块：<c:out value="${mainForum.name}"/></h4>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <a class="btn btn-primary" href="/manage/addSubForum.action?mfid=<c:out value="${mainForum.id}"/>">增加子版块</a>
            </div>
        </div>

        <div class="row">
            <c:if test="${subForums.size()==0}">
                <div class="col-sm-12">
                    <h5>无子版块</h5>
                </div>
            </c:if>
            <c:forEach items="${subForums}" var="forum" varStatus="forumStatus">
            <div class="col-sm-4 my-sm-3">
                <div class="card">
                    <div class="card-header"><c:out value="${forum.name}"/></div>
                    <div class="card-body">
                        <ul><c:out value="${forum.info}"/></ul>
                        <ul>
                            <a class="btn" href="/manage/updateSubForum.action?sfid=<c:out value="${forum.id}"/>">修改子版块</a>
                            <a class="btn" href="/manage/deleteSubForum.action?sfid=<c:out value="${forum.id}"/>">删除子版块</a>
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


<%@include file="/WEB-INF/jsp/manage/footer.jsp"%>
<%@include file="/WEB-INF/jsp/manage/foot.jsp"%>
</body>
</html>