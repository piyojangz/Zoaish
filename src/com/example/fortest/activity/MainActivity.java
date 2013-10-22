package com.example.fortest.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.fortest.custom.MenuAdapter;
import com.example.fortest.ListItem;
import com.example.fortest.custom.setAppFont;
import com.example.fortest.R;

/**
 * @author ebiz_asc1
 */
public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private MenuAdapter Menuadapter;
    private HashMap<String, String> map;
    private ListView mDrawerList;
    private RelativeLayout mDrawerMenu;
    private String[] mMenuTitles;
    private ArrayList<HashMap<String, String>> sList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private Typeface typeFace;
    private Integer selectedposition;
    private Boolean is_fistseletedItem = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        typeFace = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeueLight.ttf");
        mTitle = mDrawerTitle = getTitle();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerMenu = (RelativeLayout) findViewById(R.id.menu_left);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        typeFace = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeueLight.ttf");
        setAppFont.setAppFont(mContainer, typeFace);
        sList = new ArrayList<HashMap<String, String>>();
        String[] menu = {"Home", "Feed", "Message", "Item", "Favorite", "Logout", "Setting"};
        String[] notification = {"0", "23", "13", "2", "0", "0", "0"};
        String[] img = {"ic_home", "ic_feed", "ic_message", "ic_item", "ic_boardpin", "ic_logout", "ic_setting2"};
        mMenuTitles = menu;
        for (int i = 0; i < menu.length; i++) {
            map = new HashMap<String, String>();
            map.put(ListItem.KEY_TITLE, menu[i]);
            map.put(ListItem.KEY_IMG, img[i]);
            map.put(ListItem.KEY_NOTIFICAIONCOUNT, notification[i]);
            sList.add(map);
        }


        Menuadapter = new MenuAdapter(this, sList);
        mDrawerList.setAdapter(Menuadapter);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout,
                R.drawable.ic_arrow,
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                activeItem();
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
                Log.d(ListItem.TAG, "action_more = Clicked");
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
        // update selected item and title, then close the drawer
        this.selectedposition = position;
        if (is_fistseletedItem) activeItem();
        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerMenu);
    }

    private void activeItem() {
        // update the main content by replacing fragments
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_MENU_NUMBER, MainActivity.this.selectedposition);
        //Log.d(ListItem.TAG, "position = " + position);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        is_fistseletedItem = false;
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
            ListItem.USERID = "90999900";
            switch (i) {
                case 0:
                    HomeActivity objhome = new HomeActivity();
                    rootView = inflater.inflate(R.layout.activity_home, container, false);
                    objhome.setInstance(this.getActivity(), this.getActivity(), rootView);
                    break;
                case 1:
                    myfeedActivity objfeedact = new myfeedActivity();
                    rootView = inflater.inflate(R.layout.mylistview, container, false);
                    lv = (ListView) rootView.findViewById(R.id.myfeed_listView);
                    objfeedact.create_feed(this.getActivity(), this.getActivity(), lv, rootView);

                    break;
//testSVN2
                case 2:
                    mymessageActivity objmsgact = new mymessageActivity();
                    rootView = inflater.inflate(R.layout.mylistview, container, false);
                    lv = (ListView) rootView.findViewById(R.id.myfeed_listView);
                    objmsgact.create_feed(this.getActivity(), this.getActivity(), lv, rootView);
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_planet, container, false);
                    break;
            }
            Animation anim = AnimationUtils.loadAnimation(this.getActivity().getBaseContext(),
                    R.anim.showin);
            rootView.setAnimation(anim);
            /*int imageId = getResources().getIdentifier(Menu.toLowerCase(Locale.getDefault()),
                    "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);*/
            getActivity().setTitle(Menu);
            return rootView;
        }
    }


}

