package taco.api.tacoapi;

////////////////////////////////////////////////////////////////////////
/////////////////////////////IMPORTS////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//intellisense
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONObject;

//mapping

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//own klasses/imported libraries
import taco.api.tacoapi.FunctionLib.*;

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
            return DayOverview.dayView(date).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/weekView?day=2023-05-01
    @GetMapping("/weekView")
    public String weekView(@RequestParam(value= "day") LocalDate startday) {
        ArrayList<ArrayList<ArrayList<JSONObject>>> jsonMonth = new ArrayList<ArrayList<ArrayList<JSONObject>>>();

        try {
            int bdag = 0;
            if(startday.getDayOfWeek().getValue() > 1)
                bdag = startday.getDayOfMonth() - startday.getDayOfWeek().getValue();
            else
                bdag = startday.getDayOfMonth();

            for(int i = bdag; i < bdag + 7; i++){
                LocalDate date = LocalDate.of(startday.getYear(), startday.getMonth(), i);
                jsonMonth.add(DayOverview.dayView(date));
            }
            return jsonMonth.toString();
        } catch (Exception e) {
            return null;
        }
    }

    // http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/monthView?startday=2023-05-01
    @GetMapping("/monthView")
    public String monthView(@RequestParam(value= "startday") LocalDate startday){
        ArrayList<ArrayList<ArrayList<JSONObject>>> jsonMonth = new ArrayList<ArrayList<ArrayList<JSONObject>>>();
        try {
            for(int i = startday.getDayOfMonth(); i <= startday.lengthOfMonth(); i++){
                LocalDate date = LocalDate.of(startday.getYear(), startday.getMonth(), i);
                jsonMonth.add(DayOverview.dayView(date));
            }
            return jsonMonth.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
