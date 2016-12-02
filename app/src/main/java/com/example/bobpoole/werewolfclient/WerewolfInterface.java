package com.example.bobpoole.werewolfclient;

import com.example.bobpoole.werewolfclient.GameList.GameList;
import com.example.bobpoole.werewolfclient.GameList.LoginCall;
import com.example.bobpoole.werewolfclient.LoginActivity.LoginDetails;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Bob.Poole on 27/11/2016.
 */

public interface WerewolfInterface {

    @POST("Account/Login")
    Call<LoginDetails> login(@Body LoginCall loginCall);

    @GET("Game")
    Call<GameList> getGames();
}

