package com.example.bobpoole.werewolfclient.LoginActivity;

/**
 * Created by Bob.Poole on 27/11/2016.
 */
public class LoginDetails {
    String accessToken;
    String expiresIn;

    public LoginDetails(String token, String expiresIn){

        accessToken = token;
        this.expiresIn = expiresIn;
    }
}
