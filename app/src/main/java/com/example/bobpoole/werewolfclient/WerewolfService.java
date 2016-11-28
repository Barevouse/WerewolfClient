package com.example.bobpoole.werewolfclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class WerewolfService {
    public static WerewolfInterface CreateWerewolfService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://werewolv.es/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WerewolfInterface.class);
    }
}
