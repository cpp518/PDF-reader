// 上传书签

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class uploadBookMarksServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,IOException {

        ConnectDatabase con = null;
        returnJson j = null;
        PrintWriter out = response.getWriter();
        //用户登录验证
        String sql = null;
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");

        int result = 0;
        result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(7,200,401);break;
            case 2:j = new returnJson(7,200,404);break;
            case 4:j = new returnJson(7,200,407);break;
            case 5:j = new returnJson(7,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }


        //上传笔记

        //用id判断是否存在
        String id = request.getParameter("id");

        //System.out.println(id);
        if(id == ""){
            sql = "INSERT INTO bookmarks(userid,bookid,createdate,lastchangedate,title,content,pagenum) values ("+login.getId()
                    +","+request.getParameter("bookid")
                    +",now(),now()"
                    +",\""+new String(request.getParameter("title").getBytes("ISO-8859-1"),"utf-8")
                    +"\",\""+new String(request.getParameter("content").getBytes("ISO-8859-1"),"utf-8")
                    +"\","+request.getParameter("pagenum")+")";
        }
        else{
            sql = "update bookmarks SET lastchangedate=now(),"
                    + " title = \""+ new String(request.getParameter("title").getBytes("ISO-8859-1"),"utf-8") +"\""
                    + ", content = \"" + new String(request.getParameter("content").getBytes("ISO-8859-1"),"utf-8") +"\""
                    + ", pagenum = " + request.getParameter("pagenum")
                    + " where id = " + id;
        }
        //System.out.println(sql);
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(7,200,401);
            out.println(j.result());
            return;
        }
        //执行sql语句
        try{
            con.ExecuteUpdate(sql);
            messageServlet.insertMessage(login.getId(),"上传书签成功",0,1);
        }catch (Exception e){
            con.rollback();
            if(!con.getResult()){
                j = new returnJson(7,200,410);
            }
            else {
                j = new returnJson(7, 200, 404);
            }
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }
        //事务提交
        try{
            con.commit();
            //System.out.println(con.getResult());
        }catch(Exception e){
            messageServlet.insertMessage(login.getId(),"上传书签失败",0,1);
            j = new returnJson(7,200,411);
            out.println(j.result());
            return;
        }

        //成功
        j = new returnJson(7,100,400);
        out.println(j.result());
        return;

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doPost(request,response);
        return;
    }
}
