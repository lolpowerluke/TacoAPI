package taco.api.tacoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AddPersonal {

    public static boolean Classadd(String location, String name, String description){



        try{

            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";
            Connection conn = DriverManager.getConnection(link, "student","Student1");
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO personalactivity VALUES ('"+location+"','"+name+"','"+description+"' );");


            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }

    }
}
