<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/book/delete" method="post" enctype="multipart/form-data">
    <%--<input name="id" id="id">--%>
    <input name="bookIds" id="name">
    <input name="bookIds" id="cid">
    <input type="submit">
</form>

<p id="errorInfo"></p>
<%System.out.println(request.getContextPath());%>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.js"></script>
</body>
</html>