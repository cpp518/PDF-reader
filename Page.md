

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


/getFile   下载文件
Method:POST
Param:username,passwd

/deleteFile 删除上传的文件
Method:POST
Param:username,passwd,bookid

/showFile  展示所有的pdf文件
Method:Post,Get

/SetFileState 设置书本（书签）状态
Method:Post,Get
Param:username,passwd,bookid,state,bookmark/book
当是bookmark时--bookid表示的是书签的id
当时book时--bookid表示的是书的id,即bookid

/uploadBookMarks 上传书签
Method:Post,Get
Param:username,passwd,id,bookid,title,content,pagenum
如果id为""时，表示新增加书签
如果id不为""时，表示更新书签

/getMyBookMarks 获取书签
Method:Post,Get
param:username,passwd,type,bookid
当type为"ALL"时，表示获取所有书签
当type为"one"时，获取bookid的书签

/deleteBookMarks 删除书签
Method:Post  

/post 发帖
Method:post
Param:Username,passwd,type,targetid,comfrom.targetUserid,title,content

/showPost 看帖
Method:post

/showPostdetail 看某贴具体内容
Method:post
Param:id

/themessage 消息提醒
Method:post
Param:username,passwd,type,id

/findPassword 找回密码
Param:username,email

/changePassword 更改密码
Param:username,keyvalue

/Attention 关注
Param:username,passwd,targetid,method