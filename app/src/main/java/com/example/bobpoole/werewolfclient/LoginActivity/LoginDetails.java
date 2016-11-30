package com.example.bobpoole.werewolfclient.LoginActivity;

/**
 * Created by Bob.Poole on 27/11/2016.
 */
public class LoginDetails {
    String access_token;
    String expires_in;

    public LoginDetails(String token, String expiresIn){

        access_token = token;
        expires_in = expiresIn;
    }

    public String getToken() {
        return access_token;
    }
}
