package com.example.bobpoole.werewolfclient;

import android.content.SharedPreferences;
import android.widget.ListView;

import com.example.bobpoole.werewolfclient.GameList.GameList;
import com.example.bobpoole.werewolfclient.GameList.GameSelectionActivity;
import com.example.bobpoole.werewolfclient.LoginActivity.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Bob.Poole on 13/12/2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants=BuildConfig.class, sdk=23)
public class GameSelectionActivityTests {
    private GameSelectionActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(GameSelectionActivity.class)
                .create().get();
    }

    @Test
    public void IfActiveGamesArePassedThroughThenActiveListShouldHaveEntries(){

        List<String> list = new ArrayList<String>();

        list.add("a");
        list.add("b");
        GameList gameList = new GameList();
        gameList.setActive(list);

        activity.onGamesSuccess(gameList);

        ListView listview = (ListView) activity.findViewById(R.id.activeGameList);

        assertEquals(listview.getAdapter().getCount(), 2);
    }

    @Test
    public void IfPendingGamesArePassedThroughThenPendingListShouldHaveEntries(){

        List<String> list = new ArrayList<String>();

        list.add("a");
        list.add("b");
        GameList gameList = new GameList();
        gameList.setPending(list);

        activity.onGamesSuccess(gameList);

        ListView listview = (ListView) activity.findViewById(R.id.pendingGameList);

        assertEquals(listview.getAdapter().getCount(), 2);
    }

    @Test
    public void IfNoGameArePassedThroughThenTheListsAreEmpty(){
        GameList gameList = new GameList();

        activity.onGamesSuccess(gameList);

        ListView activeListview = (ListView) activity.findViewById(R.id.activeGameList);
        ListView pendingListview = (ListView) activity.findViewById(R.id.pendingGameList);

        assertEquals(activeListview.getAdapter().getCount(), 0);
        assertEquals(pendingListview.getAdapter().getCount(), 0);
    }




}
