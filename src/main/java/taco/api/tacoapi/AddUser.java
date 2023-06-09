package taco.api.tacoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class AddUser {

    public static String addUser(String username, String firstname, String lastname, String password){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student","Student1");
            Statement statement = conn.createStatement();
            ResultSet ids = statement.executeQuery("SELECT SUM(CASE WHEN id IS NULL OR id = 'Invalid' THEN 0 ELSE 1 END) FROM user;");
            if(ids.next()){
                int idcount = Integer.parseInt(ids.getString(1)) + 1;
                statement.executeUpdate("INSERT INTO user VALUES ('"+ idcount +"','"+username+"','"+firstname+"','"+lastname+"','"+password+"' );");


                conn.close();
                return "true";
            }
            else
                return "false, bad/no ID";
        }catch(Exception e){
            System.err.println(e.getMessage());
            return "false," + e.getMessage();
        }
    }
}
