package taco.api.tacoapi;

////////////////////////////////////////////////////////////////////////
/////////////////////////////IMPORTS////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//intellisense
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONObject;

//mapping

import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//own klasses/imported libraries
import taco.api.tacoapi.FunctionLib.*;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//defines class als een rest controller
@RestController
// /Appointment
@RequestMapping("/Appointment")
public class AppointmentHandler {

    // http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/createAssignment?timeneeded=4&due=2023-05-03&name=test&desc=hello world
    @GetMapping("/createAssignment")
    public String createAssignment(@RequestParam(value = "time") int timeneeded, @RequestParam(value = "due") LocalDate duedate, @RequestParam(value = "name") String name, @RequestParam(value = "desc") String desc){
        return AddAssignment.Asignmentadd(timeneeded, duedate, name, desc).toString();
    }

    // http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/createClass?location=A0%20A1&name=test&desc=hello%20world
    @GetMapping("/createClass")
    public String createClass(@RequestParam(value = "location") String location, @RequestParam(value = "name") String name, @RequestParam(value = "desc") String desc){
        return AddClass.Classadd(location, name, desc).toString();
    }

    // http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/createPersonal?location=teststraat%2013&name=test&desc=hello%20world
    @GetMapping("/createPersonal")
    public String createPersonal(@RequestParam(value = "location") String location, @RequestParam(value = "name") String name, @RequestParam(value = "desc") String desc){
        return AddPersonal.Personaladd(location, name, desc).toString();
    }

    // http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/dayViewtest?date=2023-05-05
    @GetMapping("/dayView")
    public String dayView(@RequestParam(value ="date") LocalDate date){
        try {

            return DayOverview.dayView(date,date).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/weekView?day=2023-05-01
    @GetMapping("/weekView")
    public String weekView(@RequestParam(value= "day") LocalDate startday) {
        ArrayList<ArrayList<ArrayList<JSONObject>>> jsonMonth = new ArrayList<ArrayList<ArrayList<JSONObject>>>();
        LocalDate endday= startday.plusDays(6);
        try {



                jsonMonth.add(DayOverview.dayView(startday, endday));

            return jsonMonth.toString();
        } catch (Exception e) {
            return null;
        }
    }

    // http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/monthView?startday=2023-05-01
    @GetMapping("/monthView")
    public String monthView(@RequestParam(value= "startday") LocalDate startday){
        ArrayList<ArrayList<ArrayList<JSONObject>>> jsonMonth = new ArrayList<ArrayList<ArrayList<JSONObject>>>();
        LocalDate endday= startday.with(lastDayOfMonth());
        try {

                jsonMonth.add(DayOverview.dayView(startday,endday));

            return jsonMonth.toString();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/AssignmentPlan")
    public ArrayList returnPossibles(@RequestParam(value = "duedate") LocalDate duedate){
        ArrayList<LocalDateTime> possibleArray= new ArrayList();
        ArrayList<LocalDateTime> filledArray= new ArrayList();
        ArrayList<String> testArray= new ArrayList();
        testArray.add("testing");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate i = LocalDate.now();
        boolean factor= false;
        try {
            Class.forName("com.mysql.jdbc.Driver");

        String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

        Connection conn = DriverManager.getConnection(link, "student","Student1");

        String query = "select time FROM timeslot  WHERE time between '"+
                i + "' and '" + duedate +"';";
            Statement statement = conn.createStatement();
            ResultSet resultSet1 = statement.executeQuery(query);

            while (resultSet1.next()){
                Timestamp tempTimestamp= resultSet1.getTimestamp("time");
                Instant tempvalue = tempTimestamp.toInstant();
                LocalDateTime tempDT= LocalDateTime.ofInstant(tempvalue, ZoneOffset.systemDefault());
                String temp= tempDT.format(formatter);
                LocalDateTime DT =LocalDateTime.parse(temp,formatter);
                filledArray.add(DT);
            }
            statement.close();
            conn.close();

            while ( factor==false){

                for (int h=16;h<24;h++){
                    for (int m=0;m<60;m=m+30){
                        LocalDateTime testTime=i.atTime(h,m,00);
                        if (filledArray.contains(testTime)==false){
                            possibleArray.add(testTime);
                        }
                    }

                }

                i=i.plusDays(1);
                if (i.equals(duedate)){
                    factor=true;
                }
            }

        return possibleArray;

        } catch (Exception e) {
            throw new ArithmeticException("error");
        }
    }
}
