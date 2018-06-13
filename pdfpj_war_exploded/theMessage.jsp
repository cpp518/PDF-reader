<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/13
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看消息</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/themessage" method="post">
        用户名：<input type="text" name="username"/><br/>
        密码：<input type="text" name="passwd"/><br/>
        类型：<input type="text" name="type" value="1"/><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
