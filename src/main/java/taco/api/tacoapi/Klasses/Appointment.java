package taco.api.tacoapi.Klasses;

import java.time.LocalDateTime;

public class Appointment {

    public String appName;
    public String appDesc;
    public String geplandMoment;
    
   //constructor for Appointment => Appointment "" = new Appointment();
    public Appointment(String name, String desc, LocalDateTime moment) {
        //set appointment name
        this.appName = name;
        //set appointment desc
        this.appDesc = desc;
        //set time of appointment if not null
        if(moment != null)
            this.geplandMoment = moment.toString();
    }
}
