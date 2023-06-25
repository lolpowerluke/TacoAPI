package taco.api.tacoapi.FunctionLib;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class AddUser {
    public static JSONObject addUser(String username, String firstname, String lastname, String password){
        String values = ",'" + username + "','" + firstname + "','" + lastname + "','" + password + "'";
        return executeUpdateJSON.executeUpdate(values, "user",firstname);
    }
    public boolean deleteUser(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student", "Student1");
            Statement statement = conn.createStatement();
            ResultSet deleteID = statement.executeQuery("DELETE FROM user WHERE id="+id+";");
            ResultSet deleteID2 = statement.executeQuery("DELETE FROM timeslot WHERE User_id="+id+";");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
