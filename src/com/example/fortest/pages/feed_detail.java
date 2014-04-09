package com.example.fortest.pages;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.fortest.ListItem;
import com.example.fortest.R;
import com.example.fortest.activity.MainActivity;
import com.example.fortest.custom.setAppFont;

/**
 * Created by Veeray5 on 9/4/2557.
 */
public class feed_detail extends Activity {
    private static final String TAG = "MyActivity";
    private CharSequence mTitle;
    private Typeface typeFace;
    private String KEY_FEEDID;
    private String KEY_FEEDTITLE;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeFace = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeueLight.ttf");
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        setAppFont.setAppFont(mContainer, typeFace);
        // DECLEAR PRMS //
        this.KEY_FEEDID = getIntent().getStringExtra(ListItem.KEY_MYFEED_ITEMID);
        this.KEY_FEEDTITLE = getIntent().getStringExtra(ListItem.KEY_MYFEED_ITEMTITLE);
        getActionBar().setTitle(this.KEY_FEEDTITLE);
        setContentView(R.layout.activity_blank);


        Log.d(TAG, "We loaded..." + this.KEY_FEEDID);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.conversation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            MainActivity.isQuit = false;
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                MainActivity.isQuit = false;
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}