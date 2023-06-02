package taco.api.tacoapi;

////////////////////////////////////////////////////////////////////////
/////////////////////////////IMPORTS////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//intellisense
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    @GetMapping("/create")
    public String createAppointment(@RequestParam(value = "name") String name, @RequestParam(value = "desc") String desc) throws IOException{

        //make new date for appointment => check other appointments
        LocalDateTime date = LocalDateTime.now();
        
        //make new account with data from parameters
        Appointment app = new Appointment(name, desc, date);
        //add account to database
        totallyRealDatabase.add(app);

        //return een json van het acc object
        return JSONhelper.getDefaultObjectMapper().writeValueAsString(app);
    }

    // /Appointment/get?name=[fillIn]
    @GetMapping("/get")
    public String getAppointment(@RequestParam(value = "name") String name) throws IOException{
        
        //temp vars
        Appointment app = new Appointment(null, null, null);
        boolean found = false;

        //zoek app in de lijst
        for (Appointment appointment : totallyRealDatabase) {
            if(appointment.appName.equals(name)){
                app = appointment;
                found = true;
                break;
            }
        }

        //als app is gevonden
        if(found)
            //return een json van het acc object
            return JSONhelper.getDefaultObjectMapper().writeValueAsString(app);
        else
            return "This appointment doesn't exist in our database!";
    }

    // /Appointment/getAll
    @GetMapping("/getAll")
    public String getAllAppointments() throws IOException{
        //if count of names in database > 0
        if(!totallyRealDatabase.isEmpty())
            //return een json van de list met acc objecten
            return JSONhelper.getDefaultObjectMapper().writeValueAsString(totallyRealDatabase);

        //return dat de lijst leeg is
        return String.format("List is empty!");
    }

    // /appointment/remove?name=[fillIn]
    @GetMapping("/remove")
    public String removeAppointment(@RequestParam(value = "name") String name) throws IOException{



            //removed de appointment met de gegeven naam
            for (Appointment appointment : totallyRealDatabase) {
                if(appointment.appName.equals(name)){
                    totallyRealDatabase.remove(appointment);
                    return "Succes!";
                }
            }

        return "Failed, appointment doesn't exist!";
    }


    @GetMapping("/dayView")
    public String dayView(@RequestParam(value ="day") LocalDate day) throws JsonProcessingException {

        return JSONhelper.getDefaultObjectMapper().writeValueAsString(DayOverview.dayView(day));
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
}
