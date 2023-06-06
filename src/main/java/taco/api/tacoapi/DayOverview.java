package taco.api.tacoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DayOverview {

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
}
