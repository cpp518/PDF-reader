<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/1
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传书签</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/uploadBookMarks" method="post">
          用户名：<input type="text" name="username" /><br>
          密码：<input type="text" name="passwd" /><br>
          书签id:<input type="text" name="id" /><br>
          书的id:<input type="text" name="bookid" /><br>
          书签标题：<input type="text" name="title" /><br>
          书签内容：<input type="text" name="content" /><br>
          书签位置：<input type="text" name="pagenum" /><br>
          <input type="submit" value="提交" />
    </form>
</body>
</html>
