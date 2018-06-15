
//--------------------
//|  注册页面         |
//|  2018/05/19       |
//--------------------


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

import java.util.Enumeration;


public class registerServlet extends HttpServlet{

    public ConnectDatabase database;
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //防止连接数据库错误
        try{
            database = new ConnectDatabase();
        }catch(Exception e){
            returnJson j = new returnJson(1,200,401);
            out.println(j.result());
            return;
        }
        //检查注册信息是否为空
        if(request.getParameter("name")=="" || request.getParameter("email")=="" || request.getParameter("nick")==""
                || request.getParameter("passwd")==""){
            returnJson j = new returnJson(1,200,402);
            out.println(j.result());
            return;
        }
        else{
            //检查username是否已经被使用
            String find = "SELECT username FROM user where username = '"+request.getParameter("name")+"'";
            try{
                ResultSet rs = database.Execute(find);
                if(database.getResult()){
                    if(!rs.next()){
                        //添加新用户到数据库
                        String sql = "INSERT INTO user(username,nickname,passwd,email,permit) values('"+request.getParameter("name")+"','"+request.getParameter("nick")+"','"+request.getParameter("passwd")+"','"+request.getParameter("email")+"',0)";
                        database.ExecuteUpdate(sql);
                        database.commit();
                        if(!database.getResult()){
                            returnJson j = new returnJson(1,200,411);
                            out.println(j.result());
                            return;
                        }
                        returnJson j = new returnJson(1,100,400);
                        out.println(j.result());
                        database.Close();
                    }
                    else{
                        returnJson j = new returnJson(1,200,403);
                        out.println(j.result());
                    }
                }
                else{
                    returnJson j = new returnJson(1,200,404);
                    out.println(j.result());
                }
            }catch(Exception e){
                database.rollback();
                if(!database.getResult()){
                    returnJson j = new returnJson(1,200,410);
                    out.println(j.result());
                    return;
                }
                returnJson j = new returnJson(1,200,405);
                out.println(j.result());
            }
        }
    }
}
