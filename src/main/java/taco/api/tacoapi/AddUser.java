package taco.api.tacoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class AddUser {

    public static boolean AddUser(String username, String firstname, String lastname, String password){



        try{

            String link = ""; //to be filled in
            Connection conn = DriverManager.getConnection(link, "username","password"); //again to be filled in
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO user VALUES ('"+username+"','"+firstname+"','"+lastname+"','"+password+"' );");


            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
}
