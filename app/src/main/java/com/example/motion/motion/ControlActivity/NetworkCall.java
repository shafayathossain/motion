package com.example.motion.motion.ControlActivity;

import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {

    public void powerOn(final View view){
        RetrofitApiInterface retrofitApiInterface =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.powerOn();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse set = response.body();
                if (set != null) {
                    if (set.isSuccess()) {
                        Toast.makeText(view.getContext(), "POWER IS ON", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(view.getContext(), "SOMETHING WRONG", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(view.getContext(), "SOMETHING WRONG", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(view.getContext(), "SOMETHING WRONG", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void powerOff(final View view) {
        RetrofitApiInterface retrofitApiInterface =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.powerOff();

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse set = response.body();
                if (set != null) {
                    if (set.isSuccess()) {
                        Toast.makeText(view.getContext(), "POWER OFF", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(view.getContext(), "SOMETHING WRONG", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(view.getContext(), "SOMETHING WRONG", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(view.getContext(), "SOMETHING WRONG", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
