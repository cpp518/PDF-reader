
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class ConnectDatabase {

    private String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private String DBURL = "jdbc:mysql://localhost:3306/pdfpj?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private String DBUSER = "pdfuser";
    private String DBPASS = "123456";
    private boolean success = false;
    private Connection conn;
    public Statement stat;
    public ConnectDatabase ()throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        stat = conn.createStatement();
        conn.setAutoCommit(false);
    }

    public ResultSet Execute(String sql) {

        try{
            ResultSet rs = stat.executeQuery(sql);
            success = true;
            return rs;
        }catch( SQLException e){
            success = false;
            return null;
        }
    }

    public void ExecuteUpdate(String sql){
        try{
            int k = stat.executeUpdate(sql);
           // System.out.println(k);
            success = k>0?true:false;
            //System.out.println(success);
            return;
        }catch(SQLException e){
            success = false;
            return;
        }
    }

    public void commit() {
        try{
            conn.commit();
            success = true;
            return;
        }catch(SQLException e ){
            success = false;
            return;
        }
    }

    public void rollback(){
        try{
            conn.rollback();
            success = true;
            return;
        }catch(SQLException e){
            success = false;
            return;
        }
    }

    public boolean getResult(){
        return success;
    }

    public void Close()throws Exception{
        stat.close();
        conn.close();
    }
}
