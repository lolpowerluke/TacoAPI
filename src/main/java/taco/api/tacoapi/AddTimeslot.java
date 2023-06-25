package taco.api.tacoapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
@RestController
@RequestMapping("/Create")
public class AddTimeslot {

    @GetMapping("/AssignmentTimeslot")
    public static boolean AddTimeslotAssignment(@RequestParam(value="time") LocalDateTime time,@RequestParam(value="id") int userid,@RequestParam(value="assignmentID") int assignmentID){
        try{

            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";
            Connection conn = DriverManager.getConnection(link, "student","Student1");
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO timeslot ( time, User_id, Assignment_id) VALUES ('"+time+"', "+userid+" , "+assignmentID+" );");



            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    @GetMapping("/ClassTimeslot")
    public static boolean AddTimeslotClass(@RequestParam(value="time") LocalDateTime time,@RequestParam(value="id") int userid,@RequestParam(value="classID") int ClassID){



        try{

            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";
            Connection conn = DriverManager.getConnection(link, "student","Student1");
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO timeslot ( time, User_id, Class_id) VALUES ('"+time+"', "+userid+" , "+ClassID+" );");


            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    @GetMapping("/PersonalTimeslot")
    public static boolean AddTimeslotPersonal(@RequestParam(value="time") LocalDateTime time,@RequestParam(value="id") int userid,@RequestParam(value="personalActivityID") int PersonalActivityID){
        try{

            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";
            Connection conn = DriverManager.getConnection(link, "student","Student1");
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO timeslot ( time, User_id, PersonalActivity_id) VALUES ('"+time+"', "+userid+" , "+PersonalActivityID+" );");


            conn.close();
            return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
}
