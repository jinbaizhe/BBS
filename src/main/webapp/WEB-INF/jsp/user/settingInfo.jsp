<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <title>个人信息</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="nav flex-column nav-pills nav-justified" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <c:choose>
                    <c:when test="${type=='info'}">
                        <a class="nav-link active" id="v-pills-info-tab" data-toggle="pill" href="#v-pills-info" role="tab" aria-controls="v-pills-info" aria-selected="true">
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" id="v-pills-info-tab" data-toggle="pill" href="#v-pills-info" role="tab" aria-controls="v-pills-info" aria-selected="false">
                    </c:otherwise>
                </c:choose>
                        修改资料
                        </a>

                <c:choose>
                    <c:when test="${type=='avatar'}">
                        <a class="nav-link active" id="v-pills-avatar-tab" data-toggle="pill" href="#v-pills-avatar" role="tab" aria-controls="v-pills-avatar" aria-selected="true">
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" id="v-pills-avatar-tab" data-toggle="pill" href="#v-pills-avatar" role="tab" aria-controls="v-pills-avatar" aria-selected="false">
                    </c:otherwise>
                </c:choose>
                        修改头像
                        </a>
                <c:choose>
                    <c:when test="${type=='password'}">
                        <a class="nav-link active" id="v-pills-password-tab" data-toggle="pill" href="#v-pills-password" role="tab" aria-controls="v-pills-password" aria-selected="true">
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" id="v-pills-password-tab" data-toggle="pill" href="#v-pills-password" role="tab" aria-controls="v-pills-password" aria-selected="false">
                    </c:otherwise>
                </c:choose>
                        修改密码
                        </a>
                <c:choose>
                    <c:when test="${type=='post'}">
                        <a class="nav-link active" id="v-pills-post-tab" data-toggle="pill" href="#v-pills-post" role="tab" aria-controls="v-pills-post" aria-selected="true">
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" id="v-pills-post-tab" data-toggle="pill" href="#v-pills-post" role="tab" aria-controls="v-pills-post" aria-selected="false">
                    </c:otherwise>
                </c:choose>
                        我的发帖
                        </a>
                            
                <c:choose>
                    <c:when test="${type=='followpost'}">
                        <a class="nav-link active" id="v-pills-followpost-tab" data-toggle="pill" href="#v-pills-followpost" role="tab" aria-controls="v-pills-followpost" aria-selected="true">
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" id="v-pills-followpost-tab" data-toggle="pill" href="#v-pills-followpost" role="tab" aria-controls="v-pills-followpost" aria-selected="false">
                    </c:otherwise>
                </c:choose>
                        我的回帖
                        </a>
                        
                <c:choose>
                    <c:when test="${type=='collection'}">
                       <a class="nav-link active" id="v-pills-collection-tab" data-toggle="pill" href="#v-pills-collection" role="tab" aria-controls="v-pills-collection" aria-selected="true">
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" id="v-pills-collection-tab" data-toggle="pill" href="#v-pills-collection" role="tab" aria-controls="v-pills-collection" aria-selected="false">
                    </c:otherwise>
                </c:choose>
                        我的收藏
                        </a>
                    
            </div>
        </div>
        <div class="col-sm-8">
            <div class="tab-content" id="v-pills-tabContent">
                <c:choose>
                    <c:when test="${type=='info'}">
                        <div class="tab-pane fade show active" id="v-pills-info" role="tabpanel" aria-labelledby="v-pills-info-tab">
                    </c:when>
                    <c:otherwise>
                        <div class="tab-pane fade show" id="v-pills-info" role="tabpanel" aria-labelledby="v-pills-info-tab">
                        </c:otherwise>
                </c:choose>
                            <div class="row">
                                <div class="col-sm-6">
                                    <form action="/user/updateUserInfo.action" method="post">
                                        <div class="form-group">
                                            <p class="text-success"><c:out value="${message_info}"/></p>
                                        </div>
                                        <div class="form-group">
                                            <label>用户名</label>
                                            <input type=text class="form-control" name="username" placeholder="请输入用户名" value="<c:out value="${user.username}"/>" readonly>
                                            <!--<small class="form-text text-muted" name=""></small>-->
                                        </div>
                                        <div class="form-group">
                                            <label>账号状态</label>
                                            <input type="text" class="form-control" name="status" readonly value="<c:out value="${user.status}"></c:out>">
                                            <!--<small class="form-text text-muted" name=""></small>-->
                                        </div>
                                        <div class="form-group">
                                            <label>论坛等级</label>
                                            <input type="text" class="form-control" name="level" readonly value="<c:out value="${user.level}"/>">
                                            <!--<small class="form-text text-muted" name=""></small>-->
                                        </div>
                                        <div class="form-group">
                                            <label>个人简介</label>
                                            <input type="text" class="form-control" name="info" value="<c:out value="${user.info}"/>">
                                            <!--<small class="form-text text-muted" name=""></small>-->
                                        </div>
                                        <div class="form-group">
                                            <label>性别</label>
                                            <c:choose>
                                                <c:when test='${user.sex=="男"}'>
                                                    <input type="radio" class="" name="sex" value="男" checked>男
                                                    <input type="radio" class="" name="sex" value="女">女
                                                </c:when>
                                                <c:when test='${user.sex=="女"}'>
                                                    <input type="radio" class="" name="sex" value="男">男
                                                    <input type="radio" class="" name="sex" value="女" checked>女
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="form-group">
                                            <label>邮箱</label>
                                            <input type="email" class="form-control" name="email" placeholder="请输入邮箱" value="<c:out value="${user.email}"/>">
                                            <c:choose>
                                                <c:when test="${user.status==0}">
                                                    <a href="www.jinbaizhe.tech/sendActiveMail.action">验证邮箱</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <small>邮箱已验证</small>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>

                                        <div class="form-group">
                                            <label>注册时间</label>
                                            <input type="text" class="form-control" name="user.registerTime" readonly value="<fmt:formatDate value="${user.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
                                        </div>

                                        <div class="form-group text-center mt-sm-4">
                                            <input class="btn btn-primary btn-block" type="submit" value="保存">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                <c:choose>
                    <c:when test="${type=='avatar'}">
                        <div class="tab-pane fade show active" id="v-pills-avatar" role="tabpanel" aria-labelledby="v-pills-avatar-tab">
                    </c:when>
                    <c:otherwise>
                        <div class="tab-pane fade show" id="v-pills-avatar" role="tabpanel" aria-labelledby="v-pills-avatar-tab">
                    </c:otherwise>
                </c:choose>
                            <div class="row">
                                <div class="col-sm-6">
                                    <form action="/uploadAvatar.action" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <c:choose>
                                                <c:when test='${user.avatar!=null}'>
                                                    <img  alt="" class="img-responsive img-circle" src="/getPicture.action?id=<c:out value="${user.avatar.id}"/>"
                                                          style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <img  alt="" class="img-responsive img-circle" src="/static/default.jpg"
                                                          style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="form-group">
                                            <label>选择本地图片：</label>
                                            <input type="file" class="form-control" name="upload">
                                            <!--<small class="form-text text-muted" name=""></small>-->
                                        </div>

                                        <div class="form-group text-center mt-sm-4">
                                            <input class="btn btn-primary btn-block" type="submit" value="上传">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>


            <c:choose>
                <c:when test="${type=='password'}">
                    <div class="tab-pane fade show active" id="v-pills-password" role="tabpanel" aria-labelledby="v-pills-password-tab">
                </c:when>
                <c:otherwise>
                    <div class="tab-pane fade show" id="v-pills-password" role="tabpanel" aria-labelledby="v-pills-password-tab">
                </c:otherwise>
            </c:choose>
                        <div class="row">
                            <div class="col-sm-6">
                                <form action="/user/updateUserPassword.action" method="post">
                                    <div class="form-group">
                                        <p class="text-danger"><c:out value="${message_password}"></c:out></p>
                                    </div>
                                    <div class="form-group">
                                        <label>旧密码</label>
                                        <input type="password" class="form-control" name="oldpassword" placeholder="请输入密码">
                                        <!--<small class="form-text text-muted" name=""></small>-->
                                    </div>
                                    <div class="form-group">
                                        <label>新密码</label>
                                        <input type="password" class="form-control" name="password" placeholder="请输入密码">
                                        <!--<small class="form-text text-muted" name=""></small>-->
                                    </div>
                                    <div class="form-group">
                                        <label>再次确认密码</label>
                                        <input type="password" class="form-control" name="repeatpassword" placeholder="请再次输入密码">
                                        <!--<small class="form-text text-muted" name=""></small>-->
                                    </div>

                                    <div class="form-group text-center mt-sm-4">
                                        <input class="btn btn-primary btn-block" type="submit" value="保存">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
            <c:choose>
                <c:when test="${type=='post'}">
                    <div class="tab-pane fade show active" id="v-pills-post" role="tabpanel" aria-labelledby="v-pills-post-tab">
                </c:when>
                <c:otherwise>
                    <div class="tab-pane fade show" id="v-pills-post" role="tabpanel" aria-labelledby="v-pills-post-tab">
                </c:otherwise>
            </c:choose>
                        <div class="row">
                            <div class="col-sm-10">
                                <c:if test="${posts.size()==0}">
                                    <h4>暂无发帖记录</h4>
                                </c:if>
                                <c:forEach items="${posts}" var="post">
                                    <div class="card my-sm-3">
                                        <div class="card-body">
                                            <h5 class="card-title">帖子标题：<a href="/post.action?postid=<c:out value="${post.id}"/>"><c:out value="${post.title}"/></a></h5>
                                            <p class="card-text">
                                                <c:out value="${post.content}"/>
                                            </p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
            <c:choose>
                <c:when test="${type=='followpost'}">
                    <div class="tab-pane fade show active" id="v-pills-followpost" role="tabpanel" aria-labelledby="v-pills-followpost-tab">
                </c:when>
                <c:otherwise>
                    <div class="tab-pane fade show" id="v-pills-followpost" role="tabpanel" aria-labelledby="v-pills-followpost-tab">
                        <div class="row">
                            <div class="col-sm-10">
                                <c:if test="${followposts.size()==0}">
                                    <h4>暂无回帖记录</h4>
                                </c:if>
                                <c:forEach items="${followposts}" var="followpost">
                                    <div class="card my-sm-3">
                                        <div class="card-body">
                                            <h5 class="card-title">回复帖子：<a href="/post.action?postid=<c:out value="${followpost.post.id}"/>"><c:out value="${followpost.post.title}"/></a></h5>
                                            <p class="card-text">
                                                <c:out value="${followpost.content}"/>
                                            </p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${type=='collection'}">
                    <div class="tab-pane fade show active" id="v-pills-collection" role="tabpanel" aria-labelledby="v-pills-collection-tab">
                </c:when>
                <c:otherwise>
                    <div class="tab-pane fade show" id="v-pills-collection" role="tabpanel" aria-labelledby="v-pills-collection-tab">
                        <div class="row">
                            <div class="col-sm-10">
                                <c:if test="${collections.size()==0}">
                                    <h4>暂无收藏记录</h4>
                                </c:if>
                                <c:forEach items="${collections}" var="collection">
                                    <div class="card my-sm-3">
                                        <div class="card-body">
                                            <h5 class="card-title">帖子标题：<a href="/post.action?postid=<c:out value="${collection.id.post.id}"/>"><c:out value="${collection.id.post.title}"/></a></h5>
                                            <p class="card-text">
                                                <span>收藏时间：<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="#collection.time"/></span>
                                                <a href="/user/unstarPost.action?postid=<c:out value="${collection.id.post.id}"/>" class="btn btn-primary float-right">取消收藏</a>
                                            </p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

                        </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/jsp/web/footer.jsp"/>
    <%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>