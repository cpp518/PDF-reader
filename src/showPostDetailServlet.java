//查看帖子内容


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class showPostDetailServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        response.setContentType("text/html;charset=utf-8");
        ConnectDatabase con = null;
        ResultSet rs = null;
        returnJson j = null;
        returnJson j2= null;
        String sql = null;
        String sql2 = null;

        int num2 = 0;
        int num3 = 0;
        PrintWriter out = response.getWriter();

        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(16,200,401);
            out.println(j.result());

            return;
        }

        int id = Integer.valueOf(request.getParameter("id"));

        //显示帖子内容
        sql = "SELECT post.id AS id,userid,username,title,content,createdate,lastdate FROM posttie,post,user WHERE userid=user.id AND post.id="+id+" AND postid=post.id";
        //System.out.println(sql);
        try{
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(16,200,404,"获取帖子错误");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception ee){
                return;
            }
            return;
        }

        try{
            returnJson k = new returnJson();
            returnJson l = new returnJson();
            LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
            ArrayList<LinkedHashMap<String,Object>> listMap = new ArrayList<LinkedHashMap<String, Object>>();
            rs.next();
            id = rs.getInt("id");
            map.put("userid",rs.getInt("userid"));
            map.put("username",rs.getString("username"));
            map.put("postid",id);
            map.put("title",rs.getString("title"));
            map.put("content",rs.getString("content"));
            map.put("createdate",rs.getString("createdate"));
            map.put("lastdate",rs.getString("lastdate"));
            String result;
            sql2 = "SELECT post.id AS id,userid,username,content,createdate FROM posttie,post,user WHERE userid=user.id AND targetid="+id+" AND postid=post.id";
            try{
                rs = con.Execute(sql2);
            }catch(Exception e){
                j = new returnJson(16,200,404,"获取回帖错误");
                out.println(j.result());
                return;
            }
            try{
                if(rs.next()){
                    do{
                        LinkedHashMap<String,Object> map2= new LinkedHashMap<String,Object>();
                        map2.put("userid",rs.getInt("userid"));
                        map2.put("username",rs.getString("username"));
                        map2.put("postid",rs.getString("id"));
                        map2.put("content",rs.getString("content"));
                        map2.put("createdate",rs.getString("createdate"));
                        listMap.add(map2);
                    }while(rs.next());
                    for(int i=0;i<listMap.size();i++) {
                        returnJson n = new returnJson();
                        id = Integer.valueOf(String.valueOf(listMap.get(i).get("postid")));

                        sql2 = "SELECT post.id AS id,userid,username,content,createdate FROM posttie,post,user WHERE userid=user.id AND targetid=" + id + " AND postid=post.id";
                        try {
                            rs = con.Execute(sql2);
                        } catch (Exception e) {
                            j = new returnJson(16, 200, 404, "获取回帖2错误");
                            out.println(j.result());
                            try {
                                con.Close();
                            } catch (Exception ee) {
                                return;
                            }
                            return;
                        }
                        try {
                            if (rs.next()) {
                                do {
                                    n.add(num3, "userid", rs.getInt("userid"),
                                            "username", rs.getString("username"),
                                            "postid", rs.getInt("id"),
                                            "content", rs.getString("content"),
                                            "createdate", rs.getString("createdate")
                                    );
                                    num3++;
                                } while (rs.next());
                                result = n.result();
                            } else {
                                result = "";
                            }
                        }catch(Exception h){
                            h.printStackTrace();
                            try{
                                con.Close();
                            }catch(Exception ee){
                                return;
                            }
                            j = new returnJson(16,200,409,"获取回帖2死了");
                            out.println(j.result());
                            return;
                        }
                        l.add(i,"userid",listMap.get(i).get("userid"),
                                "username",listMap.get(i).get("username"),
                                "postid",listMap.get(i).get("postid"),
                                "content",listMap.get(i).get("content"),
                                "createdate",listMap.get(i).get("createdate"),
                                "total",String.valueOf(num3),
                                "responses",result
                        );
                    }
                    result = l.result();
                }
                else{
                    result = "";
                }
                k.add(0,"userid",map.get("userid"),
                        "username",map.get("username"),
                        "postid",map.get("id"),
                        "title",map.get("title"),
                        "content",map.get("content"),
                        "createdate",map.get("createdate"),
                        "lastdate",map.get("lastdate"),
                        "total",String.valueOf(listMap.size()),
                        "responses",result);

            }catch(Exception f){
                j = new returnJson(16,200,409,"获取回帖死了");
                out.println(j.result());
                try{
                    con.Close();
                }catch(Exception ee){
                    return;
                }
                return;
            }
            j = new returnJson(16,100,400);
            j.put("post",k);
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception ee){
                return;
            }
            return;
        }catch(Exception e){
            j = new returnJson(16,200,409);
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception ee){
                return;
            }
            return;
        }

    }
}
