package taco.api.tacoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class AddClass {

    public static boolean Classadd(String location, String name, String description){



        try{

            String link = ""; //to be filled in
            Connection conn = DriverManager.getConnection(link, "username","password"); //again to be filled in
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO class VALUES ('"+location+"','"+name+"','"+description+"' );");


            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
}
