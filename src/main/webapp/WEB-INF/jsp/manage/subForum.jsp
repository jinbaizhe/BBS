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
                <a href="/manage/mainforum.action">主板块管理</a>
            </li>
            <li class="breadcrumb-item active">子版块管理</li>
        </ol>

        <div class="row">
            <div class="col-sm-3">
                <a class="btn btn-primary" href="/manage/addsubforum.action?mfid=<s:property value="mfid"></s:property>">增加子版块</a>
            </div>
        </div>

        <div class="row">
            <s:iterator value="subForums" var="forum" status="forumStatus">
            <div class="col-sm-4 my-sm-3">
                <div class="card">
                    <div class="card-header"><s:property value="#forum.name"></s:property></div>
                    <div class="card-body">
                        <ul><s:property value="#forum.info"></s:property></ul>
                        <ul>
                            <a class="btn" href="/manage/updatesubforum.action?sfid=<s:property value="#forum.id"></s:property>">修改子版块</a>
                            <a class="btn" href="/manage/deletesubforum.action?mfid=<s:property value="mfid"></s:property>&sfid=<s:property value="#forum.id"></s:property>">删除子版块</a>
                        </ul>
                    </div>
                </div>
            </div>
            <s:if test="(#forumStatus.index+1)%3==0">
                </div>
                <div class="row">
            </s:if>
            </s:iterator>
        </div>



    </div>


<%@include file="/WEB-INF/manage/footer.jsp"%>
<%@include file="/WEB-INF/manage/foot.jsp"%>
</body>
</html>