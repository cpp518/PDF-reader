import java.sql.ResultSet;
import java.sql.*;

public class login {
    private static ConnectDatabase database;
    private static String sql1="SELECT * FROM user WHERE username = ";
    private static String sql2="' AND passwd= '";
    private static Boolean result;
    private static int id = 0;

    public static int in(String username,String passwd){
        ResultSet rs = null;
        result = false;
        try{
            database = new ConnectDatabase();
        }catch(Exception e) {
            return 1;
        }
        try{
            rs =database.Execute(sql1+"'"+username+sql2+passwd+"'");
        }catch(Exception e){
            return 2;
        }
        try{
            if(rs.next()){
                try{
                    String sql = "UPDATE user SET lastlogindate=now() WHERE username = '"+username+"'";
                    database.ExecuteUpdate(sql);
                    //System.out.println(sql);
                }catch(Exception e){
                    database.rollback();
                    if(!database.getResult()){
                        return 2;
                    }
                }
                database.commit();
                try{
                    String sql = "SELECT id from user where username=" + "'" + username + "'";
                    rs = database.Execute(sql);
                }catch(Exception e){
                    return 2;
                }
                database.commit();
                rs.next();
                id = rs.getInt("id");
                result = true;
                return 3;
            }
            else{
                return 4;
            }
        }catch(Exception e){
            return 5;
        }

    }

    public static int getId(){
        return id;
    }

    public static boolean getResult(){
        return result;
    }
}
