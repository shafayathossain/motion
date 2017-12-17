package com.example.motion.motion.CameraActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.motion.juthiapu.R;

public class Camera extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        WebView myWebView = (WebView) findViewById(R.id.cameraWebView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://10.10.10.240:8081/");
    }

}

