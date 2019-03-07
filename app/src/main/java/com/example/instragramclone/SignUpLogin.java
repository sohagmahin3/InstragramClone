package com.example.instragramclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogin extends AppCompatActivity {

    private EditText editTextBtnSignUpUsername,editTextBtnSignUpPassword, editTextLoginUsername,editTextLoginPassword;
    private Button signUpButton,loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);

        editTextBtnSignUpUsername= findViewById(R.id.editTextBtnSignupUsername);
        editTextBtnSignUpPassword = findViewById(R.id.editTextBtnSignupPassword);
        editTextLoginUsername= findViewById(R.id.editTextBtnLoginUsername);
        editTextLoginPassword= findViewById(R.id.editTextBtnLoginPassword);

        signUpButton = findViewById(R.id.userSignUpButton);
        loginButton= findViewById(R.id.userLoginButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(editTextBtnSignUpUsername.getText().toString());
                appUser.setPassword(editTextBtnSignUpPassword.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLogin.this,appUser.get("username")+" is signed up successed!",FancyToast.SUCCESS,FancyToast.LENGTH_LONG,true).show();

                        }else {
                            FancyToast.makeText(SignUpLogin.this,appUser.get("username")+"is Failed to Sign Up!",FancyToast.ERROR,FancyToast.LENGTH_LONG,true).show();

                        }
                    }
                });
            }
        });

    }
}
