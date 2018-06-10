//返回第一个贴

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class showFirstPostServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        ConnectDatabase con = null;
        returnJson j = null;
        ResultSet rs = null;
        String sql = null;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        sql = "SELECT post.id,title,username,createdate,lastdate FROM user,posttie,post WHERE userid=user.id AND comefrom=0 AND postid=post.id";
        //System.out.println(sql);
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(15,200,401);
            out.println(j.result());
            return;
        }



        try{
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(15,200,404);
            out.println(j.result());
            return;
        }

        try{
            j = new returnJson(15,100,400);
            int num = 0;
            returnJson k = new returnJson();
            if(rs.next()){
                //num是表示第几个条目
                do{
                    k.add(num,"id",rs.getInt("id"),
                            "title",rs.getString("title"),
                            "username",rs.getString("username"),
                            "createdate",rs.getString("createdate"),
                            "lastdate",rs.getString("lastdate"));
                    num++;
                    //System.out.println(rs.getString("title"));
                }while(rs.next());
                j.put("total",String.valueOf(num));
                j.put("posts",k);
            }
            //如果rs为空
            else{
                j.put("posts","");
            }
            out.println(j.result());
            return;
        }catch (Exception e){
            j = new returnJson(15,200,405);
            out.println(j.result());
            return;
        }
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doPost(request,response);
    }
}
