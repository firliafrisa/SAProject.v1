package com.teknokrat.mobile2019.if17fx17312296.saproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class About extends AppCompatActivity {
    ImageButton info1,book1,contact1,location1;
    @SuppressLint("WrongViewCast")
    Button signOut;


   private TextView email1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        info1 =  (ImageButton) findViewById(R.id.info1);
        book1 =  (ImageButton) findViewById(R.id.book1);
        contact1 =  (ImageButton) findViewById(R.id.contact1);
        location1 =  (ImageButton) findViewById(R.id.maps1);


        info1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Info.class);
                startActivity(intent);

                //Let's Finish Splash Activity since we don't want to show this when user press back button.
                finish();

            }
        });

        book1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), Booking.class);
                startActivity(intent);

                //Let's Finish Splash Activity since we don't want to show this when user press back button.
               // finish();

            }
        });

        location1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(About.this, "Maps.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);

            }
        });

        contact1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String phoneNumberWithCountryCode = "+628976181749";
                String message = "Haiii, Saya ingin mengobrol dengan Anda";

                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                                String.format("https://api.whatsapp.com/send?phone=%s&text=%s",
                                        phoneNumberWithCountryCode, message))));

            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name1 = user.getEmail();
            //String email = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
            email1 = (TextView) findViewById(R.id.emailname);
            email1.setText(name1);
//            name1 = String.valueOf((TextView) findViewById(R.id.emailname));
//            email = String.valueOf((TextView)findViewById(R.id.username));
        } else {
            Intent intent = new Intent(About.this, SignIn.class);
            startActivity(intent);
            finish();
        }

        signOut = (Button) findViewById(R.id.logout);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(About.this, SignIn.class);
                startActivity(intent);
                finish();

            }
        });

    }


}

