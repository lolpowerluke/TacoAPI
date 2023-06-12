package taco.api.tacoapi.FunctionLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONObject;

import taco.api.tacoapi.Classes.*;

public class DayOverview {
        public static ArrayList<ArrayList<JSONObject>> dayView(LocalDate date){
        try{
            String bdatumfc = date + " 00:00:00";
            String edatumfc = date + " 23:59:59";

            //this makes the connection
            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student","Student1");

            String queryPersonalSched = "select t.id,time, name, description, location FROM timeslot t JOIN PersonalActivity p ON (t.PersonalActivity_id = p.id) WHERE t.time between '"+ 
                                        bdatumfc + "' and '" + edatumfc +"';";

            String queryClassSched = "select t.id, time, name,description,location FROM timeslot t join class c on (t.class_id = c.id) WHERE t.time between '" + 
                                     bdatumfc + "' and '" + edatumfc +"';";

            String queryAssignmentSched = "SELECT t.id, time, name,description,duedate FROM timeslot t join assignment a on (t.assignment_id = a.id) WHERE t.time between '"+ 
                                          bdatumfc + "' and '" + edatumfc +"';";

            Statement statement = conn.createStatement();
            ArrayList<ArrayList<JSONObject>> resultSets = new ArrayList<>();

            ResultSet resultSet1 = statement.executeQuery(queryAssignmentSched);
            resultSets.add(MakeResult.makeResult(resultSet1, type.Assignment));

            ResultSet resultSet2 = statement.executeQuery(queryClassSched);
            resultSets.add(MakeResult.makeResult(resultSet2, type.Class));
            

            ResultSet resultSet3 = statement.executeQuery(queryPersonalSched);
            resultSets.add(MakeResult.makeResult(resultSet3, type.Personal));
            
            statement.close();
            conn.close();
            return resultSets;
        }
        catch(Exception e){
            return null;
        }
    }
}