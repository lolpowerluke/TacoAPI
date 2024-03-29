package taco.api.tacoapi;

////////////////////////////////////////////////////////////////////////
/////////////////////////////IMPORTS////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//intellisense
import org.json.JSONException;
import org.json.JSONObject;

//mapping
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//own klasses/imported libraries
import taco.api.tacoapi.FunctionLib.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//defines class als een rest controller
@RestController
@RequestMapping("/Account")
public class AccountHandler {
    
    //  /Account/create?username=[fillIn]&password=[fillIn]&firstname=[fillIn]&lastname=[fillIn]
    @GetMapping("/create")
    public String createAccount(@RequestParam(value="username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname){
        //return een json van het acc object
        JSONObject succes = AddUser.addUser(username, firstname, lastname, password);

        return succes.toString();
    }

    @GetMapping("/check")
    public String check(@RequestParam(value="username") String username, @RequestParam(value="password") String password){
        JSONObject jObject = new JSONObject();
        JSONObject jObject2 = new JSONObject();
        String usernameV="";
        String passwordV="";
        int idV=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student","Student1");


            String queryAssignmentOverview = "SELECT username, password, id FROM user WHERE username = '"+username+"';";

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(queryAssignmentOverview);

            try {
                while (resultSet.next()) {

                     usernameV=( resultSet.getString(1));
                     passwordV=( resultSet.getString(2));
                     idV=( resultSet.getInt(3));



                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            statement.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        if(usernameV.equals(username) &&passwordV.equals(password)){
                jObject.put("success",true);
                jObject.put("id", idV);
                return jObject.toString();


        }else {
            jObject2.put("success", false);
            return jObject2.toString();
        }
    }

    // /Account/get?id=[fillIn]
    /*@GetMapping("/get")
    public String getAccountByUsername(@RequestParam("ID") int id) throws IOException{
        //get account by ID
        Account acc = totallyRealDatabase.get(id);

        //return een json van het acc object
        return JSONhelper.getDefaultObjectMapper().writeValueAsString(acc);
    }

    // /Account/getAll
    @GetMapping("/getAll")
    public String getAllAccounts() throws IOException{
        //if count of names in database > 0
        if(!totallyRealDatabase.isEmpty())
            //return een json van de list met acc objecten
            return JSONhelper.getDefaultObjectMapper().writeValueAsString(totallyRealDatabase);

        //return dat de lijst leeg is
        return String.format("List is empty!");
    }*/
}