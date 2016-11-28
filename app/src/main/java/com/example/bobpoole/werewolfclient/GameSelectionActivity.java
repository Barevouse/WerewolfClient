package com.example.bobpoole.werewolfclient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class GameSelectionActivity extends AppCompatActivity {

    public static final String LOCAL_STORAGE = "WereStorage";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);

        TextView textView = (TextView) findViewById(R.id.textView);

        SharedPreferences localStorage = getSharedPreferences(LOCAL_STORAGE, 0);

        String token = localStorage.getString("Token", "");

        textView.setText(token);


    }
}
