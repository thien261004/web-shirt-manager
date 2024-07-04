<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/18/2024
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body >
<div class="mt-2 col-10 offset-1">
    <form method="GET" action="/khach-hang/index" class="container">
        <div class="row">
            <div class="col-6">
                <label>Tìm kiếm</label>
                <div>
                    <input type="text" name="keyword" class="form-control" value="${ keyword }"/>
                </div>
            </div>
            <div class="col-6">
                <label>Trạng thái</label>
                <div>
                    <select class="form-select" name="trangThai">
                        <option></option>
                        <option value="1" ${ trangThai == 1 ? "selected" : "" } >Đang hoạt động</option>
                        <option value="0" ${ trangThai == 0 ? "selected" : "" } >Ngừng hoạt động</option>
                    </select>
                </div>
            </div>
        </div>s
        <div class="row mt-3">
            <div class="col-6 offset-3">
                <button class="btn btn-primary">Tìm kiếm</button>
                <a class="btn btn-light" href="/khach-hang/index">Làm mới</a>
            </div>
        </div>
    </form>
</div>
<a href="/khach-hang/create">Thêm mới</a>
<table class="table table-hover table-stripped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Ten</th>
        <th>SDT</th>
        <th>Mã KH</th>
        <th>Trang Thai</th>
        <th colspan="2">Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${data}" var="kh">
        <tr>
            <td>${kh.id}</td>
            <td>${kh.ten}</td>
            <td>${kh.sdt}</td>
            <td>${kh.ma}</td>
            <td>${kh.trangThai}</td>
            <td>
                <a href="#" class="btn btn-info">Cập nhât</a>
                <a href="/khach-hang/delete?id=${nv.id}" class="btn btn-primary">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="/khach-hang/index?page=1&limit=${limit}">First</a>
            </li>

            <c:if test="${ page > 1 }">
                <li class="page-item">
                    <a class="page-link" href="/khach-hang/index?page=${ page - 1 }&limit=${limit}">
                            ${ page - 1 }
                    </a>
                </li>
            </c:if>

            <li class="page-item">
                <span class="page-link">${ page }</span>
            </li>

            <c:if test="${ page < totalPage }">
                <li class="page-item">
                    <a class="page-link" href="/khach-hang/index?page=${ page + 1 }&limit=${limit}">${ page + 1 }</a>
                </li>
            </c:if>

            <li class="page-item">
                <a class="page-link" href="/khach-hang/index?page=${ totalPage }&limit=${limit}">Last</a>
            </li>
        </ul>
    </nav>
</table>
</body>
</html>
