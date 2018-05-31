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
      //  PrintWriter out = response.getWriter();
        String num = request.getParameter("index");
        String path = request.getSession().getServletContext().getRealPath("upload/pdf/" + num + "/1.pdf");
        ServletOutputStream outps = null;
        returnJson j = null;
        InputStream fileIps = null;
       // path = "D:/学习/JAVA编程/javaweb/pdfpj/out/artifacts/pdfpj_war_exploded/upload/pdf/53/1.pdf";
       // path = "file";
        File file = new File(path);
        System.out.println(file.exists());
        response.setHeader("Content-Disposition","attachment;filename=1.pdf");
        System.out.println("2");
        try{
            System.out.println("3");
            outps = response.getOutputStream();
            System.out.println("4");

            fileIps = new FileInputStream(file);
            System.out.println("5");

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

