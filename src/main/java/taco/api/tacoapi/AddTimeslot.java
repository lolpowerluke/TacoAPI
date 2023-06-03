package taco.api.tacoapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AddTimeslot {

    public static boolean AddTimeslotAssignment(LocalDateTime time, int userid, int assignmentID){



        try{

            String link = ""; //to be filled in
            Connection conn = DriverManager.getConnection(link, "username","password"); //again to be filled in
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO timeslot VALUES ("+time.truncatedTo(ChronoUnit.SECONDS)+","+userid+","+assignmentID+",null,null );");


            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public static boolean AddTimeslotClass(LocalDateTime time, int userid, int ClassID){



        try{

            String link = ""; //to be filled in
            Connection conn = DriverManager.getConnection(link, "username","password"); //again to be filled in
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO timeslot VALUES ("+time.truncatedTo(ChronoUnit.SECONDS)+","+userid+",null,"+ClassID+",null );");


            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public static boolean AddTimeslotPersonal(LocalDateTime time, int userid, int PersonalActivityID){
        try{

            String link = ""; //to be filled in
            Connection conn = DriverManager.getConnection(link, "username","password"); //again to be filled in
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO timeslot VALUES ("+time.truncatedTo(ChronoUnit.SECONDS)+","+userid+",null,null,"+PersonalActivityID+" );");


            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
}