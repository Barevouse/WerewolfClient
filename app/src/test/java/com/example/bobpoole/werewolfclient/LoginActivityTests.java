package com.example.bobpoole.werewolfclient;


import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.example.bobpoole.werewolfclient.GameList.GameSelectionActivity;
import com.example.bobpoole.werewolfclient.LoginActivity.LoginActivity;
import com.example.bobpoole.werewolfclient.LoginActivity.LoginDetails;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Calendar;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants=BuildConfig.class, sdk=23)
public class LoginActivityTests {

    private LoginActivity activity;
    private SharedPreferences.Editor editor;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(LoginActivity.class)
                .attach().get();

        SharedPreferences localStorage = activity.getSharedPreferences("WereStorage", 0);
        editor = localStorage.edit();
        editor.clear();
        editor.commit();
    }
    @Test
    public void shouldMoveToGameSelectionActivityIfTokenIsValidOnCreate(){
        editor.putString("Token", "sdsadassad");
        editor.putLong("Expiry", GetTicks(1232));
        editor.commit();

        activity.onCreate(null);

        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(GameSelectionActivity.class.getCanonicalName(), intent.getComponent().getClassName());
    }

    @Test
    public void shouldStayOnCurrentActivityIfNoToken(){
        activity.onCreate(null);

        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertNull(intent);
    }

    @Test
    public void OnLoginSuccessShouldChangeActivity(){
        activity.onCreate(null);

        activity.onLoginSuccess(new LoginDetails("token", "1232342"));

        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(GameSelectionActivity.class.getCanonicalName(), intent.getComponent().getClassName());
    }

    @Test
    public void IfFailedLoginThenErrorShouldBeThrown(){
        activity.onCreate(null);

        activity.ShowError();

        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertNull(intent);
        TextView error = (TextView) activity.findViewById(R.id.error);

        assertNotNull(error.getText());
    }


    private long GetTicks(int secs){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, secs);

        long expiry = calendar.getTimeInMillis() / 1000L;
        return expiry;
    }
}
