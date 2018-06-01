

/register 注册页面

Method:POST
Param: username,nickname,passwd,email

loginClass 判断登录账号密码是否有效


/UploadServlet 上传文件
Method:POST
Param: username,passwd,bookname,author,introduction,label,type,page

/getUploadFile 获取自己上传的文件信息
Method:POST
Param:username,passwd
return:{id{1:{},2:{},3:{}}}


/getFIle   下载文件
Method:POST
Param:username,passwd

/showFile  展示所有的pdf文件
Method:Post,Get

/SetFileState
Method:Post,Get
Param:username,passwd,bookid,state,bookmark/book
当是bookmark时--bookid表示的是书签的id
当时book时--bookid表示的是书的id,即bookid

/uploadBookMarks
Metho:Post,Get
Param:username,passwd,id,bookid,title,content,pagenum
如果id为""时，表示新增加书签
如果id不为""时，表示更新书签

