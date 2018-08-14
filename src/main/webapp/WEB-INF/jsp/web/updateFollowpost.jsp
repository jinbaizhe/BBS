<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <script src="/static/ckeditor/ckeditor.js"></script>
    <title>修改回帖</title>
</head>
<body>
<%@ include file="header.jsp"%>

<div class="container" style="margin-top: 50px" >
    <div class="row">
        <div class="col-sm-12">
            <form action="/commitUpdateFollowpost.action"  method="post" enctype="multipart/form-data" >
                <ul class="list-group">
                    <li class="list-group-item" style="background-color: #F5F5F5">
                        <h3>回复：
                            <small>
                                <a href="post.action?postid=<c:out value="${followpost.post.id}"/>">
                                    <c:out value="${followpost.post.title}"/>
                                </a>
                            </small>
                        </h3>
                    </li>
                    <li class="list-group-item">
                        <input type="hidden" name="followpostid" value="<c:out value="${followpost.id}"/>">
                        <input type="hidden" name="followpost.id" value="<c:out value="${followpost.id}"/>">
                        <h4><b>回帖内容</b></h4>
                        <textarea name="followpost.content" id="editor1" class="form-control"><c:out value="${followpost.content}"/></textarea>
                        <script>
                            // Replace the <textarea id="editor1"> with a CKEditor
                            // instance, using default configuration.
                            CKEDITOR.replace( 'editor1' );
                        </script>
                    </li>
                    <li class="list-group-item">
                        <div style="float:right;">
                            <a href='post.action?postid=<c:out value="${followpost.post.id}"></c:out>'>
                                <button type="button" class="btn btn-primary">返回</button>
                            </a>
                        </div>
                        <div style="float:right;margin-right: 20px;margin-left: 20px;">
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
