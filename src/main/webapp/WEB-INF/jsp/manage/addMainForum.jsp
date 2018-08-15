<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>添加主板块</title>
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
            <li class="breadcrumb-item active">
                <a href="/manage/mainForum.action">主版块管理</a>
            </li>
        </ol>
        <div class="row">
            <div class="col-sm-6" style="margin-left: auto;margin-right: auto">
                <form action="/manage/addMainForum.action" method="post">
                    <div class="form-group">
                        <label>主板块名</label>
                        <input type="text" name="name" placeholder="请输入主版块名" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>主板块简介</label>
                        <input type="text" name="info" placeholder="请输入主版块简介" class="form-control">
                    </div>
                    <div class="form-group float-right">
                        <input type="submit" class="btn btn-primary" value="提交">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/jsp/manage/footer.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/manage/foot.jsp"%>
</body>
</html>