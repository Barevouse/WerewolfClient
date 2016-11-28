package com.example.bobpoole.werewolfclient;

import com.example.bobpoole.werewolfclient.Models.LoginCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Bob.Poole on 27/11/2016.
 */

public interface WerewolfInterface {
    @POST("Account/login")
    Call<LoginDetails> login(@Body LoginCall loginCall);

    @GET("Game")
    Call<GameList> getGames();
}

