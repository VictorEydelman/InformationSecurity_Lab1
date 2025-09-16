package se.ifmo.InfoSec.entities.Util;

public class XSSUtil {
    public static String screening(String input){
        if(input == null){
            return null;
        }
        return input.replace("'","&#x27").replace("\"","&quot").replace("&","&amp")
                .replace("<","&lt").replace(">","&gt");
    }
}
