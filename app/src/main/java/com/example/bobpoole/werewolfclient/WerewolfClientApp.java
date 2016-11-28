package com.example.bobpoole.werewolfclient;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class WerewolfClientApp extends Application {
    private static WerewolfClientApp instance;

    public static final String LOCAL_STORAGE = "WereStorage";

    public static WerewolfClientApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    public static SharedPreferences getSharedPreferences(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(LOCAL_STORAGE, 0);
        return sharedPreferences;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
