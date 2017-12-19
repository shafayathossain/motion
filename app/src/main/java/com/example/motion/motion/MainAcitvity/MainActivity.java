package com.example.motion.motion.MainAcitvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.motion.juthiapu.R;
import com.example.motion.motion.CameraActivity.Camera;
import com.example.motion.motion.ControlActivity.NetworkCall;
import com.example.motion.motion.FTPActivity.FTP;
import com.example.motion.motion.HistoryActivity.History;

public class MainActivity extends AppCompatActivity {

    private Button hisotryButton;
    private Button powerButton;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flag = 0;

        hisotryButton = (Button) findViewById(R.id.historyButton);
        powerButton = (Button) findViewById(R.id.powerButton);
    }

    public void history(View view) {
        Intent intent = new Intent(MainActivity.this, History.class);
        startActivity(intent);
    }

    public void power(final View view) {

        NetworkCall myApiService = new NetworkCall();
        if(flag==0) {
            myApiService.powerOn(view);
            flag=1;
        }
        else{
            myApiService.powerOff(view);
            flag = 0;
        }
    }

    public void ftp(View view) {
        Intent intent = new Intent(MainActivity.this, FTP.class);
        startActivity(intent);
    }

    public void camera(View view) {
        Intent intent = new Intent(MainActivity.this, Camera.class);
        startActivity(intent);
    }
}
