package com.example.fortest.activity;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TabActivity;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.os.Bundle;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.graphics.Bitmap;
import android.graphics.drawable.*;
import android.widget.TextView;
import com.example.fortest.custom.MenuAdapter;
import com.example.fortest.custom.LazyAdapter;
import com.example.fortest.ListItem;
import com.example.fortest.R;
import com.example.fortest.custom.setAppFont;


/**
 * @author ebiz_asc1
 */
public class MainActivity extends TabActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LazyAdapter Ladapter;
    private MenuAdapter Menuadapter;
    private HashMap<String, String> map;
    private ListView mDrawerList;
    private RelativeLayout mDrawerMenu;
    private static final String TAG = "MyActivity";
    private TabHost mTabHost;
    private String[] mMenuTitles;
    private ArrayList<HashMap<String, String>> sList;
    private ImageView imgProfile = null;
    private ImageView imageBgProfile = null;
    private ListView cardlist = null;
    private ListView Lv_card_list = null;
    private FrameLayout profileLayout = null;
    private CharSequence mDrawerTitle;
    private boolean isFirstLoadMenu = false;
    private CharSequence mTitle;
    private Typeface typeFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeFace = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeueLight.ttf");
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mMenuTitles = getResources().getStringArray(R.array.menus_array);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerMenu = (RelativeLayout) findViewById(R.id.menu_left);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        imageBgProfile = (ImageView) findViewById(R.id.imageBgProfile);
        cardlist = (ListView) findViewById(R.id.card_list);
        profileLayout = (FrameLayout) findViewById(R.id.profileLayout);
        ImageView profileConsole = (ImageView) findViewById(R.id.imageBgProfile);
        Bitmap bitMapProfile = ((BitmapDrawable) getResources().getDrawable(R.drawable.img_snoop)).getBitmap();
        //imgProfile.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitMapProfile));
        imgProfile.setImageBitmap(bitMapProfile);
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        typeFace = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeueLight.ttf");
        setAppFont.setAppFont(mContainer, typeFace);

        sList = new ArrayList<HashMap<String, String>>();
        String[] menu = {"Home","Feed","Message","Item","Favorite","Logout","Setting"};
        String[] notification = {"0","23","13","2","0","0","0"};
        String[] img = {"ic_home","ic_feed","ic_message","ic_item","ic_boardpin","ic_logout","ic_setting2"};
        for(int i = 0 ; i < menu.length ; i++){
            map = new HashMap<String, String>();
            map.put(ListItem.KEY_TITLE,menu[i]);
            map.put(ListItem.KEY_IMG,img[i]);
            map.put(ListItem.KEY_NOTIFICAIONCOUNT,notification[i]);
            sList.add(map);
        }
        Menuadapter = new MenuAdapter(this, sList);
        mDrawerList.setAdapter(Menuadapter);
       // mDrawerList.setAdapter(new ArrayAdapter<String>(this,
               // R.layout.mylist, mMenuTitles));










        sList = new ArrayList<HashMap<String, String>>();
        //####################################################################################################
        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Hi , i'm interesting your product. Please contact me inbox.");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Hi , Could you please discount the item no 723A?");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/s160x160/1234628_10200284569510711_494409731_a.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "Is the item no 485A was sold out?");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash4/c44.44.550.550/s160x160/397061_346994718654706_1100389685_n.jpg");
        sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_TITLE, "I was transfer the money to you at 13:00PM , $200.00");
        map.put(ListItem.KEY_IMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn1/c33.33.414.414/s160x160/996822_3245921564722_159886767_n.jpg");
        sList.add(map);
        //####################################################################################################
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        View tabIndicator = null;
        TextView title = null;
        TextView msgCount = null;

       /* Intent intent = new Intent(this, TabsActivity.class);
        TabSpec Shopsspec = mTabHost.newTabSpec("Shops");
        tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
        title = (TextView) tabIndicator.findViewById(R.id.tab_title);
        msgCount = (TextView) tabIndicator.findViewById(R.id.txt_countTabsMsg);
        title.setText("Shops");
        title.setTypeface(typeFace);
        msgCount.setText("3");
        msgCount.setTypeface(typeFace);
        Shopsspec.setIndicator(tabIndicator);
        Shopsspec.setContent(intent);


        // Tab for Items

        TabSpec Itemsspec = mTabHost.newTabSpec("Items");
        tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
        title = (TextView) tabIndicator.findViewById(R.id.tab_title);
        msgCount = (TextView) tabIndicator.findViewById(R.id.txt_countTabsMsg);
        title.setText("Items");
        title.setTypeface(typeFace);
        msgCount.setText("97");
        msgCount.setTypeface(typeFace);
        Itemsspec.setContent(intent);

        // Tab for Massage
        TabSpec Messagespec = mTabHost.newTabSpec("Messages");
        // setting Title and Icon for the Tab
        tabIndicator = LayoutInflater.from(this).inflate(R.layout.tabmessage_indicator, getTabWidget(), false);
        title = (TextView) tabIndicator.findViewById(R.id.tab_title);
        msgCount = (TextView) tabIndicator.findViewById(R.id.txt_countTabsMsg);
        title.setText("Messages");
        title.setTypeface(typeFace);
        msgCount.setText("50");
        msgCount.setTypeface(typeFace);
        Messagespec.setIndicator(tabIndicator);
        Messagespec.setContent(intent);


        mTabHost.addTab(Shopsspec);
        mTabHost.addTab(Itemsspec);
        mTabHost.addTab(Messagespec);

        TabWidget tw = getTabWidget();
        for (int i = 0; i < tw.getChildCount(); i++) {
            View v = tw.getChildAt(i);
            v.setBackgroundDrawable(getResources().getDrawable
                    (R.drawable.tab_indicator_ab_example));
        }

        mTabHost.setCurrentTab(2);*/
        //####################################################################################################

        Lv_card_list = (ListView) findViewById(R.id.card_list);
        Ladapter = new LazyAdapter(this, sList);
        cardlist.setAdapter(Ladapter);
        //####################################################################################################
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout,
                R.drawable.ic_arrow,
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(1); // wait for coding
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerMenu);
        menu.findItem(R.id.action_more).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_more:
                Log.d(TAG, "action_more = Clicked");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_MENU_NUMBER, position);
        Log.d(TAG, "position = " + position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerMenu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();// Impotant!! to display the ic_drawer
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public static class PlanetFragment extends Fragment {
        public static final String ARG_MENU_NUMBER = "menu_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            int i = getArguments().getInt(ARG_MENU_NUMBER);
            String Menu = getResources().getStringArray(R.array.menus_array)[i];
            View rootView;
            ListView lv;

            switch (i) {
                case 0:
                    rootView = this.getView();
                    break;
                case 1:
                    myfeedActivity objfeedact = new myfeedActivity();
                    rootView = inflater.inflate(R.layout.mylistview, container, false);
                    myfeedActivity.userid = "90999900";
                    lv = (ListView)rootView.findViewById(R.id.myfeed_listView);
                    objfeedact.create_feed(this.getActivity(),this.getActivity(),lv,rootView);
                    break;
//testSVN
                case 2:
                    mymessageActivity objmsgact = new mymessageActivity();
                    rootView = inflater.inflate(R.layout.mylistview, container, false);
                    mymessageActivity.userid = "90999900";
                    lv = (ListView)rootView.findViewById(R.id.myfeed_listView);
                    objmsgact.create_feed(this.getActivity(),this.getActivity(),lv,rootView);
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_planet, container, false);
                    break;
            }
            /*int imageId = getResources().getIdentifier(Menu.toLowerCase(Locale.getDefault()),
                    "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);*/
            getActivity().setTitle(Menu);
            return rootView;
        }
    }


}

