package com.example.mywearosapplication;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mywearosapplication.endpoint.JokeAPI;
import com.example.mywearosapplication.model.Joke;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends WearableActivity {

    private TextView jokeTextView;
    private Button moreJokes;

    private final String BASE_URL = "https://icanhazdadjoke.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeTextView = (TextView) findViewById(R.id.joke);
        moreJokes = (Button) findViewById(R.id.more);

        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        final JokeAPI jokeAPI = retrofit.create(JokeAPI.class);

        moreJokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Joke> call = jokeAPI.getJoke();
                call.enqueue(new Callback<Joke>() {
                    @Override
                    public void onResponse(Call<Joke> call, Response<Joke> response) {
                        if (response.isSuccessful()) {
                            jokeTextView.setText(response.body().getJoke());
                        }
                    }

                    @Override
                    public void onFailure(Call<Joke> call, Throwable t) {
                        jokeTextView.setText("Sorry no more jokes for you");
                    }
                });


            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }
}
