<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/2
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除文件</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/deleteFile" method="post">
        输入用户名：<input type="text" name="username"/><br>
        输入密码：<input type="text" name="passwd" /><br>
        输入书的编号：<input type="text" name="bookid"/><br>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
