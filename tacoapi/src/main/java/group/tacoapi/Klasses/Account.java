package group.tacoapi.Klasses;

public class Account {
    //ID of user
    public final int id;
    //Name of user
    public final String username;
    //Password of user
    public final String password;

    //constructor for account => Account "" = new Account();
    public Account(int id, String username, String password) {
        //set id
        this.id = id;
        //set username
        this.username = username;
        //set password
        this.password = password;
    }
}
