
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;

@WebServlet("/DisplayHeader")

public class DisplayHeader extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "HTTP Header 请求实例";
        String docType = "<!DOCTYPE html> \n";
        out.println("<html>\n"+"<head><meta charset=\"utf-8\"><title>"+title+"</title></head>\n"+
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<table width=\"100%\" border=\"1\" algin=\"center\">\n" +
                "<body bgcolor = \"#949494\">\n" +
                "<td>Header 名称</td><td>Header 值</td>\n"+
                "</tr>\n");

        Enumeration headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()){
            String paramName = (String)headerNames.nextElement();
            out.print("<tr><td>"+ paramName +"</td>\n");
            String paramValue = request.getHeader(paramName);
            out.println("<td> " + paramValue+"</td></tr>\n");
        }
        out.println("</table>\n</body></html>");

    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doGet(request,response);
    }
}
