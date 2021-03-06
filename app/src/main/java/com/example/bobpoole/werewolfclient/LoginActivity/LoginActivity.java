package com.example.bobpoole.werewolfclient.LoginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bobpoole.werewolfclient.GameList.GameSelectionActivity;
import com.example.bobpoole.werewolfclient.R;
import com.example.bobpoole.werewolfclient.TokenHelper;
import com.example.bobpoole.werewolfclient.WerewolfInterface;
import com.example.bobpoole.werewolfclient.WerewolfService;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    String API = "werewolv.es/";
    public static final String LOCAL_STORAGE = "WereStorage";
    private static final String GRANT_TYPE = "password";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.loginButton);

        try {
            checkToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void login() throws IOException {


        EditText editTextEmail= (EditText)  findViewById(R.id.emailAddress);
        String emailAddress = editTextEmail.getText().toString();
        TextView editTextPassword = (TextView)  findViewById(R.id.password);
        String password = editTextPassword.getText().toString();

        WerewolfService service = new WerewolfService();
        WerewolfInterface wereInterface = service.CreateWerewolfService();

        Call<LoginDetails> call = wereInterface.login(new LoginCall(emailAddress, password));

        call.enqueue(new Callback<LoginDetails>() {
            @Override
            public void onResponse(Call<LoginDetails> call, Response<LoginDetails> response) {
                if(response.isSuccessful()){
                onLoginSuccess(response.body());
                }
                else{
                    ShowError();
                }
            }
            @Override
            public void onFailure(Call<LoginDetails> call, Throwable t) {
                ShowError();
            }
        });

    }

    public void ShowError() {
        final TextView error = (TextView) findViewById(R.id.error);
        error.setVisibility(View.VISIBLE);
    }

    private void checkToken() throws Exception {
        if (!TokenHelper.IsTokenValid(getSharedPreferences(LOCAL_STORAGE, 0))){
                return;
        }

        launchActivity();
    }

    public void onLoginSuccess(LoginDetails result) {
        SharedPreferences localStorage = getSharedPreferences(LOCAL_STORAGE, 0);
        SharedPreferences.Editor editor = localStorage.edit();
        int secs = Integer.parseInt(result.expiresIn);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, secs);

        long expiry = calendar.getTimeInMillis() / 1000L;

        editor.putString("Token", result.accessToken);
        editor.putLong("Expiry", expiry);
        editor.commit();
        launchActivity();

    }

    private void launchActivity(){
        Intent intent = new Intent(getApplicationContext(), GameSelectionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
