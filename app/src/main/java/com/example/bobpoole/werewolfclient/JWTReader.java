package com.example.bobpoole.werewolfclient;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class JWTReader {
        public static String decoded(String JWTEncoded) throws Exception {
            try {
                String[] split = JWTEncoded.split("\\.");
                return getJson(split[1]);
            } catch (UnsupportedEncodingException e) {
                //Error
            }
            return JWTEncoded;
        }
        static String getJson(String strEncoded) throws UnsupportedEncodingException{
            byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
            return new String(decodedBytes, "UTF-8");
        }
}
