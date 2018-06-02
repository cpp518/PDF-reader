//获取所有上传的图书的信息

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class showFileServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        returnJson j = null;
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("utf-8");
        ConnectDatabase con = null;
        ResultSet rs = null;
        String sql = "SELECT bookid,bookname,author,introduction,label,introImage,type,registerDate,page,state,download from book where state=3";
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(5,200,401);
            out.println(j.result());
            return;
        }
        try{
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(5,200,404);
            out.println(j.result());
            return;
        }
        try{
            //如果rs不为空
            j = new returnJson(5,100,400);
            int num = 0;
            returnJson k = new returnJson();
            if(rs.next()){
                do{
                    k.add(num,"bookid",rs.getInt("bookid"),
                            "bookname",rs.getString("bookname"),
                            "author",rs.getString("author"),
                            "introduction",rs.getString("introduction"),
                            "label",rs.getString("label"),
                            "introImage",rs.getInt("introImage"),
                            "type",rs.getString("type"),
                            "registerDate",rs.getString("registerDate"),"page",rs.getInt("page"),
                            "state",rs.getInt("state"),
                            "download",rs.getInt("download"));
                    num++;

                }while(rs.next());
                j.put("book",k);
            }
            //如果rs为空
            else{
                j.put("book","");
            }
            //System.out.println(j.result());
            out.println(j.result());
            return;
        }catch(Exception e){
            j = new returnJson(5,200,409);
            out.println(j.result());
            return;
        }



    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doPost(request,response);
    }
}
