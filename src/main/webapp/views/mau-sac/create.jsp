<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/18/2024
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <c:if test="${ not empty sessionScope.error}">
        <p style="color: red">${sessionScope.error}</p>
    </c:if>
<form action="/mau-sac/store" method="post">
    <div >
        <label class="form-label">Mã</label>
        <input type="text" class="form-control" name="ma" >
    </div>
    <div >
        <label class="form-label">Tên</label>
        <input type="text" class="form-control" name="ten">
    </div>
    <div>
        <label>Trạng thái</label>
        <input type="radio" name="trangThai" value="1" checked />
        <label >Dang hoạt động</label>
        <input type="radio" name="trangThai" value="0" checked />
        <label >Ngừng hoạt động</label>
    </div>
    <button class="btn btn-warning">Thêm</button>
</form>
</body>
</html>
