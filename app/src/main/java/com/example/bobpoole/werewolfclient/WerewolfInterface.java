package com.example.bobpoole.werewolfclient;

import com.example.bobpoole.werewolfclient.GameList.GameList;
import com.example.bobpoole.werewolfclient.LoginActivity.LoginDetails;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Bob.Poole on 27/11/2016.
 */

public interface WerewolfInterface {

    @FormUrlEncoded
    @POST("token")
    Call<LoginDetails> login(@Field ("username") String username, @Field("password") String password, @Field("grant_type") String grantType);

    @GET("Game")
    Call<GameList> getGames();
}

