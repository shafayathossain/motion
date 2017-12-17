package com.example.motion.motion.ControlActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall implements MyApiService {

    @Override
    public void powerOn(final ResponseCallback<String>setPinCheckListener){
        RetrofitApiInterface retrofitApiInterface =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.powerOn();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse set = response.body();
                if (set != null) {
                    if (set.isSuccess()) {
                        setPinCheckListener.onSuccess(set.getMessage());
                    } else
                        setPinCheckListener.onError(new Exception(set.getMessage()));
                }
                else
                    setPinCheckListener.onError(new Exception(set.getMessage()));
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                setPinCheckListener.onError(t);
            }
        });
    }

    @Override
    public void powerOff(final ResponseCallback<String> responseCallback) {
        RetrofitApiInterface retrofitApiInterface =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.powerOff();

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse set = response.body();
                if (set != null) {
                    if (set.isSuccess()) {
                        responseCallback.onSuccess(set.getMessage());
                    } else
                        responseCallback.onError(new Exception(set.getMessage()));
                }
                else
                    responseCallback.onError(new Exception(set.getMessage()));
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                responseCallback.onError(t);
            }
        });
    }
}
