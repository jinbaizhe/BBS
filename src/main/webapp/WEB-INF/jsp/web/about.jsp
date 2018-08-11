<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2018/3/23
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>关于</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div>
                <div class="my-sm-2 text-center">
                    <h1>关于</h1>
                </div>
                <div class="my-sm-5">
                    <h2>技术栈</h2>
                    <ul>
                        <li>JDK8</li>
                        <li>Struts2-2.5.14.1</li>
                        <li>Spring-4.3.14</li>
                        <li>Hibernate-5.2.14</li>
                        <li>MySQL</li>
                        <li>Tomcat8.5</li>
                        <li>Bootstrap4</li>
                    </ul>
                    <h2>特性</h2>

                </div>
                <div class="my-sm-5">
                    <h3>历史更新</h3>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.4.1</h5>
                        <ol>
                            <li>修复用户在未勾选自动登录选项时登录成功后访问其它页面却提示用户未登录的问题</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.4.0</h5>
                        <ol>
                            <li>改用注解配置方式</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.3.3</h5>
                        <ol>
                            <li>调整NBA直播页面的显示效果。</li>
                            <li>修复搜索时搜索关键字可以为空的问题。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.3.2</h5>
                        <ol>
                            <li>修复记住密码功能失效的问题。</li>
                            <li>修复不选择图片上传路径时点上传会变成默认头像的问题。</li>
                            <li>修复更新个人信息失败的问题。</li>
                            <li>修复NBA直播无法显示当天比赛的问题。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.3.1</h5>
                        <ol>
                            <li>修复无法查看他人信息的问题。</li>
                            <li>修复帖子置顶、精华功能消失的问题。</li>
                            <li>修复发帖时标题、内容可以为空的问题。</li>
                            <li>修复回帖时内容可以为空的问题。</li>
                            <li>修复用户注册时用户名可以为空的问题。</li>
                            <li>调整首页子论坛的排序。</li>
                            <li>个人中心增加显示论坛等级功能（但论坛等级并未完成开发）。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.3.0</h5>
                        <ol>
                            <li>添加找回账号密码功能。</li>
                            <li>添加邮箱验证功能。</li>
                            <li>修改帖子图片的上传方式。</li>
                            <li>添加用户设置账号头像功能。</li>
                            <li>其它细节改动。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.2.2</h5>
                        <ol>
                            <li>添加账号自动登录的功能。</li>
                            <li>添加搜索帖子的功能。</li>
                            <li>修正部分分页导航的显示效果。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.2.1</h5>
                        <ol>
                            <li>修复在IE11下无法加载CSS文件的问题。（无效）</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.2.0</h5>
                        <ol>
                            <li>添加验证码功能。</li>
                            <li>添加收藏帖子功能。</li>
                            <li>完善个人中心页面。</li>
                            <li>添加查看他人资料页面。</li>
                            <li>修复仍旧显示失效的直播链接的问题。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.1.4</h5>
                        <ol>
                            <li>修复查看直播链接时页面无法显示的问题。</li>
                            <li>更新后台Python爬虫脚本，支持表情字符。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.1.3</h5>
                        <ol>
                            <li>使用事务管理。</li>
                            <li>修复用户发帖、回帖时无法附带图片的问题。</li>
                            <li>修复帖子图片显示错误问题。</li>
                            <li>修复用户无法修改密码的问题。</li>
                            <li>修复用户更新个人信息成功但页面却仍显示旧信息的问题。</li>
                            <li>修复某一用户名已被注册但其他用户仍可注册该用户名的问题。</li>
                            <li>添加提示操作失败页面。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.1.2</h5>
                        <ol>
                            <li>调整首页版块显示效果。</li>
                            <li>添加显示回帖楼层号的功能。</li>
                            <li>修复查看帖子时回帖数量不正确的问题。</li>
                        </ol>
                    </div>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.1.1</h5>
                        <ol>
                            <li>修复部分管理权限问题。</li>
                            <li>修复部分链接地址错误。</li>
                            <li>修复部分帖子无法显示问题。</li>
                            <li>修复版块管理显示页面问题。</li>
                            <li>添加部分网页标题。</li>
                            <li>将参数整合进web.xml中。</li>
                        </ol>
                    </div>

                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.1.0</h5>
                        <ol>
                            <li>新增可通过排序查看帖子、回帖功能。</li>
                            <li>添加设置、撤销管理员功能。</li>
                            <li>添加置顶、高亮帖子功能。</li>
                            <li>修复用户修改个人信息后无法及时更新session中相关个人信息的问题。</li>
                            <li>修改部分AOP切点。</li>
                            <li>修改部分权限的验证方式。</li>
                        </ol>
                    </div>
                </div>
                <div class="my-sm-5 text-center">
                    <h5>Powered by Parker</h5>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>
