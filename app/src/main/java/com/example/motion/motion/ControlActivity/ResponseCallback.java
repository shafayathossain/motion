package com.example.motion.motion.ControlActivity;

public interface ResponseCallback<T> {
    void onSuccess(T data);
    void onError(Throwable th);
}

