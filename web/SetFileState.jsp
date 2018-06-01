<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/1
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新文件状态</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/SetFileState" method="post">
        输入用户名：<input type="text" name="username"/><br>
        输入密码：<input type="text" name="passwd" /><br>
        输入书的编号：<input type="text" name="bookid" /><br>
        输入要设置的状态：<input type="text" name="state" /><br>
        <input type="radio" name="type" value="bookmark" checked="checked">书签
        <input type="radio" name="type" value="book">书本
        <input type="submit" value="提交" />
    </form>
</body>
</html>
