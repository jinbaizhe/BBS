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
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <title>操作成功</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="my-sm-5">
                <h1 class="text-center"><c:out value="${title}"/></h1>
            </div>
            <div class="my-sm-5">
                <h5 class="text-center">
                    <c:out value="${message}"/>
                    <a href="${sessionScope.get("referURL")}">返回操作前页面</a>
                </h5>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>
