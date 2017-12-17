package com.example.motion.motion.MainAcitvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.motion.motion.CameraActivity.Camera;
import com.example.motion.motion.ControlActivity.MyApiService;
import com.example.motion.motion.ControlActivity.NetworkCall;
import com.example.motion.motion.ControlActivity.ResponseCallback;
import com.example.motion.motion.FTPActivity.FTP;
import com.example.motion.motion.HistoryActivity.History;
import com.example.motion.juthiapu.R;

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
        System.out.println(flag);
        MyApiService myApiService = new NetworkCall();
        if(flag==0) {
            myApiService.powerOn(new ResponseCallback<String>() {
                @Override
                public void onSuccess(String data) {
                    Toast.makeText(view.getContext(), "POWER IS ON", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable th) {
                    Toast.makeText(view.getContext(), th.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            flag=1;
        }
        else{
            myApiService.powerOff(new ResponseCallback<String>() {
                @Override
                public void onSuccess(String data) {
                    Toast.makeText(view.getContext(), "POWER IS OFF", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable th) {
                    Toast.makeText(view.getContext(), th.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
