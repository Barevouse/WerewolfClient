package com.example.bobpoole.werewolfclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Bob.Poole on 28/11/2016.
 */
public class GameActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        String id = getIntent().getStringExtra("gameId");

        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(id);
    }
}
