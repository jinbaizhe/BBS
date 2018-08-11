<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <title>登录</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4 border" style="border: grey;border-radius: 15px;padding: 20px;margin-left: auto;margin-right: auto;">
            <form action="/user/login.action" method="post">
                <div class="form-group">
                    <div class="text-danger text-center">
                        <h5>
                            <c:out value="${message}"/>
                        </h5>
                    </div>
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="username" placeholder="请输入用户名">
                    <!--<small class="form-text text-muted" name=""></small>-->
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="password" placeholder="请输入密码">
                    <!--<small class="form-text text-muted" name=""></small>-->
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <label>验证码</label>
                        <img src="/getVerifyCode.action" width="100" height="30" alt="无法显示验证码">
                        <input type="text" class="form-control" name="verifyCode" placeholder="请输入左侧验证码">
                    </div>
                    <!--<small class="form-text text-muted" name=""></small>-->
                </div>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" name="autoLogin" value="true">
                    <label class="form-check-label">七日内自动登录</label>
                </div>
                <div class="form-group text-center mt-sm-4">
                    <input class="btn btn-primary btn-block" type="submit" value="登录">
                </div>

                <div class="form-group">
                    <a class="float-sm-left" href="/forgetPassword.action">忘记密码?</a>
                    <a class="float-sm-right" href="/register.action">立即注册</a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>