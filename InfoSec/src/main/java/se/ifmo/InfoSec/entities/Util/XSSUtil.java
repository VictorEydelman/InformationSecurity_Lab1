package se.ifmo.InfoSec.entities.Util;


import org.apache.commons.lang3.StringEscapeUtils;

public class XSSUtil {
    public static String screening(String input){
        return StringEscapeUtils.escapeHtml4(input);
    }
}
