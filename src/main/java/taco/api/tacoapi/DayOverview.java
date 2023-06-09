package taco.api.tacoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONObject;

public class DayOverview {
    enum type{
        Assignment,
        Class,
        Personal
    }

    public static ArrayList<ResultSet> dayView(LocalDate date){
        try{
            String bdatumfc = date + "/00/00/00";
            String edatumfc = date + "/23/59/59";

            //this makes the connection
            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student","Student1");



           /* MysqlDataSource dataSource= new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setUser("student");
            dataSource.setPassword("Student1");
            dataSource.setDatabaseName("database_taco");
            Connection connection = DriverManager.getConnection(dataSource);
*/
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

            conn.close();
            return resultSets;
        }catch(Exception e){
            System.out.println(e.getMessage());
        return null ;}
    }

    public static ArrayList<ArrayList<JSONObject>> dayViewtest(LocalDate date){
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
            resultSets.add(makeResult(resultSet1, type.Assignment));

            ResultSet resultSet2 = statement.executeQuery(queryClassSched);
            resultSets.add(makeResult(resultSet2, type.Class));
            

            ResultSet resultSet3 = statement.executeQuery(queryPersonalSched);
            resultSets.add(makeResult(resultSet3, type.Personal));
            
            statement.close();
            conn.close();
            return resultSets;
        }
        catch(Exception e){
            return null;
        }
    }

    private static ArrayList<JSONObject> makeResult(ResultSet resultSet, type t){
        try {
            ArrayList<JSONObject> jArray = new ArrayList<JSONObject>();
            while(resultSet.next()){
                JSONObject jObject = new JSONObject();
                jObject.put("id", resultSet.getString(1));
                jObject.put("time", resultSet.getString(2));
                jObject.put("name", resultSet.getString(3));
                jObject.put("description", resultSet.getString(4));
                if(t == type.Class || t == type.Personal)
                    jObject.put("location", resultSet.getString(5));
                else if (t == type.Assignment)
                    jObject.put("duedate", resultSet.getString(5));
                jObject.put("type", t);
                jArray.add(jObject);
            }
            return jArray;
        } catch (SQLException e) {
            return null;
        }
    }
}