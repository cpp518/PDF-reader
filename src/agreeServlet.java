// 点赞

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class agreeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ConnectDatabase con = null;
        ResultSet rs = null;
        returnJson j = null;
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        int result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(22,200,401);break;
            case 2:j = new returnJson(22,200,404);break;
            case 4:j = new returnJson(22,200,407);break;
            case 5:j = new returnJson(22,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }
        int type = Integer.valueOf(request.getParameter("type"));
        boolean r = true;
        //操作类型：1-点赞，2-取消点赞，3-点过的赞
        switch(type){
            case 1:
                r = agreeWith(login.getId(),Integer.valueOf(request.getParameter("targetid")),Integer.valueOf(request.getParameter("agreetype")));
                if(r){
                    j = new returnJson(22,100,400);
                    out.println(j.result());
                }
                else{
                    j = new returnJson(22,200,415);
                    out.println(j.result());
                }
                break;
            case 2:
                r = agreeCancel(login.getId(),Integer.valueOf(request.getParameter("targetid")),Integer.valueOf(request.getParameter("agreetype")));
                if(r){
                    j = new returnJson(22,100,400);
                    out.println(j.result());
                }
                else{
                    j = new returnJson(22,200,416);
                    out.println(j.result());
                }
                break;
            case 3:
                rs = agreeBefore(login.getId());
                try{
                    j = new returnJson(22,100,400);
                    returnJson k = new returnJson();
                    int num = 0;
                    if(rs.next()){
                        do{

                            k.add(num,"id",rs.getString("id"),
                                    "targetid",rs.getString("targetid"),
                                    "type",rs.getString("type"),
                                    "createdate",rs.getString("createdate"));
                            num++;
                        }while(rs.next());
                        j.put("agreeTotal",String.valueOf(num));
                        j.put("agree",k);
                    }
                    else{
                        j.put("agreeTotal","0");
                        j.put("agree","");
                    }
                    out.println(j.result());
                }catch(Exception e){
                    j = new returnJson(22,200,405);
                    out.println(j.result());
                    return;
                }
                break;
        }


    }

    //点赞
    public static boolean agreeWith(int userid,int targetid,int type){
        String sql = "INSERT INTO agree(userid,targetid,type) values ("+userid+","+targetid+","+type+")";
        String sql2 = null;
        String sql3 = null;
        ResultSet rs = null;
        if(type==1){
            sql2 = "UPDATE book SET agree=agree+1 WHERE bookid="+targetid;
            sql3 = "SELECT userid FROM book WHERE bookid="+targetid;
        }
        else{
            sql2= "UPDATE bookmarks SET agree=agree+1 WHERE id="+targetid;
            sql3 = "SELECT userid FROM bookmarks WHERE id="+targetid;
        }

        System.out.println("agreeSql: "+sql);
        ConnectDatabase con = null;
        ConnectDatabase con2 = null;
        try{
            con = new ConnectDatabase();
            con2 = new ConnectDatabase();
        }catch(Exception e){
            return false;
        }
        try{
            rs = con2.Execute(sql3);
            rs.next();
            int id = rs.getInt("userid");
            messageServlet.insertMessage(id,"有人给你点赞啦！",0,1);
            con.ExecuteUpdate(sql);
            con.ExecuteUpdate(sql2);
            con.commit();
            if(!con.getResult()){
                con.Close();
                return false;
            }
        }catch(Exception e){
            try{
                con.Close();
            }catch(Exception f){

            }
            return false;
        }
        try{
            con.Close();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    //取消点赞
    public static boolean agreeCancel(int userid,int targetid,int type){
        String sql1 = "DELETE FROM agree WHERE userid="+userid+" AND targetid="+targetid+" AND type="+type;
        String sql2 = null;
        if(type==1){
            sql2 = "UPDATE book SET agree=agree-1 WHERE bookid="+targetid;
        }
        else{
            sql2= "UPDATE bookmarks SET agree=agree-1 WHERE id="+targetid;
        }
        System.out.println(sql1);
        ConnectDatabase con = null;
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            return false;
        }
        try{
            con.ExecuteUpdate(sql1);
            con.ExecuteUpdate(sql2);
            con.commit();
            if(!con.getResult()){
                return false;
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }

    //点过的赞
    public static ResultSet agreeBefore(int id){
        String sql = "SELECT id,targetid,type,createdate FROM agree WHERE userid="+id+" order by createdate";
        ConnectDatabase con = null;
        ResultSet rs = null;
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            return null;
        }
        try{
            rs = con.Execute(sql);
        }catch (Exception e){
            return null;
        }
        return rs;
    }
}
