package com.teknokrat.mobile2019.if17fx17312296.saproject;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
        // TAG is for show some tag logs in LOG screen.
        public static final String TAG = "Login";

        // Request sing in code. Could be anything as you required.
        public static final int RequestSignInCode = 7;

        // Firebase Auth Object.
        public FirebaseAuth firebaseAuth;

        // Google API Client object.
        public GoogleApiClient googleApiClient;

        // Sing out button.
        Button SignOutButton;

        // Google Sign In button .
        Button signInButton;
        ImageView fotoprofile;
        // TextView to Show Login User Email and Name.
        TextView LoginUserName, LoginUserEmail;


        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


          //  signInButton = (signInButton) findViewById(R.id.sign_Google);
            fotoprofile = (ImageView) findViewById(R.id.fotoprofile);
            SignOutButton= (Button) findViewById(R.id.logout);

            LoginUserName = (TextView) findViewById(R.id.username);

            LoginUserEmail = (TextView) findViewById(R.id.emailname);

            signInButton = (Button) findViewById(R.id.sign_Google);

            // Getting Firebase Auth Instance into firebaseAuth object.
            firebaseAuth = FirebaseAuth.getInstance();

            // Hiding the TextView on activity start up time.
           LoginUserEmail.setVisibility(View.GONE);
          LoginUserName.setVisibility(View.GONE);

            // Creating and Configuring Google Sign In object.
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            // Creating and Configuring Google Api Client.
            googleApiClient = new GoogleApiClient.Builder(Login.this)
                    .enableAutoManage(Login.this , new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        }
                    } /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                    .build();


            // Adding Click listener to User Sign in Google button.
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    UserSignInMethod();

                }
            });

            // Adding Click Listener to User Sign Out button.
            SignOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    UserSignOutFunction();

                }
            });

        }


        // Sign In function Starts From Here.
        public void UserSignInMethod(){

            // Passing Google Api Client into Intent.
            Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);

            startActivityForResult(AuthIntent, RequestSignInCode);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == RequestSignInCode){

                GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

                if (googleSignInResult.isSuccess()){

                    GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();

                    FirebaseUserAuth(googleSignInAccount);
                }

            }
        }

        public void FirebaseUserAuth(GoogleSignInAccount googleSignInAccount) {

            AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

            Toast.makeText(Login.this,""+ authCredential.getProvider(),Toast.LENGTH_LONG).show();

            firebaseAuth.signInWithCredential(authCredential)
                    .addOnCompleteListener(Login.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task AuthResultTask) {

                            if (AuthResultTask.isSuccessful()){

                                // Getting Current Login user details.
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                                // Showing Log out button.
                               SignOutButton.setVisibility(View.VISIBLE);

                                // Hiding Login in button.
                                signInButton.setVisibility(View.GONE);
                                fotoprofile.setVisibility(View.VISIBLE);
                                // Showing the TextView.
                                LoginUserEmail.setVisibility(View.VISIBLE);
                                LoginUserName.setVisibility(View.VISIBLE);

                                // Setting up name into TextView.
                                LoginUserName.setText(firebaseUser.getDisplayName().toString());

                                // Setting up Email into TextView.
                                LoginUserEmail.setText(firebaseUser.getEmail().toString());

                            }else {
                                Toast.makeText(Login.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

        public void UserSignOutFunction() {

            // Sing Out the User.
            firebaseAuth.signOut();

            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                    new ResultCallback() {
                        @Override
                        public void onResult(@NonNull Result result) {

                        }


                        public void onResult(@NonNull Status status) {

                            // Write down your any code here which you want to execute After Sign Out.

                            // Printing Logout toast message on screen.
                            Toast.makeText(Login.this, "Logout Successfully", Toast.LENGTH_LONG).show();

                        }
                    });

            // After logout Hiding sign out button.
            SignOutButton.setVisibility(View.GONE);
            fotoprofile.setVisibility(View.GONE);
            // After logout setting up email and name to null.
            LoginUserName.setText(null);
            LoginUserEmail.setText(null);

            // After logout setting up login button visibility to visible.
            signInButton.setVisibility(View.VISIBLE);
        }

}