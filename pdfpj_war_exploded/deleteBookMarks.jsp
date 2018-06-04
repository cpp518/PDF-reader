<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/6/4
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除书签</title>
</head>
<body>

     <form action="<%=request.getContextPath()%>/deleteBookMarks" method="post">
         用户名：<input type="text" name="username" /><br>
         密码：<input type="text" name="passwd"/><br>
         要删除的书签编号：<input type="text" name="num" /><br>
         <input type="submit" value="提交">
     </form>
</body>
</html>
