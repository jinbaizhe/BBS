<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>全部版块</title>
</head>
<body>
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
        <c:forEach items="${mainForumMap}" var="item">
            <div class="card mb-sm-4">
                <h5 class="card-header bg-dark text-white">
                    <c:out value="${item.key.name}"></c:out>
                    <small><c:out value="${item.key.info}"></c:out></small>
                    <a class="float-right" href="mainforum.action?mfid=<c:out value="${item.key.id}"></c:out>">
                        更多
                    </a>
                </h5>
                <table class="table table-hover" style="margin-bottom: 0px">
                    <tbody>
                    <c:choose>
                        <c:when test="${item.value.size()==0}">
                            <tr>
                                <td>
                                    <div class="text-center">
                                        <h5>暂无子版块</h5>
                                    </div>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${item.value}" var="subforum" varStatus="st">
                                <c:if test="${st.index<5}">
                                    <tr>
                                        <td>
                                            <a href="/subforum.action?sfid=<c:out value="${subforum.id}"></c:out>">
                                                <c:out value="${subforum.name}"></c:out>
                                            </a>
                                        </td>
                                        <td><c:out value="${subforum.info}"></c:out></td>
                                        <td>帖数：</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </c:forEach>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>