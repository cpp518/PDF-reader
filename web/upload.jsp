<%--
  Created by IntelliJ IDEA.
  User: 城
  Date: 2018/5/19
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传实例</title>
</head>
<body>
 <form method="POST" action="/pdfpj/UploadServlet" enctype="multipart/form-data" accept-charset="utf-8">
     用户名：<input type="text" name="username">
     <br>
     密 码：<input type="text" name="passwd">
     <br>
     书 名：<input type="text" name="bookname">
     <br>
     作 者：<input type="text" name="author">
     <br>
     简 介：<input type="text" name="introduction">
     <br>
     标 签：<input type="text" name="label">
     <br>
     类 型：<input type="text" name="type">
     <br>
     页 数：<input type="text" name="page">
     <br>
     选择一个文件：
     <input type="file" name="uploadFile">
     <br>
     <input type="radio" name="file" value="FILE" checked="checked">文件
     <input type="radio" name="file" value="PICTURE">图片
     <br>

     <input type="submit" value="上传">
 </form>
</body>
</html>
