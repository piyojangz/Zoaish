package com.example.fortest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.fortest.ListItem;
import com.example.fortest.ListUser;
import com.example.fortest.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fortest.helper.AlertDialogManager;
import com.example.fortest.helper.SessionManager;
import com.squareup.picasso.Picasso;

/**
 * Created by ebiz_asc1 on 10/24/13.
 */
public class firstLoginActivity extends Activity {

    // Email, password edittext
    EditText txtUsername, txtPassword;
    Activity a;
    // login button
    Button btn_login;
    Button btn_loginwithfacebook;
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    static final int PICK_CONTACT_REQUEST = 1;  // The request code
    // Session Manager Class
    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        a = this;
        // Session Manager
        session = new SessionManager(getApplicationContext());

        // Email, Password input text
        // txtUsername = (EditText) findViewById(R.id.txtUsername);
        //  txtPassword = (EditText) findViewById(R.id.txtPassword);


        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        // Login button

        btn_loginwithfacebook = (Button) findViewById(R.id.btn_loginwithfacebook);
        btn_loginwithfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(a, loginActivity.class);
                a.startActivityForResult(myIntent, PICK_CONTACT_REQUEST);
            }
        });
        // Login button click event

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String result = data.getExtras().getString("fbresult");
                if (result.equals("true")) {
                    session.createLoginSession(ListUser.id, ListUser.facebook_id);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("IS_LOGIN", "true");
                    startActivity(i);
                    finish();
                }

            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Log.d(ListItem.TAG, "Key back pressed");
            MainActivity.isQuit = true;
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
