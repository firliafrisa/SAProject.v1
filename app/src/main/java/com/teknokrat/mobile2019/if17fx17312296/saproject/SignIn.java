package com.teknokrat.mobile2019.if17fx17312296.saproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {



    private EditText inputEmail,inputPassword;
    private FirebaseAuth auth;

    private Button btnLogin,btnLogout,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){

            startActivity(new Intent(SignIn.this,About.class));
            finish();

        }

        inputEmail =(EditText) findViewById(R.id.edt_email);
        inputPassword =(EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.email_sign_in_button);
        signUp = (Button) findViewById(R.id.email_create_account_button);
        btnLogout =(Button) findViewById(R.id.sign_out_button);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Arahkan ke Sign up
                //   startActivity(new Intent());
                Intent intent = new Intent(SignIn.this, SignUp.class);






                startActivity(intent);





            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();

                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)){

                    Toast.makeText(getApplicationContext(),"Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    Toast.makeText(getApplicationContext(),"Enter Password", Toast.LENGTH_SHORT).show();
                    return;

                }


                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                              if(!task.isSuccessful()){
                                  if(password.length()<6){
                                      inputPassword.setError(getString(R.string.minimum_password));


                                  }
                                  else{
                                      Toast.makeText(SignIn.this, "Failed", Toast.LENGTH_SHORT).show();



                                  }


                              }
                              else
                                    {
                                  Intent intent = new Intent(SignIn.this, About.class);
                                  String name ;





                                  startActivity(intent);
                                  finish();

                              }
                            }
                        });
            }
        });




    }
}
