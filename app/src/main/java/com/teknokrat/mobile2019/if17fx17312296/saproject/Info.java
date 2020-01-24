package com.teknokrat.mobile2019.if17fx17312296.saproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        String frameVideo = "<html><body>Example Video<br><iframe width=\"1080\" height=\"720\" src=\"https://www.youtube.com/embed/LmvQBHb9eUA?controls=0\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></body></html>";

        WebView displayYoutubeVideo = (WebView) findViewById(R.id.mWebView);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        displayYoutubeVideo.setInitialScale(1);
        displayYoutubeVideo.getSettings().setJavaScriptEnabled(true);
        displayYoutubeVideo.getSettings().setLoadWithOverviewMode(true);
        displayYoutubeVideo.getSettings().setUseWideViewPort(true);
        displayYoutubeVideo.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        displayYoutubeVideo.setScrollbarFadingEnabled(false);
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
