package com.teknokrat.mobile2019.if17fx17312296.saproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splashscreen extends AppCompatActivity {
    private Handler mWaitHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splashscreen);
      //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //The following code will execute after the 5 seconds.

                try {

                    //Go to next page i.e, start the next activity.
                    Intent intent = new Intent(getApplicationContext(), SignIn.class);
                    startActivity(intent);

                    //Let's Finish Splash Activity since we don't want to show this when user press back button.
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 8000);  // Give a 5 seconds delay.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Remove all the callbacks otherwise navigation will execute even after activity is killed or closed.
        mWaitHandler.removeCallbacksAndMessages(null);
    }



    }

