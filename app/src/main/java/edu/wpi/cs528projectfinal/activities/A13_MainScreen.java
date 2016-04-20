package edu.wpi.cs528projectfinal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.wpi.cs528projectfinal.R;

public class A13_MainScreen extends Activity{

    Button btnGotoList;
    Button btnGotoCreate;

    // INTENT extras
    private static final String INTENT_UID = "uid";

    // user ID - comes in as an "extra" in the intent
    String uid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a13_main_screen);

        // getting user ID (uid) from intent
        Intent i = getIntent();
        uid = i.getStringExtra(INTENT_UID);

        // Buttons
        btnGotoList = (Button) findViewById(R.id.goto_meeting_list);
        btnGotoCreate = (Button) findViewById(R.id.goto_create_meeting);


        // new meeting button
        btnGotoCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), A14b_NewMeetingActivity.class);
                i.putExtra(INTENT_UID, uid);
                startActivity(i);
            }
        });
/*
        // signup button
        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), A12_SignupActivity.class);
        i.putExtra(INTENT_UID, uid);
                startActivity(i);

            }
        });
*/

    }
}