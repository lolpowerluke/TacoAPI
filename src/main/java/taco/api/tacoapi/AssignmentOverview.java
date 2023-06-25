package taco.api.tacoapi;



import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
// /Appointment
@RequestMapping("/Appointment")
public class AssignmentOverview {

    @GetMapping("/getAssignments")
    public  String assignments(){

        LocalDate date = LocalDate.now();
        ArrayList<JSONObject> jArray = new ArrayList<JSONObject>();
        ArrayList<ArrayList<JSONObject>> jArray2 = new ArrayList<ArrayList<JSONObject>>();
        try{
            //this makes the connection
            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student","Student1");


            String queryAssignmentOverview = "SELECT id, timeneeded, duedate, name, description FROM assignment WHERE duedate >= '"+date+"';";

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(queryAssignmentOverview);



            try {
                while (resultSet.next()) {
                    JSONObject jObject = new JSONObject();
                    jObject.put("id", resultSet.getString(1));
                    jObject.put("timeneeded", resultSet.getString(2));
                    jObject.put("duedate", resultSet.getString(3));
                    jObject.put("name", resultSet.getString(4));
                    jObject.put("description", resultSet.getString(5));

                    jArray.add(jObject);

                }
                jArray2.add(jArray);
            }catch (JSONException e){
                e.printStackTrace();
            }
            statement.close();
            conn.close();


            return jArray2.toString();
        }catch(Exception e){
            System.err.println(e.getMessage());}

        return null;
    }

    @GetMapping("/getAssignment")
    public String singleAssignment(@RequestParam(value="name") String name){
        ArrayList<JSONObject> jArray = new ArrayList<JSONObject>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student","Student1");


            String queryAssignmentOverview = "SELECT id, timeneeded, duedate, name, description FROM assignment WHERE name = '"+name+"';";

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(queryAssignmentOverview);

            try {
                while (resultSet.next()) {
                    JSONObject jObject = new JSONObject();
                    jObject.put("id", resultSet.getString(1));
                    jObject.put("timeneeded", resultSet.getString(2));
                    jObject.put("duedate", resultSet.getString(3));
                    jObject.put("name", resultSet.getString(4));
                    jObject.put("description", resultSet.getString(5));

                    jArray.add(jObject);

                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            statement.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return jArray.toString();
    }

}
