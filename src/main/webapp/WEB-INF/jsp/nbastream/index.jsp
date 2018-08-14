<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fnt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/jsp/web/head.jsp"%>
    <title>NBA直播</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-10" style="margin-left: auto;margin-right: auto">
            <h1 style="text-align: center">NBA直播</h1>
            <h3 style="text-align: center">（比赛前一小时左右更新比赛信息）</h3>
            <c:if test="${games.size()==0}">
                <h2 style="text-align: center">暂无今日比赛信息</h2>
                <div class="card my-sm-5">
                    <div class="card-body">
                        <h5 class="card-title">过去五场比赛：</h5>
                        <p class="card-text">
                            <c:forEach items="${old_games}" var="game">
                                <div>
                                    <fnt:formatDate value="#game.date" pattern="yyyy-MM-dd"></fnt:formatDate>：
                                    <a href="/nbastream/gameLink.action?gameid=<c:out value="${game.id}"/>"><strong><c:out value="${game.away}"/></strong>&nbsp;VS&nbsp;<strong><c:out value="${game.home}"/></strong></a>
                                </div>
                            </c:forEach>
                        </p>
                    </div>
                </div>
            </c:if>
            <c:forEach items="${games}" var="game">
            <div class="card my-sm-5">
                <div class="card-body">
                    <h5 class="card-title">比赛：</h5>
                    <p class="card-text">
                        <strong><c:out value="${game.away}"/></strong>&nbsp;VS&nbsp;<strong><c:out value="${game.home}"/></strong>
                    </p>
                    <a href="/nbastream/gameLink.action?gameid=<c:out value="${game.id}"/>" class="btn btn-primary">查看直播链接</a>
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