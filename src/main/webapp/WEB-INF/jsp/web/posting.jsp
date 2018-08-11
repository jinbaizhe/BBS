<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <script src="/static/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        function addOne() {
            var fdiv=document.getElementById("filediv");
            fdiv.innerHTML+='<div class="row mt-sm-3 mb-sm-3"><div class="col-sm-11"><input type="file" name="files" class="form-control-file" /></div><div class="col-sm-1"><input type="button" value="删除" class="btn" onclick="delOne(this)" id="delBut"></div></div>';
        }
        function delOne(obj){
            obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
        }
    </script>
    <title>发帖</title>
</head>
<body>
<%@ include file="header.jsp"%>

<div class="container" style="margin-top: 50px" >
    <div class="row">
        <div class="col-sm-12">
            <form action="commitPosting.action?subforumid=<s:property value="subForum.id"></s:property>"  method="post" enctype="multipart/form-data" >
                <ul class="list-group">
                    <li class="list-group-item" style="background-color: #F5F5F5">
                        <h3>发表帖子
                            <small>
                                <a href="mainforum.action?mfid=<s:property value="subForum.mainForum.id"></s:property>">
                                    <s:property value="subForum.mainForum.name"></s:property></a>
                                    >>
                                <a href="subforum.action?sfid=<s:property value="subForum.id"></s:property>">
                                    <s:property value="subForum.name"></s:property>
                                </a>
                            </small>
                        </h3>
                    </li>
                    <li class="list-group-item">
                        <h4><b>帖子标题</b></h4>
                        <input type="text" name="post.title" value="" class="form-control" placeholder="请输入标题">
                    </li>
                    <li class="list-group-item">
                        <h4><b>帖子内容</b></h4>
                        <textarea name="post.content" id="editor1" class="form-control;"></textarea>
                        <script>
                            // Replace the <textarea id="editor1"> with a CKEditor
                            // instance, using default configuration.
                            CKEDITOR.replace( 'editor1');
                        </script>
                    </li>

                    <li class="list-group-item">
                        <div style="float:right;">
                            <a href='subforum.action?sfid=<s:property value="subForum.id"></s:property>'>
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
