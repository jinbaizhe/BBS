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
        $(document).ready(function(){
            getMainForumsData();
        });

        function getMainForumsData() {
            var mainForumSelect = document.getElementById('mainForum');
            var selectMainForumId = document.getElementById('selectMainForumId').value;
            $.ajax({
                type: 'get',
                url: '/getMainForumsData.action',
                data: {},
                dataType: 'json',
                success: function (data) {
                    mainForumSelect.options.length=0;
                    var index = 0;
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].id == selectMainForumId) {
                            index = i;
                        }
                        mainForumSelect.add(new Option(data[i].name, data[i].id));
                    }
                    mainForumSelect.options[index].selected = true;
                    var mfid = mainForumSelect.options[index].value;
                    getSubForumsData(mfid);
                },
                error: function () {
                    alert('出错了！');
                }
            });
        }
        function getSubForumsData(mainForumId) {
            var subForumSelect = document.getElementById('subForum');
            var selectSubForumId = document.getElementById('selectSubForumId').value;
            $.ajax({
                type: 'get',
                url: '/getSubForumsData.action',
                data: {'mfid': mainForumId},
                dataType: 'json',
                success: function (data) {
                    subForumSelect.options.length=0;
                    var index = -1;
                    if (selectSubForumId == ''){
                        subForumSelect.add(new Option('请选择子版块', ''));
                        subForumSelect.options[0].hidden = true;
                    }
                    for(var i=0;i<data.length;i++){
                        if (selectSubForumId == data[i].id) {
                            index = i;
                        }
                        subForumSelect.add(new Option(data[i].name, data[i].id));
                    }
                    if (index !== -1){
                        subForumSelect.options[index].selected = true;
                    }
                },
                error: function () {
                    alert('出错了！');
                }
            })
        }
    </script>
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
            <li class="breadcrumb-item active">帖子管理</li>
        </ol>
        <div class="row">
            <div class="col-sm-12">
                <h3 class="text-center"><c:out value="${title}"/></h3>
            </div>
        </div>
        <div class="row my-sm-2">
            <div class="col-sm-12">
                <input type="hidden" id="selectMainForumId" value="<c:out value="${mainForumId}"/>">
                <input type="hidden" id="selectSubForumId" value="<c:out value="${subForumId}"/>">
                <form action="/manage/post.action" method="post" style="width: 40%">
                    <div class="input-group-append">
                        <input class="form-control mr-5" type="text" name="search" id="search" placeholder="帖子ID/帖子标题">
                        <input type="hidden" name="type" value="search">
                        <input class="btn btn-primary mr-5" type="submit" value="搜索">
                    </div>
                </form>
                <form action="/manage/post.action" method="post">
                    <div class="input-group" style="width: 100%">
                        <select autocomplete="off" class="form-control mr-5" name="mfid" id="mainForum" onchange="getSubForumsData(this.value);">
                        </select>
                        <select autocomplete="off" class="form-control mr-5" name="sfid" id="subForum">
                        </select>
                        <input type="hidden" name="type" value="browse">
                        <input class="btn btn-primary mr-5" type="submit" value="确定">
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
                    <tbody id="dataBody">
                    <c:if test="${users.size()==0}">
                        <tr>
                            <td colspan="8">
                                <h4 class="text-center">暂无用户</h4>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach items="${posts}" var="post">
                        <tr>
                            <th scope="row"><c:out value="${post.id}"/></th>
                            <td><c:out value="${post.subForum.id}"/></td>
                            <td><c:out value="${post.title}"/></td>
                            <td><c:out value="${post.user.id}"/></td>
                            <td><fmt:formatDate value="${post.sendTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><fmt:formatDate value="${post.lastReplyTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><c:out value="${post.viewNum}"/></td>
                            <td><c:out value="${post.type}"/></td>
                            <td><c:out value="${user.status}"/></td>
                            <td>
                                <shiro:hasAnyRoles name="Admin,SuperAdmin">
                                    <c:choose>
                                        <c:when test="${post.top==0}">
                                            <a class="btn btn-info btn-sm mr-sm-2" href="/manage/setPostTop.action?postid=<c:out value="${post.id}"/>">置顶</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-info btn-sm mr-sm-2" href="/manage/unsetPostTop.action?postid=<c:out value="${post.id}"></c:out>">取消置顶</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${post.type==0}">
                                            <a class="btn btn-warning btn-sm mr-sm-2" href="/manage/setPostEssential.action?postid=<c:out value="${post.id}"/>">加精</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-warning btn-sm mr-sm-2" href="/manage/unsetPostEssential.action?postid=<c:out value="${post.id}"/>">取消加精</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <a class="btn btn-danger btn-sm mr-sm-2" href="/manage/deletePost.action?postid=<c:out value="${post.id}"/>">删除</a>
                                </shiro:hasAnyRoles>
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
                                <a class="page-link" href="/manage/post.action?page=1&search=<c:out value="${search}"/>">
                                    首页
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="/manage/post.action?page=<c:out value="${pager.getCurrentPage-1}"/>&search=<c:out value="${search}"/>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach items='${pager.pageList}' var="item">
                            <c:choose>
                                <c:when test="${item==pager.currentPage}">
                                    <li class="page-item active">
                                        <a class="page-link" href="/manage/post.action?page=<c:out value="${item}"/>&search=<c:out value="${search}"/>">
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
                                        <a class="page-link" href="/manage/post.action?page=<c:out value="${item}"/>&search=<c:out value="${search}"/>">
                                            <c:out value="${item}"/>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        <c:if test='${pager.lastPage!=true}'>
                            <li class="page-item">
                                <a class="page-link" href="/manage/post.action?page=<c:out value="${pager.getCurrentPage+1}"/>&search=<c:out value="${search}"/>" aria-label="Next">
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
