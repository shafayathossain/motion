package com.example.motion.motion.ControlActivity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiInterface {

    @GET("digital/18/1")
    Call<ServerResponse> powerOn();

    @GET("digital/18/0")
    Call<ServerResponse> powerOff();
}

