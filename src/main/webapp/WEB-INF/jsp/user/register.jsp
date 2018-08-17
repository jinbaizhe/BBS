<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <script type="text/javascript" src="/dwr/engine.js"></script>
    <script type="text/javascript" src="/dwr/util.js"></script>
    <script type="text/javascript" src="/dwr/interface/UserDAOAjax.js"></script>
    <script type="text/javascript">
        function show(boolean) {
            var info=document.getElementById("usernameInfo");
            if(boolean)
            {
                info.innerText="用户名已被注册";
            }
        }
        function validate() {
            var name=form1.name.value;
            var info=document.getElementById("usernameInfo");
            info.innerText="";
            if(name=="")
            {
                info.innerText="用户名不能为空";
                return;
            }
            UserDAOAjax.isExistUser(name,show)
        }
        function checkForm() {
            var isCommit = true;
            var form = document.getElementById('register_form')
            var username =  document.getElementById('username');
            var password =  document.getElementById('password');
            var repeat_password =  document.getElementById('repeat_password');
            var email =  document.getElementById('email');
            var info =  document.getElementById('info');
            if (username.innerText==''){
                var info = document.getElementById('username_message');
                info.innerText='用户名不能为空';
                isCommit = false;
            }
            if (password.innerText !== repeat_password.innerText){
                var info = document.getElementById('password_message');
                var info_repeat = document.getElementById('repeat_password_message');
                info.innerText='两次密码不一致';
                info_repeat.innerText='两次密码不一致';
                isCommit = false;
            }
            if (password.innerText ==''){
                var info = document.getElementById('password_message');
                var info_repeat = document.getElementById('repeat_password_message');
                info.innerText='密码不能为空';
                info_repeat.innerText = '密码不能为空';
                isCommit = false;
            }
            return isCommit;
        }
    </script>
    <title>注册</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4 border my-sm-3" style="border: grey;border-radius: 15px;padding: 20px;margin-left: auto;margin-right: auto;">
            <form method="post" action="/user/register.action" id="register_form" onsubmit="return checkForm(this);">
                <div class="form-group">
                    <div class="text-danger text-center">
                        <h5>
                            <c:out value="${message}"/>
                        </h5>
                    </div>
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名" onblur="validate()">
                    <small class="form-text text-danger" id="username_message"></small>
                </div>
                <div class="form-group">
                    <label>邮箱</label>
                    <input type="text" class="form-control" name="email" id="email" placeholder="请输入邮箱">
                    <small class="form-text text-danger" id="email_message"></small>
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                    <small class="form-text text-danger" id="password_message"></small>
                </div>
                <div class="form-group">
                    <label>再次确认密码</label>
                    <input type="password" class="form-control" name="repeat_password" id="repeat_password" placeholder="请再次输入密码">
                    <small class="form-text text-danger" id="repeat_password_message"></small>
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
                <button class="btn btn-primary btn-block mt-sm-4" type="submit">注册</button>
                <button class="btn btn-primary btn-block mt-sm-4" type="reset">重置</button>
                <a href="/login.action" class="mt-sm-3 float-right">已有账号,直接登录</a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>