package com.example.bobpoole.werewolfclient.Models;

import com.google.gson.Gson;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class DecodedToken {
    public TokenInfo tokenInfo;

    public static DecodedToken fromJson(String s) {
        DecodedToken decodedToken = new Gson().fromJson(s, DecodedToken.class);

        return decodedToken;
    }
}

