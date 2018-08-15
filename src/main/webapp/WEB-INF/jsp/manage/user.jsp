<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2018/4/11
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>用户管理</title>
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<%@ include file="slideBar.jsp"%>
<div class="content-wrapper">
    <div class="container">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/manage/index.action">后台管理系统</a>
            </li>
            <li class="breadcrumb-item active">用户设置</li>
        </ol>
        <div class="text-center">
            <h1>用户设置</h1>
            <!--
            {% if error_message %}
            <h4 class="text-danger text-center">{{ error_message }}</h4>
            {% endif %}
            {% if success_message %}
            <h4 class="text-success text-center">{{ success_message }}</h4>
            {% endif %}
            -->
        </div>
        <div class="row">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">用户ID</th>
                    <th scope="col">用户名</th>
                    <th scope="col">用户类型</th>
                    <th scope="col">邮箱</th>
                    <th scope="col">注册时间</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <c:if test="${users.size()==0}">
                    <tr>
                        <td colspan="6">
                            <h4 class="text-center">暂无用户数据</h4>
                        </td>
                    </tr>
                </c:if>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <th scope="row"><c:out value="${user.id}"/></th>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.type}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><fmt:formatDate value="${user.registerTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.type==0}">
                                    <a class="btn btn-primary btn-sm mr-sm-2" href="/manage/setAdmin.action?userid=<c:out value="${user.id}"/>">设为管理员</a>
                                </c:when>
                                <s:when test="${user.type==1}">
                                    <a class="btn btn-primary btn-sm mr-sm-2" href="/manage/unsetAdmin.action?userid=<c:out value="${user.id}"/>">撤销管理员</a>
                                </s:when>
                            </c:choose>

                            <!--
                            <a class="btn btn-primary btn-sm mr-sm-2" href="#">删除</a>
                            -->
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <nav aria-label="Page navigation example" class="my-sm-3">
                    <ul class="pagination justify-content-center">

                        <c:if test='${pager.firstPage!=true}'>
                            <li class="page-item">
                                <a class="page-link" href="/manage/user.action?page=1">
                                    首页
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="/manage/user.action?page=<c:out value="${pager.getCurrentPage-1}"/>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach items='${pager.pageList}' var="item">
                            <c:choose>
                                <c:when test="${item==pager.currentPage}">
                                    <li class="page-item active">
                                        <a class="page-link" href="/manage/user.action?page=<c:out value="${item}"/>">
                                            <c:out value="${item}"/>
                                        </a>
                                    </li>
                                </c:when>
                                <c:when test="${item=='...'}">
                                    <li class="page-item">
                                        <div class="page-link">
                                            <c:out value="${item}"></c:out>
                                        </div>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link" href="/manage/user.action?page=<c:out value="${item}"/>">
                                            <c:out value="${item}"/>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>



                        </c:forEach>
                        <c:if test='${pager.lastPage!=true}'>
                            <li class="page-item">
                                <a class="page-link" href="/manage/user.action?page=<c:out value="${pager.getCurrentPage+1}"/>" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/jsp/manage/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/manage/foot.jsp"%>
</body>
</html>
