<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>添加子版块</title>
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
                <a href="/manage/mainForum.action">主版块管理</a>
            </li>
            <li class="breadcrumb-item">
                <a href="/manage/subForum.action?mfid=<c:out value="${mainForum.id}"/>">子版块管理</a>
            </li>
            <li class="breadcrumb-item active">添加子版块</li>
        </ol>


        <div class="row">
            <div class="col-sm-6" style="margin-left: auto;margin-right: auto">
                <form action="/manage/addSubForum.action" method="post">
                    <div class="form-group">
                        <label>所属主板块</label>
                        <input type="text" value="<c:out value="${mainForum.name}"></c:out>" class="form-control" disabled>
                        <input type="hidden" name="mfid" value="<c:out value="${mainForum.id}"></c:out>">
                    </div>
                    <div class="form-group">
                        <label>子板块名</label>
                        <input type="text" name="name" placeholder="请输入子版块名" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>子板块简介</label>
                        <input type="text" name="info" placeholder="请输入子版块简介" class="form-control">
                    </div>
                    <div class="form-group float-right">
                        <input type="submit" class="btn btn-primary" value="提交">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/manage/footer.jsp"%>
<%@include file="/WEB-INF/jsp/manage/foot.jsp"%>
</body>
</html>