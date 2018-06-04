<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/5/28
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下载文件</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/download" method="post">
        输入要下载的文件序号：<input type="text" name="index" />
        <input type="submit" value="下载"/>
    </form>
</body>
</html>
