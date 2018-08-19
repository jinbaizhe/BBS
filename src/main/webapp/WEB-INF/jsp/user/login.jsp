<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <title>登录</title>
    <script>
        function checkVerifyCode(){
            var inputVerifyCode = document.getElementById('verifyCode').value;
            var verifyCode_message = document.getElementById("verifyCode_message");
            var isRight = true;
            $.ajax({
                type: 'post',
                url: '/user/checkVerifyCode.action',
                data: {'verifyCode': inputVerifyCode},
                dataType: 'json',
                async: false,
                success: function(data){
                    verifyCode_message.innerText = data.message;
                    if (data.isRight) {
                        verifyCode_message.className = 'form-text text-success';
                    }else {
                        verifyCode_message.className = 'form-text text-danger';
                        isRight = false;
                    }
                },
                error: function () {
                    verifyCode_message.innerText = '连接服务器失败';
                    isRight = false;
                }
            });
            return isRight;
        }
        function checkUsername() {
            var username = document.getElementById("username");
            var username_message = document.getElementById("username_message");
            var pattern = /^\w+$/;
            if (!pattern.test(username.value)){
                username_message.innerText = '用户名格式不正确';
                return false;
            }else {
                username_message.innerText = '';
                return true;
            }
        }
        function checkPassword() {
            var password = document.getElementById("password");
            var password_message = document.getElementById("password_message");
            var pattern = /^\w+$/;
            if (!pattern.test(password.value)){
                password_message.innerText = '密码格式不正确';
                return false;
            }else {
                password_message.innerText = '';
                return true;
            }
        }
        function changeVerifyCode() {
            var verifyCode = document.getElementById('verifyCodeImg');
            var num = (Math.random()*100)*(Math.log(Math.random()*10));
            num = Math.round(num);
            verifyCode.src = '/user/getVerifyCode.action?num=' + num;
        }
        function checkForum() {
            var checkUsernameResult = checkUsername();
            var checkPasswordResult = checkPassword();
            var checkVerifyCodeResult = checkVerifyCode();
            return checkUsernameResult && checkPasswordResult && checkVerifyCodeResult;
        }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4 border" style="border: grey;border-radius: 15px;padding: 20px;margin-left: auto;margin-right: auto;">
            <form action="/user/login.action" method="post" onsubmit="return checkForum();">
                <div class="form-group">
                    <div class="text-danger text-center">
                        <h5>
                            <c:out value="${message}"/>
                        </h5>
                    </div>
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="username" id="username" onblur="checkUsername()" placeholder="请输入用户名">
                    <small class="form-text text-danger" id="username_message"></small>
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="password" id="password" onblur="checkPassword()" placeholder="请输入密码">
                    <small class="form-text text-danger" id="password_message"></small>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <label>验证码</label>
                        <img src="/user/getVerifyCode.action" id="verifyCodeImg" width="120" height="40" alt="无法显示验证码" onclick="changeVerifyCode();">
                        <input type="text" class="form-control" name="verifyCode" id="verifyCode" onblur="checkVerifyCode()" placeholder="请输入验证码">
                        <small class="form-text text-muted" name="verifyCode_message" id="verifyCode_message"></small>
                    </div>
                </div>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" name="autoLogin" value="true">
                    <label class="form-check-label">七日内自动登录</label>
                </div>
                <div class="form-group text-center mt-sm-4">
                    <input class="btn btn-primary btn-block" type="submit" value="登录">
                </div>

                <div class="form-group">
                    <a class="float-sm-left" href="/user/forgetPassword.action">忘记密码?</a>
                    <a class="float-sm-right" href="/user/register.action">立即注册</a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/web/footer.jsp"/>
<%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>