package taco.api.tacoapi;

////////////////////////////////////////////////////////////////////////
/////////////////////////////IMPORTS////////////////////////////////////
////////////////////////////////////////////////////////////////////////

//intellisense
import java.io.IOException;
import java.util.ArrayList;

//mapping
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
// /Account
@RequestMapping("/Account")
public class AccountHandler {
    
    //Temp list ==> "database"
    public ArrayList<Account> totallyRealDatabase = new ArrayList<Account>();

    //  /Account/create?username=[fillIn]&password=[fillIn]&firstname=[fillIn]&lastname=[fillIn]
    @GetMapping("/create")
    public String createAccount(@RequestParam("username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname){
        //return een json van het acc object
        String succes = AddUser.addUser(username, firstname, lastname, password);

        if(succes.split(",").length > 1)
            return succes.split(",")[0] + ", error: " + succes.split(",")[1];
        else
            return succes;
    }

    // /Account/get?id=[fillIn]
    @GetMapping("/get")
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
    }
}