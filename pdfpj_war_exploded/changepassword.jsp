<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/14
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
      <form action="<%=request.getContextPath()%>/changepassword" method="post">
          输入新密码：<input type="text" name="passwd"><br/>
          <input type="hidden" name="keyvalue" value="<%=request.getParameter("keyvalue")%>" />
          <input type="submit" value="提交">
      </form>
</body>
</html>
