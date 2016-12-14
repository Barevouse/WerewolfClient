package com.example.bobpoole.werewolfclient;

import android.content.SharedPreferences;

import com.example.bobpoole.werewolfclient.LoginActivity.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Created by Bob.Poole on 13/12/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants=BuildConfig.class, sdk=23)
public class APICallTests {

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
    public void loginIsSuccessful() throws IOException {
        final MockWebServer server = new MockWebServer();

        final MockResponse response = new MockResponse();
        response.setResponseCode(HttpURLConnection.HTTP_OK);
        response.setBody("{Token : 'dsasdads', expiresIn : '231123414'}");
        server.url("http://werewolv.es/api/");
        server.enqueue(response);

        server.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                return response;
            }
        });

        activity.onCreate(null);
        activity.login();
    }
}
