<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <script type="text/javascript">
        function checkUsername(){
            var inputName = $("#username").val();
            alert(inputName);
            $.ajax({
                type: 'post',
                url: '/user/test1.action',
                data: {username: inputName},
                dataType: 'json',
                success: function(user){
                    alert(user.username);
                    alert(user.sex);
                },
                error: function () {
                    alert("出错了！！！");
                }
            });
        }
    </script>
    <title>注册</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4 border my-sm-3" style="border: grey;border-radius: 15px;padding: 20px;margin-left: auto;margin-right: auto;">
            <form method="post" action="/user/register.action" id="register_form">
                <div class="form-group">
                    <div class="text-danger text-center">
                        <h5>
                            <c:out value="${message}"/>
                        </h5>
                    </div>
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名" onblur="">
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
                <button class="btn btn-primary btn-block mt-sm-4" type="button" onclick="checkUsername()">重置</button>
                <a href="/user/login.action" class="mt-sm-3 float-right">已有账号,直接登录</a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>