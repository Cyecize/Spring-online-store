package com.cyecize.skatefixers.areas.googleDrive.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageIdExtractor {

    public static final String LINK_FORMAT = "https://drive.google.com/uc?id=%s&";

    public static String setFormat(String id){
        return String.format(LINK_FORMAT, id) ;
    }

    public static String extractId(String link){
        if(link == null) return "";
        Matcher matcher = Pattern.compile("id=(.*?)&").matcher(link);
        if(matcher.find())
            return matcher.group(1);
        return "";
    }
}
