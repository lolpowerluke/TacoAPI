package taco.api.tacoapi.FunctionLib;
import java.time.LocalDate;

import org.json.JSONObject;

public class AddAssignment {
    public static JSONObject Asignmentadd(int timeneeded, LocalDate duedate, String name, String description){
        String values = ",'" + timeneeded + "','" + duedate + "','" + name + "','" + description + "'";
        return executeUpdateJSON.executeUpdate(values, "assignment");
    }
}
