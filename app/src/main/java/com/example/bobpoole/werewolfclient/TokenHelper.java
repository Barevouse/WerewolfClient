package com.example.bobpoole.werewolfclient;

import android.content.SharedPreferences;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class TokenHelper {
    private static final Object LOCAL_STORAGE = "WereStorage";

    public static boolean IsTokenValid(SharedPreferences sharedPrefences) throws ParseException {
        SharedPreferences localStorage = sharedPrefences;
        String token = localStorage.getString("Token", "");
        Long expiry = localStorage.getLong("Expiry", 0);

        Calendar now = Calendar.getInstance();

        if(token.isEmpty() || token == "" ){
            return false;
        }

        Calendar exp = Calendar.getInstance();
        exp.setTimeInMillis(expiry * 1000L);

        if(exp.before(now)){
            return false;
        }
        return true;
    }
}
