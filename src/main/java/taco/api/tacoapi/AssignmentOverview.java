package taco.api.tacoapi;



import java.sql.*;
import java.time.LocalDate;


public class AssignmentOverview {


    public static ResultSet assignments(){

        LocalDate date = LocalDate.now();

        try{
            //this makes the connection

            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(link, "student","Student1");


            String queryAssignmentOverview = "select id, name, duedate, description FROM assignment where duedate>= "+date+" ;";

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(queryAssignmentOverview);

            // hier beslissen wat met de data te doen



            statement.close();
            return resultSet;
        }catch(Exception e){
            System.err.println(e.getMessage());}

        return null;
    }

}
