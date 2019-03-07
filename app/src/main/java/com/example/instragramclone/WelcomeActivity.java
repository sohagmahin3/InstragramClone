package com.example.instragramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class WelcomeActivity extends AppCompatActivity {

    private Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView welcomTextView= findViewById(R.id.welcomText);
        welcomTextView.setText("Welcome !"+ ParseUser.getCurrentUser().get("username"));

        //For logout button without button declearation
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FancyToast.makeText(WelcomeActivity.this,ParseUser.getCurrentUser().get("username")+" is logout successfully!",FancyToast.SUCCESS,FancyToast.LENGTH_LONG,true).show();
               ParseUser.logOut();
               finish();
            }
        });
    }
}
