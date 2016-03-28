package edu.wpi.cs528projectfinal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.wpi.cs528projectfinal.Activity04Checkin.AttendeeListFragment;
import edu.wpi.cs528projectfinal.R;

public class MainActivity extends AppCompatActivity {

    private Button mButtonGotoView4MeetingCheckin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonGotoView4MeetingCheckin = (Button) findViewById(R.id.to_view_04);
        mButtonGotoView4MeetingCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start Activity

                //TODO - when starting the next view - send in the meeting ID
                Integer theMeetingId = 123456;
                Intent i = AttendeeListFragment.newIntent(MainActivity.this, theMeetingId);
                    startActivity(i);
                }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
