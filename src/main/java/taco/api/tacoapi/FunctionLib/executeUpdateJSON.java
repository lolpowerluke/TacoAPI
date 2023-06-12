package taco.api.tacoapi.FunctionLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

public class executeUpdateJSON{
    public static JSONObject executeUpdate(String values, String tablename) {
        JSONObject returnjson = new JSONObject();
        try{

            Class.forName("com.mysql.jdbc.Driver");
            String link = "jdbc:mysql://192.168.1.2:3306/database_taco";

            Connection conn = DriverManager.getConnection(link, "student","Student1");
            Statement statement = conn.createStatement();
            ResultSet ids = statement.executeQuery("SELECT MAX(id) FROM "+ tablename + ";");
            if(ids.next()){
                int id;
                if(ids.getString(1) != null)
                    id = Integer.parseInt(ids.getString(1)) + 1;
                else
                    id = 1;

                String query = "INSERT INTO " + tablename +" VALUES ('"+ id + "'" + values +");";
                statement.executeUpdate(query);
                
                conn.close();
                returnjson.put("succes", true);

                return returnjson;
            }
            else{
                returnjson.put("succes", false);
                returnjson.put("error", "bad/no ID");
                return returnjson;
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
            returnjson.put("succes", false);
            returnjson.put("error", e.getMessage());
            return returnjson;
        }
    }
}