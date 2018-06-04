//删除书签

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class deleteBookMarksServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        ConnectDatabase con = null;
        String sql = null;
        returnJson j = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        //用户验证
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        int result = 0;
        int id = 0;
        result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(13,200,401);break;
            case 2:j = new returnJson(13,200,404);break;
            case 4:j = new returnJson(13,200,407);break;
            case 5:j = new returnJson(13,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }
        id = login.getId();
        //删除数据库记录
        //删除的书签编号
        String num = request.getParameter("num");
       //select 找出第num条记录，再获取它的id，delete
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(13,200,401);
            out.println(j.result());
            return;
        }
        //获取要删除的书签的id
        try{
            sql = "SELECT id FROM bookmarks where userid = "+id;
            System.out.println(sql);
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(13,200,404);
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception k){
                return;
            }
            return;
        }
        //数出要删除书签的id
        try{
            int n = Integer.valueOf(num);
            do{
                n--;
                rs.next();
            }while(n>0);
            id = rs.getInt("id");
        }catch(Exception e){
            j = new returnJson(13,200,405);
            out.println(j.result());
            return;
        }
        //删除书签
        try{
            sql = "DELETE FROM bookmarks WHERE id = "+id;
            System.out.println(sql);
            con.ExecuteUpdate(sql);
            if(!con.getResult()){
                con.rollback();
                j = new returnJson(13,200,404);
                out.println(j.result());
                try{
                    con.Close();
                }catch(Exception t){
                   return;
                }
            }
            con.commit();
        }catch(Exception e){
            j = new returnJson(13,200,411);
            out.println(j.result());
            return;
        }
        //
        j = new returnJson(12,100,400);
        out.println(j.result());
        return;
    }
}
