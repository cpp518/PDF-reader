//修改密码
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class changePasswordServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,
            IOException {
        ConnectDatabase con = null;
        returnJson j = null;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String passwd = request.getParameter("passwd");
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        BigInteger date = new BigInteger(time);
        String sql = null;
        String keyValue = request.getParameter("keyvalue");
        ResultSet rs = null;
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(24,200,401);
            out.println(j.result());
            return;
        }
        sql = "SELECT userid,createdate,keyvalue FROM findpassword WHERE keyvalue='"+keyValue+"'";
   //     System.out.println(sql);
        try{
            rs = con.Execute(sql);
        }catch(Exception e){
            return;
        }
        try{

            if(rs.next()){

                String userid = rs.getString("userid");

                BigInteger date2 = new BigInteger(rs.getString("createdate"));

                if(date.subtract(date2).compareTo(new BigInteger("1800")) <=1){
                    sql = "UPDATE user SET passwd = '"+passwd+"' WHERE id="+userid;
                 //   System.out.println(sql);
                    con.ExecuteUpdate(sql);
                    con.commit();
                }
                else{
                    j = new returnJson(24,200,419,"过期");
                    out.println(j.result());
                    con.Close();
                    return;
                }
            }
            else{
                j = new returnJson(24,200,419);
                out.println(j.result());
                con.Close();
                return;
            }
        }catch(Exception e){
            j = new returnJson(24,200,405);
            out.println(j.result());
            try{
                con.Close();
            }catch(Exception f){
                return ;
            }
            return;
        }
        j = new returnJson(24,100,400);
        out.println(j.result());
        return;

    }
}
