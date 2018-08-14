<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fnt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <title><c:out value="${game.away}"/>&nbsp;VS&nbsp;<c:out value="${game.home}"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-10" style="margin-left: auto;margin-right: auto">
            <h2 class="text-center">
                <fnt:formatDate value="game.date" pattern="yyyy-MM-dd"/>：
                <strong><c:out value="${game.away}"/></strong>
                &nbsp;VS&nbsp;
                <strong><c:out value="${game.home}"/></strong>
            </h2>
            <c:if test="${gameLinks.size()==0}">
                <h2 style="text-align: center">暂无该场比赛的直播信息</h2>
                <h3 style="text-align: center">比赛前一小时左右更新直播链接</h3>
            </c:if>
            <c:forEach items="${gameLinks}" var="gameLink">
                <div class="card my-sm-5">
                    <div class="card-body">
                        <h5 class="card-title">链接：</h5>
                        <p class="card-text">
                            <c:out value="${gameLink.info}"/>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>