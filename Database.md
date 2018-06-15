

username: pdfuser
password: 123456
database: pdfpj

#所有内容
mysqldump --no-defaults -u xxxx -p databasenae > filename
#仅表结构
mysqldump --no-defaults --opt -d -u xxxx -p databasenae > filename

table:
uesr:
-----------------------------------------------------------------------------------------------------------
id             | username  |   nickname |   passwd  |  registerDate| LastloginDate|  email    | permit    |
---------------|-----------|------------|-----------|--------------|--------------|-----------|-----------|
int primary key|varchar(20)| varchar(20)|varchar(20)|datetime      | datetime     |varchar(20)|tinyint(up)|
-----------------------------------------------------------------------------------------------------------
更新字段
UPDATE user set LastloginDate = now() where name = ''

book:
---------------------------------------------------------------------------------------------------------------------------------------------------------
userid  | bookid    | bookname  | author       | introduction | label     |introImage   |   type       | registerDate | page      |   state  | download | 
--------|-----------|-----------|--------------|--------------|-----------|-------------|--------------|--------------|-----------|----------|----------|
int     |int        |varchar(50)|varchar(50)   |varchar(2000) |varchar(50)| tinyint     |  varchar(50) |  datetime    | smallint  |  tinyint | int      |
--------|-----------|-----------|--------------|--------------|-----------|-------------|--------------|--------------|-----------|---------------------|
not null|not null   |not null   | not null     |not null      |not null   | default 0   | not null     | DEFAULT now()| default 0 | default 0|default 0 |
---------------------------------------------------------------------------------------------------------------------------------------------------------
foreign key(userid ) references user(id) on delete cascade;
state表示当前pdf书籍的状态：审核中：0
                            仅自己可见：1
							仅好友可见：2
							所有人可见：3


Bookmarks
------------------------------------------------------------------------------------------------------
id  | userid | bookid | createDate   | LastChangeDate|   title    |  content    | pagenum  |  state  |
----|--------|--------|--------------|---------------|------------|-------------|----------|---------|
int |int     | int    |datetime      | datemtime     | varchar(50)|varchar(2000)|smalint   |  tinyint|
------------------------------------------------------------------------------------------------------
foreign userid(int) references user(id) on delete cascade;
foreign bookid(int) references book(bookid) on delete cascade;
CreateDate     表示创建书签的日期
LastChangeDate 表示书签更改的时间
title 表示书签标题
content 表示书签的内容
pagenum 表示书签所在位置
state 表示书签是否共享：0 不共享
                        1 仅好友可见
						2 所有人可见
						




			
posttie
-------------------------------------------------------------
id | postid | type  | targetid|state  |	comefrom | lastdate |
---|--------|-------|---------|-------|----------|----------|
int| int    |tinyint| int     |tinyint| int      | datetime |
--------------------------------------------------------------
帖子的类型：
targetid:当type为1:0
         当type为2:头贴的id
comefrom：表示是哪个帖子下的回帖
poststate
----------------
id     |type   |
-------|-------|
tinyint|tinyint|
----------------
1:审核
2:发布
		 
posttype
-----------------
id     | type   |
-------|--------|
tinyint| tinyint|
-----------------
type:1-头贴，2-回帖
Post
--------------------------------------------------------
id | userid | title       | content       | createdate |
---|--------|-------------|---------------|------------|
int| int    | varchar(60) |text           |   datatime |
--------------------------------------------------------
						
select title,content from post,posttype where bookid = targetid 


attention
------------------------
id  | userid |targetid |
----|--------|---------|
int | int    | int     |
------------------------


agree
----------------------------------
id  | userid | targetid | type   |
----|--------|----------|--------|
int |int     | int      | tinyint|
----------------------------------

agreetype
-----------------
id     | type   |
-------|--------|
tinyint| tinyint|
-----------------

type:1、书本
     2、书签

message
-----------------------------------------------------
id   | userid | content | type  | createdate | jump |
-----|--------|---------|-------|------------|------|
int  | int    | text    |tinyint| datetime   | int  |
-----------------------------------------------------

messagetype
------------------
id       | type  |
---------|--------
tinyint  |tinyint|
------------------


findpassword
---------------------------------------
id  | userid | createdate| keyvalue   |
----|--------|-----------|------------|
int | int    |varchar(20)| varchar(70)|
--------------------------------------|




recentreadopen
-------------------------------------------------------------
id | userid | bookid | createdate | lastdate| page|totalpage|
---|--------|--------|------------|---------|-----|---------|
   |        |        |            |         |     |         |
-------------------------------------------------------------

recentreadclose
