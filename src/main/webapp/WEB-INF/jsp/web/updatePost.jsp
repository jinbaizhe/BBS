<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <script src="/static/ckeditor/ckeditor.js"></script>
    <title>修改帖子</title>
</head>
<body>
<%@ include file="header.jsp"%>

<div class="container" style="margin-top: 50px" >
    <div class="row">
        <div class="col-sm-12">
            <form action="/updatePost.action"  method="post">
                <ul class="list-group">
                    <li class="list-group-item" style="background-color: #F5F5F5">
                        <h3>修改帖子
                            <small>
                                <a href="mainforum.action?mfid=<c:out value="${post.subForum.mainForum.id}"/>">
                                    <c:out value="post.subForum.mainForum.name"></c:out></a>
                                    >>
                                <a href="subforum.action?sfid=<c:out value="${post.subForum.id}"/>">
                                    <c:out value="${post.subForum.name}"/>
                                </a>
                            </small>
                        </h3>
                    </li>
                    <input type="hidden" name="postid" value="<c:out value="${post.id}"/>">
                    <li class="list-group-item">
                        <h4><b>帖子标题</b></h4>
                        <input type="text" name="title" value="<c:out value="${post.title}"/>" class="form-control" placeholder="请输入标题">
                    </li>
                    <li class="list-group-item">
                        <h4><b>帖子内容</b></h4>
                        <textarea name="content" id="editor1" class="form-control"><c:out value="${post.content}"/></textarea>
                        <script>
                            // Replace the <textarea id="editor1"> with a CKEditor
                            // instance, using default configuration.
                            CKEDITOR.replace( 'editor1' );
                        </script>
                    </li>
                    <li class="list-group-item">
                        <div style="float:right;">
                            <a href='post.action?postid=<c:out value="${post.id}"/>'>
                                <button type="button" class="btn btn-primary">返回</button>
                            </a>
                        </div>
                        <div style="float:right;margin-right: 20px;margin-left: 20px;">
                            <%--<input type="hidden" name="sub_forumid" value="">--%>
                            <input type="submit" class="btn btn-primary" value="发表">
                        </div>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<%@include file="foot.jsp"%>
</body>
</html>
