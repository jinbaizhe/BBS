<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>修改主板块</title>
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
            <div class="col-sm-6" style="margin-left: auto;margin-right: auto">
                <form action="/manage/commitupdatemainforum.action" method="post">
                    <input type="hidden" name="mainForum.id" value="<s:property value="mainForum.id"></s:property>">
                    <div class="form-group">
                        <label>主板块名</label>
                        <input type="text" name="mainForum.name" value="<s:property value="mainForum.name"></s:property>" placeholder="请输入版块名" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>主板块简介</label>
                        <input type="text" name="mainForum.info" value="<s:property value="mainForum.info"></s:property>" placeholder="请输入版块简介" class="form-control">
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