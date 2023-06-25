package taco.api.tacoapi.FunctionLib;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import org.json.JSONObject;

public class AddAssignment {
    public static JSONObject Asignmentadd(int timeneeded, LocalDate duedate, String name, String description) {
        String values = ",'" + timeneeded + "','" + duedate + "','" + name + "','" + description + "'";
        return executeUpdateJSON.executeUpdate(values, "assignment",name);
    }


    public boolean deleteAssignment(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student", "Student1");
            Statement statement = conn.createStatement();
            ResultSet deleteID = statement.executeQuery("DELETE FROM assignment WHERE id="+id+";");
            ResultSet deleteID2 = statement.executeQuery("DELETE FROM timeslot WHERE Assignment_id="+id+";");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
