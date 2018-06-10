<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/10
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看帖子</title>
</head>
<body>

    <form action="<%=request.getContextPath()%>/showpostdetial" method="post">
        <input type="text" name="id"/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
