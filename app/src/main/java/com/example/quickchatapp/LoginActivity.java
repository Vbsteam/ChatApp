package com.example.quickchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    TextView signin_btn, txt_signup;
    EditText login_email,login_password;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signin_btn=findViewById(R.id.signin_btn);
        txt_signup=findViewById(R.id.txt_signup);
        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);







        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=login_email.getText().toString();
                String password=login_password.getText().toString();


                //checking all fields are entered or not
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this, "Enter all the Fields", Toast.LENGTH_SHORT).show();
                }
                //checkinf if entered email is valid or not
                else if(!email.matches(emailPattern))
                {
                    login_email.setError("Invalid email");
                    Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });






        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);

            }
        });
    }
}