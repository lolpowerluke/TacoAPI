package taco.api.tacoapi.FunctionLib;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import taco.api.tacoapi.Classes.*;

public class MakeResult {
    public static ArrayList<JSONObject> makeResult(ResultSet resultSet, type t){
        try {
            ArrayList<JSONObject> jArray = new ArrayList<JSONObject>();
            while(resultSet.next()){
                JSONObject jObject = new JSONObject();
                jObject.put("id", resultSet.getString(1));
                jObject.put("time", resultSet.getString(2));
                jObject.put("name", resultSet.getString(3));
                jObject.put("description", resultSet.getString(4));
                if(t == type.Class || t == type.Personal)
                    jObject.put("location", resultSet.getString(5));
                else if (t == type.Assignment)
                    jObject.put("duedate", resultSet.getString(5));
                jObject.put("type", t);
                jArray.add(jObject);
            }
            return jArray;
        } catch (SQLException e) {
            return null;
        }
    }
}
