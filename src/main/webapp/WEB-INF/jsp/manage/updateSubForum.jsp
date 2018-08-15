<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>修改子版块</title>
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
                <a href="/manage/subforum.action?mfid=<c:out value="${mainForum.id}"/>">子版块管理</a>
            </li>
            <li class="breadcrumb-item active">修改子版块</li>
        </ol>

        <div class="row">
            <div class="col-sm-6" style="margin-left: auto;margin-right: auto">
                <form action="/manage/updateSubForum.action" method="post">
                    <div class="form-group">
                        <label>所属主板块</label>
                        <input type="text" value="<c:out value="${subForum.mainForum.name}"/>" class="form-control" disabled>
                    </div>
                    <input type="hidden" name="sfid" value="<c:out value="${subForum.id}"/>">
                    <div class="form-group">
                        <label>子板块名</label>
                        <input type="text" name="name" value="<c:out value="${subForum.name}"/>" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>子板块简介</label>
                        <input type="text" name="info" value="<c:out value="${subForum.info}"/>" class="form-control">
                    </div>
                    <div class="form-group float-right">
                        <input type="submit" class="btn btn-primary" value="提交修改">
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