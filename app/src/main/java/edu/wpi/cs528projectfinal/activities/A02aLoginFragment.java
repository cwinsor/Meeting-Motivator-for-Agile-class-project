package edu.wpi.cs528projectfinal.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/26/2016.
 * Reference
 * http://www.tutorialspoint.com/android/android_php_mysql.htm
 */
public class A02aLoginFragment extends Fragment {
    private String TAG = this.getClass().toString();

    private EditText usernameField,passwordField;
    private TextView status,role,method;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setHasOptionsMenu(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.a02a_fragment_login, container, false);


        usernameField = (EditText)view.findViewById(R.id.editText1);
        passwordField = (EditText)view.findViewById(R.id.editText2);

        status = (TextView)view.findViewById(R.id.textView6);
        role = (TextView)view.findViewById(R.id.textView7);
        method = (TextView)view.findViewById(R.id.textView9);

        updateUI();

        return view;
    }


    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Get Method");
//        new A02cSigninAsyncTask(this,status,role,0).execute(username, password);

        // switch to another fragment at runtime
        // references
        // http://stackoverflow.com/questions/20176999/how-to-switch-between-fragments-during-onclick
        // http://developer.android.com/intl/ru/training/basics/fragments/fragment-ui.html#AddAtRuntime
        Fragment fragment = new A02bSigninFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Post Method");
        // new A02cSigninAsyncTask(this,status,role,1).execute(username, password);

        // switch to another fragment at runtime
        // references
        // http://stackoverflow.com/questions/20176999/how-to-switch-between-fragments-during-onclick
        // http://developer.android.com/intl/ru/training/basics/fragments/fragment-ui.html#AddAtRuntime
        Fragment fragment = new A02bSigninFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    private void updateUI() {
        // nothing to do
    }

}
