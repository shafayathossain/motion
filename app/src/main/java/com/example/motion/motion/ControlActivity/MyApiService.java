package com.example.motion.motion.ControlActivity;

public interface MyApiService {
    void powerOn(ResponseCallback<String> callback);

    void powerOff(ResponseCallback<String> responseCallback);
}
