<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/5/27
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取pdf文件</title>
</head>
<body>
      <form action="<%=request.getContextPath()%>/getUploadFile" method="post" >
          输入用户名：<input type="text" name="username" />
          <br>
          输入密  码：<input type="text" name="passwd" />
          <br>
          <input type="submit" value="提交"/>
      </form>
</body>
</html>
