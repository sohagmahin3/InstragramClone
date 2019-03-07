package com.example.instragramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickPower,edtKickSpeed;
    private Button saveButton;

    private String name =null;
    private int punchSpeed ;
    private int punchPower ;
    private int kickPower ;
    private int kickSpeed ;
    private String allKickBoxer,allKickBoxerKickPower;
    private TextView getDataTextView;
    private TextView showTextView;
    private Button btnGetData,btnActivitySwitching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.kickBoxerName);
        edtPunchSpeed= findViewById(R.id.punchSpeed);
        edtPunchPower = findViewById(R.id.punchPower);
        edtKickPower = findViewById(R.id.punchPower);
        edtKickSpeed = findViewById(R.id.kickSpeed);
        edtPunchSpeed  = findViewById(R.id.punchSpeed);
        saveButton = findViewById(R.id.savedButton);
        getDataTextView = findViewById(R.id.getDataFromParse);
        btnActivitySwitching = findViewById(R.id.btnActivitySwitching);

        btnGetData = findViewById(R.id.btnGetData);

        saveButton.setOnClickListener(SignUp.this);

        btnActivitySwitching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this,SignUpLogin.class);
                startActivity(intent);
            }
        });

        getDataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery= ParseQuery.getQuery("Kick_Boxer");
                parseQuery.getInBackground("Qy4JHT5eut", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e==null){
                            getDataTextView.setText(object.get("name")+""+object.get("kick_power"));
                        }
                    }
                });
            }
        });

        btnGetData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> getAllData = ParseQuery.getQuery("Kick_Boxer");
                getAllData.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(objects.size() > 0){
                            allKickBoxer="";
                            allKickBoxerKickPower="";
                            for(ParseObject parseObject: objects){
                                allKickBoxer= allKickBoxer+"\n"+parseObject.get("name")+" "+parseObject.get("kick_speed");
                            }
                            FancyToast.makeText(SignUp.this, allKickBoxer+" "+allKickBoxerKickPower, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }else {
                            FancyToast.makeText(SignUp.this, e.getMessage().toString(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickBoxer = new ParseObject("Kick_Boxer");
            name = edtName.getText().toString();
            punchSpeed = Integer.parseInt(edtPunchSpeed.getText().toString());
            punchPower = Integer.parseInt(edtPunchPower.getText().toString());
            kickPower = Integer.parseInt(edtKickPower.getText().toString());
            kickSpeed = Integer.parseInt(edtKickSpeed.getText().toString());
            kickBoxer.put("name", name);
            kickBoxer.put("Punch_speed", punchSpeed);
            kickBoxer.put("Punch_power", punchPower);
            kickBoxer.put("kick_speed", kickSpeed);
            kickBoxer.put("kick_power", kickPower);
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, name + " is Saved!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage().toString(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        }catch (Exception e){
            FancyToast.makeText(SignUp.this,e.getMessage().toString(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
        }
    }

}
