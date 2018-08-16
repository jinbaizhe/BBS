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
                <div class="my-sm-5">
                    <h3>历史更新</h3>
                    <div class="my-sm-3" style="text-align: left">
                        <h5>版本：0.1.0</h5>
                        <ol>
                            <li>初始版本。</li>
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
