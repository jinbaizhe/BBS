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
<!DOCTYPE>
<html>
<head>
    <%@include file="head.jsp"%>
    <title><c:out value="${title}"/></title>
    <script type="text/javascript">
        function getMainForumsData() {
            var mainForumSelect = document.getElementById('mainForum');
            $.ajax({
                type: 'post',
                url: '/getMainForumsData.action',
                data: {},
                dataType: 'json',
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        mainForumSelect.innerHTML += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                },
                error: function (e) {
                    alert(e.value)
                }
            });
        }
        function getSubForumsData() {
            var subForumSelect = document.getElementById('subForum');
            var mainForumId = document.getElementById('mainForum').value;
            subForumSelect.innerHTML = '';
            $.ajax({
                type: 'post',
                url: '/getSubForumsData.action',
                data: {'mfid': mainForumId},
                dataType: 'json',
                success: function (data) {
                    for(var i=0;i<data.length;i++){
                        subForumSelect.innerHTML+='<option value="' + data[i].id +  '">' + data[i].name + '</option>';
                    }
                },
                error: function (e) {
                    alert(e.value)
                }
            })
        }
    </script>
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top" onload="getMainForumsData();">
<%@ include file="slideBar.jsp"%>
<div class="content-wrapper">
    <div class="container">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/manage/index.action">后台管理系统</a>
            </li>
            <li class="breadcrumb-item active">帖子管理</li>
        </ol>
        <div class="row">
            <div class="col-sm-12">
                <h3 class="text-center"><c:out value="${title}"/></h3>
            </div>
        </div>
        <div class="row my-sm-2">
            <div class="col-sm-12">
                <form action="/manage/post.action" method="get">
                    <div class="input-group" style="width: 100%">
                        <select class="form-control mr-5" name="mfid" id="mainForum" onchange="getSubForumsData();">
                            <option hidden>请选择主板块</option>
                        </select>
                        <select class="form-control mr-5" name="mfid" id="subForum">
                            <option hidden>请选择子板块</option>
                        </select>
                        <input class="form-control mr-5" type="text" name="search" id="search" placeholder="帖子ID/帖子标题">
                        <input class="btn btn-primary mr-5" type="submit" value="搜索">
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <c:if test="${message!=null}">
                    <h4 class="text-success"><c:out value="${message}"/></h4>
                </c:if>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">帖子ID</th>
                        <th scope="col">所属子版块</th>
                        <th scope="col">标题</th>
                        <th scope="col">发帖者</th>
                        <th scope="col">发帖时间</th>
                        <th scope="col">最后回复时间</th>
                        <th scope="col">浏览数</th>
                        <th scope="col">加精</th>
                        <th scope="col">置顶</th>
                        <th scope="col">操作</th>
                    </tr>
                    </thead>
                    <c:if test="${users.size()==0}">
                        <tr>
                            <td colspan="8">
                                <h4 class="text-center">暂无用户</h4>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <th scope="row"><c:out value="${user.id}"/></th>
                            <td><c:out value="${user.username}"/></td>
                            <td><c:out value="${user.email}"/></td>
                            <td><fmt:formatDate value="${user.registerTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td></td>
                            <td></td>
                            <td><c:out value="${user.status}"/></td>
                            <td>
                                <shiro:hasRole name="SuperAdmin">
                                    <c:if test="${type=='user'}">
                                        <a class="btn btn-success btn-sm mr-sm-2" href="/manage/setUserAdmin.action?userid=<c:out value="${user.id}"/>">设为管理员</a>
                                    </c:if>
                                    <c:if test="${type=='admin'}">
                                        <a class="btn btn-success btn-sm mr-sm-2" href="/manage/unsetUserAdmin.action?userid=<c:out value="${user.id}"/>">撤销管理员</a>
                                    </c:if>
                                </shiro:hasRole>
                                <a class="btn btn-info btn-sm mr-sm-2" href="#">重置密码</a>
                                <a class="btn btn-warning btn-sm mr-sm-2" href="#">禁言</a>
                                <a class="btn btn-danger btn-sm mr-sm-2" href="#">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <nav aria-label="Page navigation example" class="my-sm-3">
                    <ul class="pagination justify-content-center">

                        <c:if test='${pager.firstPage!=true}'>
                            <li class="page-item">
                                <a class="page-link" href="/manage/user.action?page=1&search=<c:out value="${search}"/>">
                                    首页
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="/manage/user.action?page=<c:out value="${pager.getCurrentPage-1}"/>&search=<c:out value="${search}"/>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach items='${pager.pageList}' var="item">
                            <c:choose>
                                <c:when test="${item==pager.currentPage}">
                                    <li class="page-item active">
                                        <a class="page-link" href="/manage/user.action?page=<c:out value="${item}"/>&search=<c:out value="${search}"/>">
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
                                        <a class="page-link" href="/manage/user.action?page=<c:out value="${item}"/>&search=<c:out value="${search}"/>">
                                            <c:out value="${item}"/>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        <c:if test='${pager.lastPage!=true}'>
                            <li class="page-item">
                                <a class="page-link" href="/manage/user.action?page=<c:out value="${pager.getCurrentPage+1}"/>&search=<c:out value="${search}"/>" aria-label="Next">
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
