<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <s:if test="games.size==0">
                <h2 style="text-align: center">暂无今日比赛信息</h2>
                <div class="card my-sm-5">
                    <div class="card-body">
                        <h5 class="card-title">过去五场比赛：</h5>
                        <p class="card-text">
                            <s:iterator value="old_games" var="game">
                                <div>
                                    <s:date name="#game.date" format="yyyy-MM-dd"></s:date>：
                                    <a href="/nbastream/gameLink.action?gameid=<s:property value="#game.id"></s:property>"><strong><s:property value="#game.away"></s:property></strong>&nbsp;VS&nbsp;<strong><s:property value="#game.home"></s:property></strong></a>
                                </div>
                            </s:iterator>
                        </p>
                    </div>
                </div>
            </s:if>
            <s:iterator value="games" var="game">
            <div class="card my-sm-5">
                <div class="card-body">
                    <h5 class="card-title">比赛：</h5>
                    <p class="card-text">
                        <strong><s:property value="#game.away"></s:property></strong>&nbsp;VS&nbsp;<strong><s:property value="#game.home"></s:property></strong>
                    </p>
                    <a href="/nbastream/gameLink.action?gameid=<s:property value="#game.id"></s:property>" class="btn btn-primary">查看直播链接</a>
                </div>
            </div>
            </s:iterator>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/web/foot.jsp"%>
</body>
</html>