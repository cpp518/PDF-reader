<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/1
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取书签</title>
</head>
<body>
    <form method="post" action="<%=request.getContextPath()%>/getBookMarks">
        输入用户名：<input type="text" name="username"><br>
        输入密码：<input type="text" name="passwd" /><br>
        输入书的编号：<input type="text" name="bookid" /><br>
        <input type="radio" name="type" value="ALL" checked="checked"/>所有书签
        <input type="radio" name="type" value="one" />特定书签<br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
