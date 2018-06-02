import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class downloadFileServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-msdownload");
        ConnectDatabase con = null;
      //  PrintWriter out = response.getWriter();
        String num = request.getParameter("index");
        String path = request.getSession().getServletContext().getRealPath("upload/pdf/" + num + "/1.pdf");
        ServletOutputStream outps = null;
        returnJson j = null;
        InputStream fileIps = null;
        File file = new File(path);
        //System.out.println(file.exists());
        response.setHeader("Content-Disposition","attachment;filename=1.pdf");
        try{
            outps = response.getOutputStream();
            fileIps = new FileInputStream(file);
            byte[] b = new byte[1024];
            int i= 0;
            while((i=fileIps.read(b))>0){
                outps.write(b,0,i);
            }
            outps.flush();
            /*j = new returnJson(6,100,400);
            out.println(j.result());*/
            outps.close();
            fileIps.close();
            try{
                con = new ConnectDatabase();
            }catch(Exception e){
                j = new returnJson(6,200,401);
                outps.println(j.result());
                return;
            }
            try{
                String sql = "UPDATE book SET download = download + 1 WHERE bookid="+num;
                con.ExecuteUpdate(sql);
                con.commit();
            }catch(Exception e){
                j = new returnJson(6,200,404);
                outps.println(j.result());
                return;
            }
            return;
        }catch(Exception e){
            /*j = new returnJson(6,200,412);
            out.println(j.result());
            outps.close();
            fileIps.close();*/
            return;
        }
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}

