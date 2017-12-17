package com.example.motion.motion.ControlActivity;

import com.google.gson.annotations.SerializedName;


public class ServerResponse {

    @SerializedName("message")
    String message;

    public boolean isSuccess(){
        if(message.contains("set to")){
            return true;
        }
        else
            return false;
    }

    public String getMessage(){
        return message;
    }
}


