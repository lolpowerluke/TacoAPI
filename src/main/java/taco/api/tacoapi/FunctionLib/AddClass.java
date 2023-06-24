package taco.api.tacoapi.FunctionLib;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddClass {

    public static JSONObject Classadd(String location, String name, String description){
        String values = ",'" + location + "','" + name + "','" + description + "'";
        return executeUpdateJSON.executeUpdate(values, "class");
    }

    public boolean deleteClass(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student", "Student1");
            Statement statement = conn.createStatement();
            ResultSet deleteID = statement.executeQuery("DELETE FROM class WHERE id="+id+";");
            ResultSet deleteID2 = statement.executeQuery("DELETE FROM timeslot WHERE Class_id="+id+";");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
