package com.teknokrat.mobile2019.if17fx17312296.saproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Booking extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSecw2pMopvsQuWBDcCc1SxUKjtY_lUAyC9m-ns_B4wOxRbafg/viewform?usp=sf_link");



//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if((keyCode==KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
//            webView.goBack();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
}}
