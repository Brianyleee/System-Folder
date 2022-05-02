package brgy_abella_system;

import java.sql.*;


public class Connector {
    
    public static Connection Connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:DatabaseAbella");
            return conn;
        }catch(Exception e){
            return null;
        }
    };
}
