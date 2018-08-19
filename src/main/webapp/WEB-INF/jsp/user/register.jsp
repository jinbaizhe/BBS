<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <script type="text/javascript">
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
        function checkUsername(){
            var inputName = document.getElementById('username').value;
            var user_message = document.getElementById("username_message");
            var isPass = true;
            var pattern = /^\w{3,}$/;
            if (!pattern.test(inputName)){
                user_message.className = 'form-text text-danger';
                user_message.innerText = '用户名不符合规范，要求3个及以上的字符';
                isPass = false;
            }else {
                $.ajax({
                    type: 'post',
                    url: '/user/isExistUser.action',
                    data: {username: inputName},
                    dataType: 'json',
                    async: false,
                    success: function(data){
                        user_message.innerText = data.message;
                        if (data.isExist) {
                            user_message.className = 'form-text text-danger';
                            isPass = false;
                        }else {
                            user_message.className = 'form-text text-success';
                        }
                    },
                    error: function () {
                        user_message.innerText = '连接服务器失败';
                        isPass = false;
                    }
                });
            }
            return isPass;
        }

        function checkPassword() {
            var password = document.getElementById('password');
            var password_message = document.getElementById('password_message');
            var pattern = /^\w{6,}/;
            if (!pattern.test(password.value)){
                password_message.innerText = '密码输入不符合规范，要求6位及以上';
                return false;
            }
            else {
                password_message.innerText = '';
                return true;
            }
        }
        function confirmPassword() {
            var password = document.getElementById('password');
            var repeatPassword = document.getElementById('repeat_password');
            var repeatPassword_message = document.getElementById('repeat_password_message');
            if (password.value !== repeatPassword.value){
                repeatPassword_message.innerText = '两次密码输入不一致';
                return false;
            }
            else {
                repeatPassword_message.innerText = '';
                return true;
            }
        }
        function changeVerifyCode() {
            var verifyCode = document.getElementById('verifyCodeImg');
            var num = (Math.random()*100)*(Math.log(Math.random()*10));
            num = Math.round(num);
            verifyCode.src = '/user/getVerifyCode.action?num=' + num;
        }
        function validateForm() {
            var checkUsernameResult = checkUsername();
            var checkPasswordResult = checkPassword();
            var confirmPasswordResult = confirmPassword();
            var checkVerifyCodeResult = checkVerifyCode();
            return checkUsernameResult && checkPasswordResult && confirmPasswordResult && checkVerifyCodeResult;
        }
    </script>
    <title>注册</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4 border my-sm-3" style="border: grey;border-radius: 15px;padding: 20px;margin-left: auto;margin-right: auto;">
            <form method="post" action="/user/register.action" id="register_form" onsubmit="return validateForm();">
                <div class="form-group">
                    <div class="text-danger text-center">
                        <h5>
                            <c:out value="${message}"/>
                        </h5>
                    </div>
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名" onblur="checkUsername(this)">
                    <small class="form-text text-danger" id="username_message"></small>
                </div>
                <div class="form-group">
                    <label>邮箱</label>
                    <input type="text" class="form-control" name="email" id="email" placeholder="请输入邮箱">
                    <small class="form-text text-danger" id="email_message"></small>
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码" onblur="checkPassword()">
                    <small class="form-text text-danger" id="password_message"></small>
                </div>
                <div class="form-group">
                    <label>再次确认密码</label>
                    <input type="password" class="form-control" name="repeat_password" id="repeat_password" placeholder="请再次输入密码" onblur="confirmPassword()">
                    <small class="form-text text-danger" name="repeat_password_message" id="repeat_password_message"></small>
                </div>

                <div class="form-group">
                    <label>性别</label>
                    <input type="radio" class="" name="sex" id="sex_male" value="男" checked>男
                    <input type="radio" class="" name="sex" id="sex_female" value="女">女
                    <small class="form-text text-danger" id="sex_message"></small>
                </div>

                <div class="form-group">
                    <label>个人简介</label>
                    <input type="text" class="form-control" name="info" id="info" placeholder="请输入个人简介">
                    <small class="form-text text-danger" id="info_message"></small>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <label>验证码</label>
                        <img src="/user/getVerifyCode.action" id="verifyCodeImg" width="120" height="40" alt="无法显示验证码" onclick="changeVerifyCode();">
                        <input type="text" class="form-control" name="verifyCode" id="verifyCode" onblur="checkVerifyCode()" placeholder="请输入验证码">
                        <small class="form-text text-muted" name="verifyCode_message" id="verifyCode_message"></small>
                    </div>
                </div>
                <button class="btn btn-primary btn-block mt-sm-4" type="submit">注册</button>
                <button class="btn btn-primary btn-block mt-sm-4" type="reset">重置</button>
                <a href="/user/login.action" class="mt-sm-3 float-right">已有账号,直接登录</a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>