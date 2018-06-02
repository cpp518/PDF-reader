//获取上传文件的信息

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class getUploadFileServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        //登录账号验证
       // response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        returnJson j = null;
        ResultSet rs = null;
        ConnectDatabase con = null;
        String sql = null;
        PrintWriter out = response.getWriter();
        int result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(4,200,401);break;
            case 2:j = new returnJson(4,200,404);break;
            case 4:j = new returnJson(4,200,407);break;
            case 5:j = new returnJson(4,200,408);break;
        }

        if(result!=3){
            out.println(j.result());
            return;
        }

        // 获取user的id
        int userId = 0;
        userId = login.getId();
        if(!login.getResult()){
            switch(userId){
                case 1:j = new returnJson(4,200,401);break;
                case 2:j = new returnJson(4,200,404);break;
                case 5:j = new returnJson(4,200,405);break;
            }
            out.println(j.result());
            return;
        }
        //返回信息
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(4,200,401);
            out.println(j.result());
            return;
        }
        try{
            sql = "SELECT bookid,bookname,author,introduction,label,introImage,type,registerDate,page,state,download from book where userid = '"+userId+"'";
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(4,200,404);
            out.println(j.result());
            return;
        }
        try{
            //如果rs不为空
            j = new returnJson(4,100,400);
            int num = 0;
            returnJson k = new returnJson();
            if(rs.next()){
                //num是表示第几个条目
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
            out.println(j.result());
            return;
        }catch(Exception e){
            j = new returnJson(4,200,409);
            out.println(j.result());
            return;
        }
    }
}
