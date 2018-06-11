<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/11
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关注情况</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/attention" method="post">
    用户名：<input type="text" name="username"/><br>
    密码：<input type="text" name="passwd"/><br>
    类型：<input type="text" name="method"/><br>
    目标：<input type="text" name="targetid"/><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
