package taco.api.tacoapi.FunctionLib;
import org.json.JSONObject;



public class AddUser {
    public static JSONObject addUser(String username, String firstname, String lastname, String password){
        String values = ",'" + username + "','" + firstname + "','" + lastname + "','" + password + "'";
        return executeUpdateJSON.executeUpdate(values, "user");
    }
}
