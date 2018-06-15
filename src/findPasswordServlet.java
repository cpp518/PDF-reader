//邮箱找回密码
import org.apache.commons.mail.HtmlEmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class findPasswordServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        String username = request.getParameter("username");
        String mail = request.getParameter("email");
        returnJson j = null;
        ConnectDatabase con = null;
        ResultSet rs = null;
        String sql = null;
        String id = null;
        boolean result = true;
        PrintWriter out = response.getWriter();
        try{
            con = new ConnectDatabase();
        }catch(Exception e){
            j = new returnJson(23,200,401);
            out.println(j.result());
            return;
        }

        try{
            sql = "SELECT id FROM user WHERE username= '"+username+"' AND email='"+mail+"'";
            System.out.println(sql);
            rs = con.Execute(sql);
            if(rs.next()){
                id = rs.getString("id");
                result = true;
            }
            else{
                result = false;
            }
        }catch(Exception e){
            j = new returnJson(23,200,405);
            out.println(j.result());
            return;
        }

        if(result){
            request.getRemoteAddr();
            String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            BigInteger date = new BigInteger(time);
            String s = username + mail+time+"A1B2C3D4E5F6";
            String str = null;
            try {
                MessageDigest dig = MessageDigest.getInstance("SHA-256");
                dig.update(s.getBytes());
                byte[] b = dig.digest();
                str = byte2String(b);
            //    System.out.println(str);
            }catch(Exception e){
                j = new returnJson(23,200,405);
                out.println(j.result());
                return;
            }
            sql = "INSERT INTO findpassword(userid,createdate,keyvalue) values ("+id+",'"+time+"','"+str+"')";
        //    System.out.println("findPassword: "+sql);
            try{
                con.ExecuteUpdate(sql);
                con.commit();
                if(!con.getResult()){
                    con.Close();
                    j = new returnJson(23,200,411);
                    out.println(j.result());
                    return;
                }
            }catch(Exception e){
                j = new returnJson(23,200,404);
                out.println(j.result());
                try{
                    con.Close();
                }catch(Exception f){
                    return ;
                }
                return;
            }
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.qq.com");
            email.setSmtpPort(25);
            String userName = "";
            String password = "";
            email.setAuthentication(userName,password);
            try{
                email.setFrom(userName);
                email.setCharset("utf-8");
                email.setHtmlMsg("<a href=http://127.0.0.1:8080/pdfpj/changepassword.jsp?keyvalue="+str+">点击此处修改密码</a>");
         //       System.out.println("http://127.0.0.1:8080/pdfpj/changepassword.jsp?keyvalue="+str);
                email.setSSLOnConnect(true);
                email.setSubject("听说你要修改密码");
                email.addTo(mail);
                email.send();
            }catch(Exception e){
               // e.printStackTrace();
               // System.out.println("发送失败！！！");
                j = new returnJson(23,200,420);
                out.println(j.result());
                return;
            }
            System.out.println("发送成功！！！");
            j = new returnJson(23,100,400);
            j.put("id",str);
            out.println(j.result());
            return;
        }
        else{
            j = new returnJson(23,200,417);
            out.println(j.result());
            return;
        }
    }

    public static String  byte2String (byte []b){
        char[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char []c = new char[b.length*2];
        int index = 0;
        for(byte t:b){
            c[index++] = hex[t >>>4 & 0xf];
            c[index++] = hex[t & 0xf];
        }
        return new String(c);
    }


}
