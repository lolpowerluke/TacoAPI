package taco.api.tacoapi;

import java.sql.*;

public class AssignmentOverview {


    public static void assignments(){

        try{
            //this makes the connection
            String driver = ""; //to be filled in
            String link = ""; //to be filled in
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(link, "username","password"); //again to be filled in

            String queryAssignmentOverview = "select id, name, duedate, description FROM assignment;";

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(queryAssignmentOverview);

            // hier beslissen wat met de data te doen

            statement.close();

        }catch(Exception e){
            System.err.println(e.getMessage());}
    }
}
