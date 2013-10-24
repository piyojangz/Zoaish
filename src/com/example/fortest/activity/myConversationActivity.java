package com.example.fortest.activity;
import com.example.fortest.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fortest.ListItem;
import com.example.fortest.custom.ConversationAdapter;
import com.example.fortest.custom.setAppFont;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by ebiz_asc1 on 10/2/13.
 */
public class myConversationActivity extends Activity {
    private HashMap<String, String> map;
    private ArrayList<HashMap<String, String>> sList;
    private ListView conversationList;
    private ConversationAdapter Ladapter;
    private String KEY_MYMESSAGEID;
    private String KEY_MYMESSAGEUSERID;
    private CharSequence mTitle;
    private Typeface typeFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeFace = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeueLight.ttf");
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        setAppFont.setAppFont(mContainer, typeFace);
        // DECLEAR PRMS //
        this.KEY_MYMESSAGEID = getIntent().getStringExtra(ListItem.KEY_MYMESSAGEID);
        this.KEY_MYMESSAGEUSERID = getIntent().getStringExtra(ListItem.KEY_MYMESSAGEUSERID);
        getActionBar().setTitle("i'm interesting your product");
        // END DECLEAR PRMS //
        setContentView(R.layout.myconversationlistview);
        conversationList = (ListView) this.findViewById(R.id.myconversation_listView);
        sList = new ArrayList<HashMap<String, String>>();
        //####################################################################################################
        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Hi , i'm interesting your product. Please contact me inbox.");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Hi , Could you please discount the item no 723A?");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/c42.42.524.524/s160x160/1236460_10200487087965988_1178539083_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Is the item no 485A was sold out?");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "I was transfer the money to you at 13:00PM , $200.00");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/c42.42.524.524/s160x160/1236460_10200487087965988_1178539083_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Hi , i'm interesting your product. Please contact me inbox.");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Hi , Could you please discount the item no 723A?");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/c42.42.524.524/s160x160/1236460_10200487087965988_1178539083_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Is the item no 485A was sold out?");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "I was transfer the money to you at 13:00PM , $200.00");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/c42.42.524.524/s160x160/1236460_10200487087965988_1178539083_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Hi , i'm interesting your product. Please contact me inbox.");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Hi , Could you please discount the item no 723A?");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/c42.42.524.524/s160x160/1236460_10200487087965988_1178539083_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Is the item no 485A was sold out?");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "I was transfer the money to you at 13:00PM , $200.00");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/c42.42.524.524/s160x160/1236460_10200487087965988_1178539083_n.jpg");
        sList.add(map);
        Ladapter = new ConversationAdapter(this, sList);
        this.conversationList.setAdapter(Ladapter);


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
