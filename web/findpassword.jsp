<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/14
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
    <form method="post" action="<%=request.getContextPath()%>/findpassword" >
        输入用户名：<input type="text" name="username"/><br/>
        输入邮箱：<input type="text" name="email" /><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
