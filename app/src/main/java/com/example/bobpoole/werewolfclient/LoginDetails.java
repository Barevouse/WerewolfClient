package com.example.bobpoole.werewolfclient;

/**
 * Created by Bob.Poole on 27/11/2016.
 */
public class LoginDetails {
    String Token;
    private String token;

    public LoginDetails(String token){
        Token = token;
    }

    public String getToken() {
        return token;
    }
}
