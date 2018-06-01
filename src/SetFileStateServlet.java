//设置上传文件和书签的状态
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class SetFileStateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        returnJson j = null;
        ConnectDatabase con = null;
        String sql = null;
        PrintWriter out = response.getWriter();
        int result = 0;
        result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(10,200,401);break;
            case 2:j = new returnJson(10,200,404);break;
            case 4:j = new returnJson(10,200,407);break;
            case 5:j = new returnJson(10,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }
        int id = login.getId();
        String state = request.getParameter("state");
        String bookid = request.getParameter("bookid");
        String type = request.getParameter("type");
        if(type.equals("book")){
             sql = "UPDATE book SET state=" + state + " where bookid = "+bookid +" AND userid = " + id;
        }
        else{
             sql = "UPDATE bookmarks SET state=" + state + " where id = "+bookid ;
        }
        System.out.println(sql);
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(10,200,400);
            out.println(j.result());
            return;
        }
        try{
            con.ExecuteUpdate(sql);
        }catch(Exception e){
            j = new returnJson(10,200,404);
            out.println(j.result());
            return;
        }
        try{
            con.commit();
        }catch(Exception e){
            j = new returnJson(10,200,411);
            out.println(j.result());
            return;
        }
        j = new returnJson(10,100,400);
        out.println(j.result());
        return;
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        doPost(request,response);
        return;
    }
}
