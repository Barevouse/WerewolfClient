package com.example.bobpoole.werewolfclient;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class GameListAdapter extends ArrayAdapter<String> {

    public GameListAdapter(Context context, List<String> resource) {
        super(context, 0 ,resource);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        String game = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.game_list, parent, false);
        }
        TextView gameId = (TextView) convertView.findViewById(R.id.game_Id);

        gameId.setText(game);

        gameId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                TextView textView = (TextView) view.findViewById(R.id.game_Id);

                Intent intent = new Intent(getContext().getApplicationContext(), GameActivity.class);
                String text = textView.getText().toString();
                intent.putExtra("gameId", text);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
