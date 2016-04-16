package edu.wpi.cs528projectfinal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.wpi.cs528projectfinal.R;

public class A22_EstimateArrivalActivity extends Activity{

    Button btnLogin;
    Button btnSignup;
    Button btnGoToProductExample;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a10_home_screen);

        // Buttons
        btnLogin = (Button) findViewById(R.id.btnHomeLogin);
        btnSignup = (Button) findViewById(R.id.btnHomeSignup);
        btnGoToProductExample = (Button)  findViewById(R.id.btnGoToProductExample);

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

        // legacy example "products" from http://www.androidhive.info/2012/05/how-to-connect-android-with-php-mysql/
        btnGoToProductExample.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), A05_MainScreenActivity.class);
                startActivity(i);

            }
        });

    }
}