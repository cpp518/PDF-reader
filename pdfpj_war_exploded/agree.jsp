<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/14
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>点赞</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/agree" method="post">
        用户名：<input type="text" name="username"><br/>
        密码：<input type="text" name="passwd"/><br/>
        操作的类型：<input type="text" name="type"/><br/>
        点赞对象：<input type="text" name="targetid"/><br/>
        点赞对象的类型：<input type="text" name="agreetype"/><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
