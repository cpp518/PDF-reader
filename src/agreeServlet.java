// 点赞

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

public class agreeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ConnectDatabase con = null;
        ResultSet rs = null;
        returnJson j = null;



    }

    //点赞

    //取消点赞

    //点过的赞
    public static ResultSet agreeBefore(int id){
        String sql = "SELECT id,targetid,type,createdate FROM agree WHERE userid="+id+" AND order by createdate";

    }
    //
}
