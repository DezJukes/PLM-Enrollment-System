package registration_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
   public static Connection Connect(){
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
            System.out.println("Connection Established successfully");
            //return con;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return con;
    } 
}
