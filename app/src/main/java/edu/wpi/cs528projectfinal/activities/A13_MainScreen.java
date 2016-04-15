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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a13_main_screen);

        // Buttons
        btnGotoList = (Button) findViewById(R.id.goto_meeting_list);
        btnGotoCreate = (Button) findViewById(R.id.goto_create_meeting);

        /*
        // login button
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), A11_LoginActivity.class);
                startActivity(i);

            }
        });

        // signup button
        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), A12_SignupActivity.class);
                startActivity(i);

            }
        });
*/

    }
}