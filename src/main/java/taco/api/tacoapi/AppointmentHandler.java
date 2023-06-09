package taco.api.tacoapi;

////////////////////////////////////////////////////////////////////////
/////////////////////////////IMPORTS////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//intellisense
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONObject;

//mapping
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//own klasses/imported libraries
import taco.api.tacoapi.FunctionLib.*;
import taco.api.tacoapi.Klasses.*;

////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//defines class als een rest controller
@RestController
// /Appointment
@RequestMapping("/Appointment")
public class AppointmentHandler {

    //Temp list ==> "database"
    public ArrayList<Appointment> totallyRealDatabase = new ArrayList<Appointment>();

    // /Appointment/create?name=[fillIn]&desc=[fillIn]
    //@GetMapping("/create")
    //public String createAppointment(@RequestParam(value = "name") String name, @RequestParam(value = "desc") String desc) throws IOException{

    // /Appointment/get?name=[fillIn]
    //@GetMapping("/get")
    //public String getAppointment(@RequestParam(value = "name") String name) throws IOException{
     
    // /appointment/remove?name=[fillIn]
    //@GetMapping("/remove")
    //public String removeAppointment(@RequestParam(value = "name") String name) throws IOException{

    @GetMapping("/dayView")
    public String dayView(@RequestParam(value ="date") LocalDate date) throws JsonProcessingException {
        //http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/dayView?date=2023-05-05
        return JSONhelper.getDefaultObjectMapper().writeValueAsString(DayOverview.dayView(date));
    }

    @GetMapping("/dayViewtest")
    public String dayViewtest(@RequestParam(value ="date") LocalDate date) throws JsonProcessingException {
        try {
            //http://192.168.1.2:8080/tacoapi-TacoAPI.1.0.0/Appointment/dayViewtest?date=2023-05-05
            ArrayList<ArrayList<JSONObject>> jArrayList = DayOverview.dayViewtest(date);
            JSONObject json = new JSONObject();
            json.put("assignments", jArrayList.get(0));
            json.put("classes", jArrayList.get(1));
            json.put("personal", jArrayList.get(2));
            
            return DayOverview.dayViewtest(date).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/weekView")
    public String weekView(@RequestParam(value= "startday") LocalDate startday,@RequestParam(value="endday") LocalDate endday) throws JsonProcessingException {

        return JSONhelper.getDefaultObjectMapper().writeValueAsString(WeekOverview.weekView(startday,endday));
    }

    @GetMapping("/monthView")
    public String monthView(@RequestParam(value= "startday") LocalDate startday,@RequestParam(value="endday") LocalDate endday) throws JsonProcessingException {

        return JSONhelper.getDefaultObjectMapper().writeValueAsString(MonthOverview.monthView(startday,endday));
    }

    @GetMapping("/AssignmentView")
    public String AssignmentView () throws JsonProcessingException {

        return JSONhelper.getDefaultObjectMapper().writeValueAsString(AssignmentOverview.assignments());
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
