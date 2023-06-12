package taco.api.tacoapi.FunctionLib;

import org.json.JSONObject;

public class AddPersonal {

    public static JSONObject Personaladd(String location, String name, String description){
        String values = ",'" + location + "','" + name + "','" + description + "'";
        return executeUpdateJSON.executeUpdate(values, "personalactivity");
    }
}
