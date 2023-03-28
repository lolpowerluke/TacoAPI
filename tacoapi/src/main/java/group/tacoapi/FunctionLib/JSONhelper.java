package group.tacoapi.FunctionLib;

import com.fasterxml.jackson.databind.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

// gebruik als: String headerToJson = JSONhelper.getDefaultObjectMapper().writeValueAsString(eenObject);

public class JSONhelper {
	// object mapper is een object in Jackson.databind en zorgt voor de omzetting van een object naar een json string
    private static ObjectMapper objMapper = getDefaultObjectMapper();

    public static ObjectMapper getDefaultObjectMapper()
    {
        if(objMapper == null) {
            ObjectMapper defmapper = new ObjectMapper();
            defmapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            objMapper = defmapper;
        }
        return objMapper;
    }
    public static String DecodeStringComingIn(String payload) {
        if (payload == null)
            return null;
        String decoded = "";
        try {
            decoded = URLDecoder.decode(payload, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decoded;
    }
    public static String EncodeStringGoingOut(String payload) {
        if (payload == null)
            return null;
        String encoded = "";
        try {
            encoded = URLEncoder.encode(payload, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoded;
    }

}
