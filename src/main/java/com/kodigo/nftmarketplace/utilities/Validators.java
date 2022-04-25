package com.kodigo.nftmarketplace.utilities;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static boolean isValidPassword(String password){

        /*
         * At least one upper case English letter, (?=.*?[A-Z])
         * At least one lower case English letter, (?=.*?[a-z])
         * At least one digit, (?=.*?[0-9])
         * At least one special character, (?=.*?[#?!@$%^&*-])
         * Minimum eight in length .{8,} (with the anchors)
         * */
        Pattern p = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
