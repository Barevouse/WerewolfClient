package com.example.bobpoole.werewolfclient;

import android.content.SharedPreferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bob.Poole on 28/11/2016.
 */

public class WerewolfService {

    public final static int CONNECTION_TIMEOUT = 30;

    static Interceptor headerInterceptor;

    public WerewolfInterface CreateWerewolfService() {
        getHeaderInterceptor();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://werewolv.es/api/")
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WerewolfInterface.class);
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
        okClientBuilder.addInterceptor(headerInterceptor);
        okClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okClientBuilder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        return okClientBuilder.build();
    }

    private Interceptor getHeaderInterceptor(){
       headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                SharedPreferences defaultSharedPreferences = WerewolfClientApp.getSharedPreferences();
                String token = defaultSharedPreferences.getString("Token", "");
                Request request = chain.request();
                if(!token.isEmpty()){
                    request = request.newBuilder()
                            .addHeader("Authorization", "bearer " + token)
                            .build();
                }

                Response response = chain.proceed(request);
                return response;
            }
        };
        return headerInterceptor;
    }
}
