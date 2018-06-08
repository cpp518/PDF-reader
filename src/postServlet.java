// 发帖
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class postServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        ConnectDatabase con = null;
        returnJson j = null;
        String sql = null;
        String sql2 = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        //用户登录验证
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        String type = request.getParameter("type");
        String targetid = request.getParameter("targetid");
        String comefrom = request.getParameter("comefrom");
        int result = 0;
        int id = 0;
        result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(14,200,401);break;
            case 2:j = new returnJson(14,200,404);break;
            case 4:j = new returnJson(14,200,407);break;
            case 5:j = new returnJson(14,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }
        id = login.getId();

        //插入数据库post表
        String title = new String(request.getParameter("title").getBytes("ISO-8859-1"),"utf-8");
        String content =new String(request.getParameter("content").getBytes("ISO-8859-1"),"utf-8");
        sql = "INSERT INTO post(userid,title,content) values("+id+",\""+title+"\",\""+content+"\")";
       // System.out.println(sql);
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(14,200,401);
            out.println(j.result());
            return;
        }
        try{
            con.ExecuteUpdate(sql);
            if(!con.getResult()){
                j = new returnJson(13,200,403,"insert post record error");
                out.println(j.result());
                try{
                    con.Close();
                }catch(Exception f){
                    return;
                }
                return;
            }
            con.commit();
        }catch(Exception e){
            con.rollback();
            j = new returnJson(13,200,411,"post error");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }

        //获取刚刚插入的记录id
        sql = "SELECT LAST_INSERT_ID()";
        try{
            rs = con.Execute(sql);
            if(!con.getResult()){
                j = new returnJson(13,200,403,"get id error");
                out.println(j.result());
                try{
                    con.Close();
                }catch(Exception f){
                    return;
                }
                return;
            }
            rs.next();
            id = rs.getInt("LAST_INSERT_ID()");
        }catch(Exception e){
            j = new returnJson(14,200,405);
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }

        //插入数据库posttie表
        if(Integer.valueOf(type).equals(1)){
            sql = "INSERT INTO posttie(postid,type,targetid,state,comefrom,lastdate) values("+id+",1,1,2,0,now())";
        }
        else{
            sql = "INSERT INTO posttie(postid,type,targetid,state,comefrom,lastdate) values("+id+",2,"+Integer.valueOf(targetid)+",2,"+Integer.valueOf(comefrom)+",now())";
            sql2 = "UPDATE posttie SET lastdate=now() WHERE id="+Integer.valueOf(comefrom);
        }
       // System.out.println(sql);
        try{
            con.ExecuteUpdate(sql);
            con.ExecuteUpdate(sql2);
            if(!con.getResult()){
                j = new returnJson(13,200,403,"insert postite record error");
                out.println(j.result());
                try{
                    con.Close();
                }catch(Exception f){
                    return;
                }
                return;
            }
            con.commit();
        }catch(Exception e){
            con.rollback();
            j = new returnJson(13,200,411,"posttie error");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }


        j = new returnJson(13,100,400);
        out.println(j.result());
        try{
            con.Close();
        }catch(Exception f){
            return;
        }
        return;



    }
}
