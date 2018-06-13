//消息提醒

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class messageServlet extends HttpServlet {
    private static ConnectDatabase con = null;
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        ResultSet rs = null;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        returnJson j = null;
        int result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(21,200,401);break;
            case 2:j = new returnJson(21,200,404);break;
            case 4:j = new returnJson(21,200,407);break;
            case 5:j = new returnJson(21,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }
        switch(Integer.valueOf(request.getParameter("type"))) {
            case 1:
                rs = getMessage(login.getId());
                try {
                    j = new returnJson(21, 100, 400);
                    returnJson k = new returnJson();
                    if (rs.next()) {
                        int num = 0;
                        do {
                            k.add(num, "id", rs.getString("id"),
                                    "userid", rs.getString("userid"),
                                    "username", rs.getString("username"),
                                    "content", rs.getString("content"),
                                    "type", rs.getString("type"),
                                    "createdate", rs.getString("createdate"),
                                    "jumppost",rs.getString("jumppost"),
                                    "jumpuser",rs.getString("jumpuser"));
                            num++;
                        } while (rs.next());
                        j.put("message", k);
                    } else {
                        j.put("message", "");
                    }
                    out.println(j.result());
                } catch (Exception e) {
                    j = new returnJson(21, 200, 405);
                    out.println(j.result());
                    return;
                }
                break;
            case 2:
                changeMessage(login.getId());
        }
    }
    public static ResultSet getMessage(int id){
        ResultSet rs = null;
        String getSql = "SELECT message.id,userid,username,content,type,createdate,jumppost,jumpuser FROM user,message WHERE userid="+id+" AND user.id=userid order by type,createdate desc";
        System.out.println("getSql: "+getSql);
        try{
            con = new ConnectDatabase();
        }catch (Exception e){
            return null;
        }
        try{
            rs = con.Execute(getSql);
            return rs;
        }catch (Exception e){
            return null;
        }

    }

    public static boolean insertMessage(int userid,String content,int jump,int  type){
            int id = userid;
            String insertSql = null;
            if(type==3) {
                insertSql = "INSERT INTO message(userid,content,type,createdate,jumppost) values (" + id + ",\"" + content + "\",1,now()," + jump + ")";
            }
            else if(type==2){
                insertSql = "INSERT INTO message(userid,content,type,createdate,jumpuser) values (" + id + ",\"" + content + "\",1,now()," + jump + ")";
            }
            else{
                insertSql = "INSERT INTO message(userid,content,type,createdate) values (" + id + ",\"" + content + "\",1,now())";
            }
            System.out.println("insertSql: "+insertSql);
            try{
                con = new ConnectDatabase();
            }catch (Exception e){
                return false;
            }
            try{
                con.ExecuteUpdate(insertSql);
                con.commit();
                if(!con.getResult()){
                    return  false;
                }
            }catch(Exception e){
                try{
                    con.Close();
                }catch (Exception f){
                    return false;
                }
            }

        return true;
    }

    public static boolean changeMessage(int id){
        ConnectDatabase con = null;
        String changeSql = "UPDATE message SET type=2 WHERE id="+id;
        System.out.println("changeSql: "+changeSql);
        try{
            con = new ConnectDatabase();
        }catch (Exception e){
            return false;
        }
        try{
            con.ExecuteUpdate(changeSql);
            con.commit();
        }catch(Exception e){
            try{
                con.Close();
            }catch (Exception f){
                return false;
            }
            return false;
        }
        try{
            con.Close();
        }catch (Exception f){
            return false;
        }
        return  true;
    }

}
