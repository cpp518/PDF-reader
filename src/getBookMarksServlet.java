//获取书签

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class getBookMarksServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        returnJson j = null;
        ConnectDatabase con = null;
        String sql = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        int result = 0;
        result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(11,200,401);break;
            case 2:j = new returnJson(11,200,404);break;
            case 4:j = new returnJson(11,200,407);break;
            case 5:j = new returnJson(11,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }
        int id = login.getId();
        String type= request.getParameter("type");
        if(type.equals("ALL")){
            sql = "SELECT bookid,createdate,lastchangedate,title,content,pagenum,state FROM bookmarks WHERE userid = " + id;
        }
        else{
            String bookid = request.getParameter("bookid");
            sql = "SELECT bookid,createdate,lastchangedate,title,content,pagenum,state FROM bookmarks WHERE userid = " + id + " AND bookid = " + bookid;
        }
       // System.out.println(sql);
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(11,200,401) ;
            out.println(j.result());
            return;
        }
        try{
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(11,200,404);
            out.println(j.result());
            return;
        }
        try{
            j = new returnJson(11,100,400);
            int num = 0;
            returnJson k = new returnJson();
            if(rs.next()){
                //num是表示第几个条目
                do{
                    k.add(num,"bookid",rs.getInt("bookid"),
                            "createdate",rs.getString("createdate"),
                            "lastchangedate",rs.getString("lastchangedate"),
                            "title",rs.getString("title"),
                            "content",rs.getString("content"),
                           "pagenum",rs.getInt("pagenum"),
                            "state",rs.getInt("state"));

                    num++;

                }while(rs.next());
                j.put("total",String.valueOf(num));
                j.put("bookmarks",k);
            }
            //如果rs为空
            else{
                j.put("total",String.valueOf(num));
                j.put("bookmarks","");
            }
            out.println(j.result());
            return;
        }catch (Exception e){
            j = new returnJson(11,200,405);
            out.println(j.result());
            return;
        }


    }

    public  void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
        return;
    }
}
