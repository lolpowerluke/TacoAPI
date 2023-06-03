package taco.api.tacoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DayOverview {

    public static ArrayList<ResultSet> dayView(String date){
        try{
            String bdatumfc = "STR_TO_DATE('" + date + " 00:00:00', '%D-%m-%Y %H:%i:%S')";
            String edatumfc = "STR_TO_DATE('" + date + " 23:59:59', '%D-%m-%Y %H:%i:%S')";
            
            //this makes the connection
            String driver = ""; //to be filled in
            String link = ""; //to be filled in
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(link, "username","password"); //again to be filled in

            String queryPersonalSched = "select t.id,time, PersonalActivity_id, name, description, location\n" +
                    "FROM timeslot t\n" +
                    "JOIN PersonalActivity p ON (t.PersonalActivity_id = p.id)\n" +
                    "where time between "+ bdatumfc +" and "+ edatumfc + "\n" +
                    "Order by time asc;";

            String queryClassSched= "select t.id, time, Class_id, name,description,location\n" +
                    "FROM timeslot t\n" +
                    "join class c on (t.class_id = c.id)\n" +
                    "where time between "+ bdatumfc +" and "+ edatumfc + "\n" +
                    "order by time asc;\n";

            String queryAssignmentSched= "select t.id, time, Assignment_id, name,description,duedate\n" +
                    "FROM timeslot t\n" +
                    "join assignment a on (t.assignment_id = a.id)\n" +
                    "where time between "+ bdatumfc +" and "+ edatumfc + "\n" +
                    "order by time asc;";

            Statement statement = conn.createStatement();

            ResultSet resultSet1 = statement.executeQuery(queryAssignmentSched);
            ResultSet resultSet2 = statement.executeQuery(queryClassSched);
            ResultSet resultSet3 = statement.executeQuery(queryPersonalSched);

            // hier beslissen wat met de data te doen

            ArrayList<ResultSet> resultSets = new ArrayList<>();
            resultSets.add(resultSet1);
            resultSets.add(resultSet2);
            resultSets.add(resultSet3);

            statement.close();
            return resultSets;
        }catch(Exception e){
            System.err.println(e.getMessage());}
        return null;
    }
}
