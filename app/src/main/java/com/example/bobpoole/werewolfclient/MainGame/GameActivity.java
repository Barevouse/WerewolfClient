package com.example.bobpoole.werewolfclient.MainGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.bobpoole.werewolfclient.R;

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
