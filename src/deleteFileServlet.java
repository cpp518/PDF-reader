import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class deleteFileServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        ConnectDatabase con = null;
        response.setContentType("text/html;charset=utf-8");
        String num = request.getParameter("bookid");
        String sql = null;
        returnJson j = null;
        PrintWriter out = response.getWriter();
        //用户登录验证
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        int result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(12,200,401);break;
            case 2:j = new returnJson(12,200,404);break;
            case 4:j = new returnJson(12,200,407);break;
            case 5:j = new returnJson(12,200,408);break;
        }

        if(result!=3){
            out.println(j.result());
            return;
        }

        //删除数据库记录
        sql = "DELETE FROM book WHERE bookid = "+ num +" AND userid="+login.getId();
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(12,200,401);
            out.println(j.result());
            return;
        }
        System.out.println(sql);
        try{
            con.ExecuteUpdate(sql);
            if(!con.getResult()){
                throw new Exception("error");
            }
            con.commit();
        }catch(Exception e){
            con.rollback();
            try{
                con.Close();
            }catch(Exception t){
                j = new returnJson(12,200,413);
                out.println(j.result());
                return;
            }
            j = new returnJson(12,200,404,"没有权限");
            out.println(j.result());
            return;
        }

        try{
            con.Close();
        }catch(Exception e){
            j = new returnJson(12,100,413);
            out.println(j.result());
            return;
        }
        //删除本地文件
        try{
            String path = request.getSession().getServletContext().getRealPath("upload/pdf/" +num);
            File file = new File(path);
            File []files = file.listFiles();
            for(int i=0;i<files.length;i++){
                files[i].delete();
            }
            file.delete();
        }catch(Exception e){
            j = new returnJson(12,200,414);
            out.println(j.result());
            return;
        }

        j = new returnJson(12,100,400);
        out.println(j.result());
        return;

    }
}
