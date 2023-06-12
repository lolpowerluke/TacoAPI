package taco.api.tacoapi.FunctionLib;

import org.json.JSONObject;

public class AddClass {

    public static JSONObject Classadd(String location, String name, String description){
        String values = ",'" + location + "','" + name + "','" + description + "'";
        return executeUpdateJSON.executeUpdate(values, "class");
    }
}
