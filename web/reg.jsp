<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/5/19
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<center>
    <form action="<%=request.getContextPath()%>/register" method="POST" >
        账号: <input type="text" name="name">
        <br />
        昵称：<input type="text" name="nick">
        <br>
        密码: <input type="text" name="passwd" />
        <br />
        邮箱：<input type="text" name="email" />
        <br>
        <input type="submit" value="提交" />
</center>
</body>
</html>
