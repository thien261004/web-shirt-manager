<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/18/2024
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body class="container">
<form action="/mau-sac/update?id=${ms.id}" method="post" value="${ms.id}">
    <div >
        <label class="form-label">Mã</label>
        <input type="text" class="form-control" name="ma" value="${ms.ma}">
    </div>
    <div >
        <label class="form-label">Tên</label>
        <input type="text" class="form-control" name="ten" value="${ms.ten}">
    </div>
    <div>
        <label>Trạng thái</label>
        <input type="radio" name="trangThai" value="1" ${ms.trangThai==1 ? "checked":""}/>
        <label >Dang hoạt động</label>
        <input type="radio" name="trangThai" value="0" ${ms.trangThai==0 ? "checked":""} />
        <label >Ngừng hoạt động</label>
    </div>
    <button class="btn btn-warning">Update</button>
</form>
</body>
</html>
