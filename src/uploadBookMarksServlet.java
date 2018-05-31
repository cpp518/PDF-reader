// 上传书签

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class uploadBookMarksServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,IOException {

        ConnectDatabase con = null;
        ResultSet rs = null;
        returnJson j = null;
        PrintWriter out = response.getWriter();
        //用户登录验证
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        int result = 0;
        result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(4,200,401);break;
            case 2:j = new returnJson(4,200,404);break;
            case 4:j = new returnJson(4,200,407);break;
            case 5:j = new returnJson(4,200,408);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }

        //


    }
}
