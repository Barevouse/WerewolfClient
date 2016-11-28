package com.example.bobpoole.werewolfclient;

import android.content.SharedPreferences;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class TokenHelper {
    private static final Object LOCAL_STORAGE = "WereStorage";

    public static boolean IsTokenValid(SharedPreferences sharedPrefences) throws ParseException {
        SharedPreferences localStorage = sharedPrefences;
        String token = localStorage.getString("Token", "");
        Date now = new Date();

        if(token.isEmpty() ){
            return true;
        }

        int i = token.lastIndexOf('.');
        String withoutSignature = token.substring(0, i+1);

        SignedJWT signedJWT = null;

        try {
            signedJWT = SignedJWT.parse(withoutSignature);
        } catch (ParseException e) {
            return true;
        }

        JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();

        Date exp = jwtClaimsSet.getDateClaim("exp");

        if(exp.before(now)){
            return true;
        }
        return false;
    }
}
