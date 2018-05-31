//上传文件

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class UploadServlet
 */

public class UploadFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static  String UPLOAD_DIRECTORY = null;

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 30;  // 30MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 400; // 400MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 500; // 500MB

    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 检测是否为多媒体上传

        //上传文件的文件夹命名
        //UPLOAD_DIRECTORY = UPLOAD_DIRECTORY;
/*        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }*/

        //用户登录判断
        PrintWriter out = response.getWriter();
        String sql;
        returnJson j = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        List<FileItem> formItems = null;
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            formItems = upload.parseRequest(request);
        }catch(Exception e){
            j = new returnJson(2,200,408);
            out.println(j.result());
            return;
        }

        int result = 0;
        String str = formItems.get(9).getString();
        String username = formItems.get(0).getString();
        String passwd = formItems.get(1).getString();
        result = login.in(username,passwd);
        switch(result){
            case 1:j = new returnJson(3,200,401);break;
            case 2:j = new returnJson(3,200,404);break;
            case 4:j = new returnJson(3,200,407);break;
            case 5:j = new returnJson(3,200,405);break;
        }
        if(result!=3){
            out.println(j.result());
            return;
        }
        // 配置上传参数



        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("utf-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录

        try {
            // 解析请求的内容提取文件数据
            //@SuppressWarnings("unchecked")
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        fileName = "1" + fileName.substring(fileName.lastIndexOf("."),fileName.length()).toLowerCase();
                        //System.out.println(fileName);
                        ConnectDatabase con = new ConnectDatabase();
                        ResultSet rs = null;
                        int id = login.getId(username);
                       /* try{
                            sql = "UPDATE user SET lastlogindate=now() WHERE id="+id;
                            con.ExecuteUpdate(sql);
                            System.out.println(sql);
                        }catch(Exception e){
                            con.rollback();
                            if(!con.getResult()){
                                j = new returnJson(2,200,410);
                            }
                            else {
                                j = new returnJson(2, 200, 404);
                            }
                            out.println(j.result());
                            return;
                        }
                        con.commit();*/
                        if(!con.getResult()){
                            j = new returnJson(2,200,411);
                            out.println(j.result());
                            return;
                        }
                        //如果上传的是文件，则加入到数据库
                        if(str.equals("FILE")){
                            UPLOAD_DIRECTORY = "upload/pdf/";
                            if(!login.getResult()){
                                switch(id){
                                    case 1:j = new returnJson(2,200,401);break;
                                    case 2:j = new returnJson(2,200,404,"get id error");break;
                                    case 5:j = new returnJson(2,200,405);break;
                                }
                                out.println(j.result());
                                return;
                            }
                            try {

                                sql = "INSERT INTO book(userid,bookname,author,introduction,label,type,page)values("
                                        + id + ",'" + new String(formItems.get(2).getString().getBytes("ISO-8859-1"),"utf-8") + "','"
                                        + new String(formItems.get(3).getString().getBytes("ISO-8859-1"),"utf-8") + "','"
                                        + new String(formItems.get(4).getString().getBytes("ISO-8859-1"),"utf-8") + "','"
                                        + new String(formItems.get(5).getString().getBytes("ISO-8859-1"),"utf-8") + "','"
                                        + new String(formItems.get(6).getString().getBytes("ISO-8859-1"),"utf-8") + "','"
                                        + new String(formItems.get(7).getString().getBytes("ISO-8859-1"),"utf-8")+ "')";
                              //  System.out.println(sql);
                                con.ExecuteUpdate(sql);

                                con.commit();
                                if(!con.getResult()){
                                    j = new returnJson(2,200,411);
                                    out.println(j.result());
                                    return;
                                }

                            }catch(Exception e){

                                con.rollback();
                                if(!con.getResult()){
                                    j = new returnJson(2,200,410);
                                }
                                else {
                                    j = new returnJson(2, 200, 404, "INSERT sql error");
                                }
                                out.println(j.result());
                                return;
                            }
                        }
                        else{
                            UPLOAD_DIRECTORY = "upload/pic/";
                        }
                        //System.out.println(UPLOAD_DIRECTORY);
                        //获取加入数据库后的id值并加入到filePath中
                        try {
                            sql = "SELECT MAX(bookid) FROM book";
                            rs = con.Execute(sql);
                        }catch(Exception e){
                            j = new returnJson(2,200,404,"get max bookid error");
                            out.println(j);
                            return;
                        }
                        try {
                            rs.next();
                            id = rs.getInt("MAX(bookid)");
                        }catch(Exception e){
                            j = new returnJson(2,200,405);
                            out.println(j.result());
                            return;
                        }
                        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY+id;
                        // 如果目录不存在则创建
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        //System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                       /* request.setAttribute("message",
                                "文件上传成功!");*/
                        j = new returnJson(2,100,400);
                        out.println(j.result());
                    }
                }
            }
        } catch (Exception ex) {
            j = new returnJson(2,200,406);
            out.println(j.result());
        }
        // 跳转到 message.jsp
     /*   getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);*/
    }
}