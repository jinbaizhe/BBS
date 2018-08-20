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
            <h1 class="my-sm-2 text-center">关于</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="my-sm-5">
                <h2>技术栈</h2>
                <ul>
                    <li>JDK8</li>
                    <li>Spring MVC</li>
                    <li>Spring</li>
                    <li>MyBatis</li>
                    <li>Redis</li>
                    <li>MySQL</li>
                    <li>Tomcat8.5</li>
                    <li>Bootstrap4</li>
                </ul>
                <h2>特性</h2>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="my-sm-5">
                <h3>历史更新</h3>
                <div class="my-sm-3" style="text-align: left">
                    <h5>版本：0.2.0</h5>
                    <ol>
                        <li>新增点赞功能。</li>
                        <li>部分服务改用异步形式。</li>
                        <li>完善后台管理。</li>
                        <li>新增日志记录功能。</li>
                    </ol>
                </div>
                <div class="my-sm-3" style="text-align: left">
                    <h5>版本：0.1.0</h5>
                    <ol>
                        <li>初始版本。</li>
                    </ol>
                </div>
            </div>
            <div class="my-sm-5">
                <h3>未来待增加功能（按优先级排序）</h3>
                <div class="my-sm-3" style="text-align: left">
                    <ul>
                        <li>图片上传、头像修改。</li>
                        <li>日志分类显示。</li>
                        <li>NBA直播模块。</li>
                        <li>搜索帖子功能。</li>
                        <li>帖子排序。</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="my-sm-2 text-center">
                <h5>Powered by Parker</h5>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>
