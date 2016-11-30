package com.example.bobpoole.werewolfclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class GameSelectionActivity extends AppCompatActivity {

    public static final String LOCAL_STORAGE = "WereStorage";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);

        WerewolfService service = new WerewolfService();
        WerewolfInterface wereInterface = service.CreateWerewolfService();

        wereInterface.getGames().enqueue(new Callback<GameList>() {
            @Override
            public void onResponse(Call<GameList> call, Response<GameList> response) {
                if(response.isSuccessful()){
                    onGamesSuccess(response.body());
                }
                else{
                    //TODO: Add in error handling
                }
            }

            @Override
            public void onFailure(Call<GameList> call, Throwable t) {

            }
        });

    }

    private void onGamesSuccess(GameList gameList) {
        GameListAdapter gamelistAdapter = new GameListAdapter(this, gameList.getActive());

        ListView activeGameList = (ListView) findViewById(R.id.activeGameList);
        activeGameList.setAdapter(gamelistAdapter);
    }

}
