<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/7
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发帖</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/post">
    输入用户名：<input type="text" name="username"/><br/>
    输入密码：<input type="text" name="passwd"/><br/>
    输入标题：<input type="text" name="title"/><br/>
    输入内容：<input type="text" name="content"/><br/>
    输入属于的帖子：<input type="text" name="comefrom"/><br/>
    输入类型：<input type="text" name="type"/><br/>
    输入目标帖子：<input type="text" name="targetid"/><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
