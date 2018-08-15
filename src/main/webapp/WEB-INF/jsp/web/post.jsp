<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <link rel="stylesheet" href="/static/css/post-detail.css" >
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
    <title><c:out value="${post.title}"/></title>
</head>
<body>
<%@ include file="header.jsp"%>
<div style="height:auto">
    <div class="container" style="margin-top: 50px" >
        <div class="row">
            <div class="col-sm-12">
                <!-- Breadcrumbs-->
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="mainforum.action">全部</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="mainforum.action?mfid=<c:out value="${post.subForum.mainForum.id}"/>"><c:out value="${post.subForum.mainForum.name}"/></a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="subforum.action?sfid=<c:out value="${post.subForum.id}"/>"><c:out value="${post.subForum.name}"/></a>
                    </li>
                    <li class="breadcrumb-item active">
                        <c:out value="${post.title}"></c:out>
                    </li>
                </ol>
            </div>
        </div>
        <div class="row my-sm-1">
            <div class="col-sm-12">
                <div class="float-right">
                    <c:choose>
                        <c:when test="order=='asc'">
                            <a class="btn btn-primary btn-sm active" href="/post.action?postid=<c:out value="${post.id}"/>&order=asc">按回帖时间升序</a>
                            <a class="btn btn-primary btn-sm" href="/post.action?postid=<c:out value="${post.id}"/>&order=desc">按回帖时间降序</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-primary btn-sm" href="/post.action?postid=<c:out value="${post.id}"/>&order=asc">按回帖时间升序</a>
                            <a class="btn btn-primary btn-sm active" href="/post.action?postid=<c:out value="${post.id}"/>&order=desc">按回帖时间降序</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!-- 帖子内容 -->
        <div class="row">
            <div class="col-sm-2" style="padding-right: 0px;">
                <div class="post-head">
                    <div class="text-center">
                        <div>
                            <a href="/user/userInfo.action?userid=<c:out value="${post.user.id}"/>">
                                <c:choose>
                                    <c:when test='${post.user.avatar!=null}'>
                                        <img  alt="" class="img-responsive img-circle" src="/getPicture.action?id=<c:out value="${post.user.picture.id}"/>"
                                              style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>

                                    </c:when>
                                    <c:otherwise>
                                        <img  alt="" class="img-responsive img-circle" src="/static/default.jpg"
                                              style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </div>
                        <div>
                            <span class="badge" style="background: #f1c40f;margin-top: 5px">发帖者:<c:out value="${post.user.username}"/></span>
                        </div>
                        <div>
                            <span class="badge" style="background: #2ecc71;margin-top: 5px">性别:<c:out value="${post.user.sex}"/></span>
                        </div>
                        <div>
                            <span class="badge" style="background: #ff6927;margin-top: 5px">论坛等级:LV<c:out value="${post.user.level}"/></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-10" style="padding-left: 0px">
                <div class="post-content">
                    <div class="post-title">
                        <h2 style="margin-left:20px;color:black;"><c:out value="${post.title}"/></h2>
                        <div style="margin-left:20px" >
                            <span class="glyphicon glyphicon-comment"></span>
                            <%--回复数:<c:out value="${post.followposts.size()}"/>--%>
                            &nbsp;|&nbsp;
                            浏览数:<c:out value="${post.viewNum}"/>
                            &nbsp;|&nbsp;
                            发表于:<fmt:formatDate value="${post.sendTime}"  pattern="yyyy-MM-dd HH:mm:ss"/>
                            <strong style="float:right;margin-right:10px">
                                <span class="badge" style="background: #ff6927;width: 50px;">楼主</span>
                            </strong>
                            <c:if test="${post.user.id==sessionScope.user.id}">
                                <%--还需要加入管理员删除的权限验证--%>
                                <a style="float:right;margin-right: 20px;" href="/deletePost.action?postid=<c:out value="${post.id}"/>">删除</a>
                            </c:if>
                            <c:if test="${post.user.id==sessionScope.user.id}">
                                <a style="float:right;margin-right: 20px;" href="/updatePost.action?postid=<c:out value="${post.id}"/>">编辑</a>
                            </c:if>

                            <c:choose>
                                <c:when test="collection!=null">
                                    <a style="float:right;margin-right: 20px;" href="/unstarPost.action?postid=<c:out value="${post.id}"/>">取消收藏</a>
                                </c:when>
                                <c:otherwise>
                                    <a style="float:right;margin-right: 20px;" href="/starPost.action?postid=<c:out value="${post.id}"/>">收藏</a>
                                </c:otherwise>
                            </c:choose>

                            <shiro:hasAnyRoles name="Admin,SuperAdmin">
                                <c:choose>
                                    <c:when test="${post.top==0}">
                                        <a style="float:right;margin-right: 20px;" href="/manage/setTop?postid=<c:out value="${post.id}"/>">置顶</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a style="float:right;margin-right: 20px;" href="/manage/unsetTop?postid=<c:out value="${post.id}"></c:out>">取消置顶</a>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${post.type==0}">
                                        <a style="float:right;margin-right: 20px;" href="/manage/setPostEssential?postid=<c:out value="${post.id}"/>">精华</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a style="float:right;margin-right: 20px;" href="/manage/unsetPostEssential?postid=<c:out value="${post.id}"/>">取消精华</a>
                                    </c:otherwise>
                                </c:choose>
                            </shiro:hasAnyRoles>
                        </div>
                    </div>
                    <div style="margin: 20px">
                        <c:out value="${post.content}" escapeXml="false"/>
                    </div>
                </div>
            </div>
        </div>

        <c:forEach items="${followposts}" var="followpost" varStatus="st">
            <!-- 回复内容 -->
            <div class="row" style="margin-top: 5px">
                <div class="col-sm-2" style="padding-right: 0px;">
                    <div class="reply-head">
                        <div class="text-center">
                            <div>
                                <a href="/user/userInfo.action?userid=<c:out value="${followpost.user.id}"/>">
                                    <c:choose>
                                        <c:when test='${followpost.user.avatar!=null}'>
                                            <img  alt="" class="img-responsive img-circle" src="/getPicture.action?id=<c:out value="${followpost.user.picture.id}"/>"
                                                  style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img  alt="" class="img-responsive img-circle" src="/static/default.jpg"
                                                  style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                            <div>
                                <span class="badge" style="background: #f1c40f;margin-top: 5px">回帖者:<c:out value="${followpost.user.username}"/></span>
                            </div>
                            <div>
                                <span class="badge" style="background: #2ecc71;margin-top: 5px">性别:<c:out value="${followpost.user.sex}"/></span>
                            </div>
                            <div>
                                <span class="badge" style="background: #ff6927;margin-top: 5px">论坛等级:LV<c:out value="${followpost.user.level}"/></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-10" style="padding-left: 0px;">
                    <div class="reply-content">
                        <div class="reply-time">
                            <span style="color: gray">回复于:<fmt:formatDate value="${followpost.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <div style="float:right;margin-right:10px">
                                <span class="badge" style="float:right;margin-right:10px;background: #4b9ded;width: 50px;"><c:out value="${st.index}"/>楼</span>
                            </div>
                            <shiro:user>
                                <c:if test="${followpost.user.id==sessionScope.user.id}">
                                    <%--还需增加管理员权限--%>
                                    <a style="float:right;margin-right: 20px;" href="deleteFollowpost.action?followpostid=<c:out value="${followpost.id}"/>">删除</a>

                                </c:if>
                                <c:if test="${followpost.user.id==sessionScope.user.id}">
                                    <a style="float:right;margin-right: 20px;" href="updateFollowpost.action?followpostid=<c:out value="${followpost.id}"/>">编辑</a>
                                </c:if>
                            </shiro:user>
                        </div>
                        <div style="margin: 20px;">
                                <c:out value="${followpost.content}" escapeXml="false"/>
                                <%--<c:forEach items="${followpost.followpostPictures}" var="item">--%>
                                    <%--<img src="getPicture.action?id=<c:out value="${item.picture.id}"/>" width="200" height="150" alt="无法显示图片">--%>
                                <%--</c:forEach>--%>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <div class="row">
            <div class="col-sm-12">
                <nav aria-label="Page navigation example" class="my-sm-3">
                    <ul class="pagination justify-content-center">
                        <c:if test='${pager.firstPage!=true}'>
                            <li class="page-item">
                                <a class="page-link" href="post.action?postid=<c:out value="${post.id}"/>&page=1">
                                    首页
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link"
                                   href="post.action?postid=<c:out value="${post.id}"/>&page=<c:out value="${pager.getCurrentPage()-1}"/>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach items='${pager.pageList}' var="item">
                            <c:choose>
                                <c:when test="${item == pager.currentPage.toString()}">
                                    <li class="page-item active">
                                        <a class="page-link" href=""><c:out value="${item}"/></a>
                                    </li>
                                </c:when>
                                <c:when test='${item=="..."}'>
                                    <li class="page-item">
                                        <div class="page-link">
                                            <c:out value="${item}"/>
                                        </div>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="post.action?postid=<c:out value="${post.id}"/>&page=<c:out value="${item}"/>">
                                            <c:out value="${item}"/>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test='${pager.lastPage!=true}'>
                            <li class="page-item">
                                <a class="page-link"
                                   href="post.action?postid=<c:out value="${post.id}"/>&page=<c:out value="${pager.getCurrentPage()+1}"/>" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <form action="/commitFollowpost.action" method="post">
                    <div class="my-sm-2">
                        <textarea name="content" id="editor1" class="form-control"></textarea>
                        <script>
                            // Replace the <textarea id="editor1"> with a CKEditor
                            // instance, using default configuration.
                            CKEDITOR.replace( 'editor1' );
                        </script>
                    </div>
                    <div class="my-sm-2" style="float:right;">
                        <input type="hidden" name="postid" value="<c:out value="${post.id}"/>">
                        <input type="submit" class="btn btn-primary" value="发表">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>