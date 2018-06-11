//关注等相关操作
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class AttentionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html;charset=utf-8");
        returnJson j = null;
        ResultSet rs = null;
        PrintWriter out = null;
        int id = 0;
        String targetid = "";
        String username = "";
        String sql = "";
        String passwd = "";
        int result = 0;

        out = response.getWriter();
        //登录验证
        username=request.getParameter("username");
        passwd = request.getParameter("passwd");
        result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(19,200,401);break;
            case 2:j = new returnJson(19,200,404);break;
            case 4:j = new returnJson(19,200,407);break;
            case 5:j = new returnJson(19,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }
        id = 0;
        id = login.getId();
        targetid = request.getParameter("targetid");


        int method = Integer.valueOf(request.getParameter("method"));
        System.out.println(method);
        switch (method){
            //关注了谁
            case 1:
                sql = "SELECT targetid,username,nickname FROM attention,user WHERE targetid=user.id AND userid="+id+" order by username";
                System.out.println(sql);
                AttentionWho(sql,out);
                break;
            //关注
            case 2:
                sql = "INSERT INTO attention(userid,targetid) values ("+id+","+targetid+")";
             //   System.out.println(sql);

                Attention(sql,out);
                break;
            //取消关注
            case 3:
                sql = "DELETE FROM attention where targetid="+targetid;
             //   System.out.println(sql);
                AttentionCancel(sql,out);
                break;
            //被谁关注
            case 4:
                sql = "SELECT userid,username,nickname FROM attention,user WHERE targetid="+id+" AND user.id=userid order by username";
                System.out.println(sql);
                AttentionByWho(sql,out);
                break;
            //是否存在关系
            case 5:
                sql = "SELECT * FROM attention WHERE targetid="+targetid+" AND userid="+id+" OR targetid="+id+" AND userid="+targetid;
                System.out.println(sql);
                AttentionFind(sql,out,id);
        }
    }

    public void AttentionWho(String sql,PrintWriter out){
        ConnectDatabase con = null;
        returnJson j = null;
        ResultSet rs = null;
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(19,200,401,"查询关注对象失败");
            out.println(j.result());
            return;
        }
        try{
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(19,200,404,"查询关注对象失败");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }
        try{
            int num =0;
            j = new returnJson(19,100,400);
            if(rs.next()){
                returnJson k = new returnJson();
                do{
                    k.add(num,"targetid",rs.getString("targetid"),
                            "username",rs.getString("username"),
                            "nickname",rs.getString("nickname"));
                    num++;
                }while(rs.next());
                j.put("AttentionTotal",String.valueOf(num));
                j.put("Attention",k);
            }
            else{
                j.put("AttentionTotal","0");
                j.put("Attention","");
            }
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception ee){
                return;
            }
            return;
        }catch(Exception e){
            j = new returnJson(19,200,405,"查询关注对象失败");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }
    }

    public void Attention(String sql,PrintWriter out){
        ConnectDatabase con = null;
        returnJson j = null;
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(19,200,401,"关注失败");
            out.println(j.result());
            return;
        }
        try{
            con.ExecuteUpdate(sql);
            con.commit();
            if(!con.getResult()){
                try{
                    con.Close();
                }catch(Exception f){
                    return;
                }
                j = new returnJson(19,200,411,"关注失败");
                out.println(j.result());
                return;
            }
            j = new returnJson(19,100,400);
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception ee){
                return;
            }
            return;
        }catch(Exception e){
            con.rollback();
            j = new returnJson(19,200,404,"关注失败");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }
    }

    public void AttentionCancel(String sql,PrintWriter out){
        ConnectDatabase con = null;
        returnJson j = null;
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(19,200,401,"取消关注失败");
            out.println(j.result());
            return;
        }
        try{
            con.ExecuteUpdate(sql);
            con.commit();
            if(!con.getResult()){
                try{
                    con.Close();
                }catch(Exception f){
                    return;
                }
                j = new returnJson(19,200,411,"取消关注失败");
                out.println(j.result());
                return;
            }
            j = new returnJson(19,100,400);
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception ee){
                return;
            }
            return;
        }catch(Exception e){
            con.rollback();
            j = new returnJson(19,200,404,"取消关注失败");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }
    }

    public void AttentionByWho(String sql,PrintWriter out){
        ConnectDatabase con = null;
        returnJson j = null;
        ResultSet rs = null;
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(19,200,401,"查询被关注失败");
            out.println(j.result());
            return;
        }
        try{
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(19,200,404,"查询被关注失败");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }
        try{
            int num =0;
            j = new returnJson(19,100,400);
            if(rs.next()){

                returnJson k = new returnJson();
                do{
                    k.add(num,"userid",rs.getString("userid"),
                            "username",rs.getString("username"),
                            "nickname",rs.getString("nickname"));
                    num++;
                }while(rs.next());
                j.put("AttentionTotal",String.valueOf(num));
                j.put("Attention",k);
            }
            else{
                j.put("AttentionTotal","0");
                j.put("Attention","");
            }
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception ee){
                return;
            }
            return;
        }catch(Exception e){
            j = new returnJson(19,200,405,"查询被关注失败");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }

    }

    public void AttentionFind(String sql,PrintWriter out,int id){
        ConnectDatabase con = null;
        returnJson j = null;
        ResultSet rs = null;
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(19,200,401,"查询关系失败");
            out.println(j.result());
            return;
        }
        try{
            rs = con.Execute(sql);
        }catch(Exception e){
            j = new returnJson(19,200,404,"查询关系失败");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }
        try{
            j = new returnJson(19,100,400);
            int num = 0;
            int userid = 0;
            if(rs.next()){
                userid = Integer.valueOf(rs.getString("userid"));
                num++;
                if(rs.next()){
                    num++;
                }
            }
            switch(num){
                case 2:
                    j.put("relationship","3");
                    break;
                case 1:
                    rs.next();
                    if(id==userid){
                        j.put("relationship","2");
                    }
                    else{
                        j.put("relationship","1");
                    }
                    break;
                case 0:
                    j.put("relationship","0");
            }
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception ee){
                return;
            }
            return;
        }catch(Exception e){
            j = new returnJson(19,200,405,"查询关系失败");
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return;
            }
            return;
        }
    }





}
