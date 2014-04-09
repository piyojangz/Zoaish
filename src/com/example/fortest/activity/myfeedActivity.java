package com.example.fortest.activity;

import com.example.fortest.helper.GifMovieView;
import com.example.fortest.custom.myfeedAdapter;
import com.example.fortest.pages.feed_detail;
import com.example.fortest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.fortest.ListItem;
import com.example.fortest.helper.SessionManager;
import com.example.fortest.helper.JsonHelper;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ebiz_asc1 on 10/2/13.
 */
public class myfeedActivity extends AsyncTask<String, Void, Object> {

    public static String userid;
    protected Context context;
    protected Handler handler;
    protected List<String> l;
    protected ArrayList<HashMap<String, String>> sList;
    protected HashMap<String, String> map;
    public ListView lv;
    Activity a;
    private static final String TAG = "MyActivity";
    private static final String TAGSERVICE = "Service";
    public myfeedAdapter myfeed;
    private ProgressDialog dialog;
    protected int limit = 0;
    private boolean flag_loading = false;
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    public RelativeLayout insertPoint;
    protected static final int PERPAGE = 10;
    private View rootView;
    private GifMovieView gifView;
    private SessionManager session;

    public void create_feed(final Activity a, final Context context, ListView lv, View rootView, SessionManager session) {
        this.context = context;
        this.a = a;
        this.lv = lv;
        this.session = session;
        this.rootView = rootView;
        this.l = new ArrayList<String>();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                downloadData();
            }
        }, 0);
        this.sList = new ArrayList<HashMap<String, String>>();
        this.insertPoint = (RelativeLayout) this.rootView.findViewById(R.id.customloadingGround);
        this.gifView = new GifMovieView(this.context);
        this.insertPoint.addView(this.gifView);
        myfeed = new myfeedAdapter(this.a, this.sList);

        this.lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                        // currentPage++;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    // Log.d(TAG, "scroll do = " + currentPage);
                    // I load the next page of gigs using a background task,
                    // but you can call any function here.
                    new LoadMoreTask().execute(currentPage + 10);
                    loading = true;
                }
            }
        });


        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // a.finish();

                String FEED_ID = sList.get(i).get(ListItem.KEY_MYFEED_ITEMID).toString();
                String FEED_TITLE = sList.get(i).get(ListItem.KEY_MYFEED_ITEMTITLE).toString();
                Intent myIntent = new Intent(a, feed_detail.class);
                myIntent.putExtra(ListItem.KEY_MYFEED_ITEMID, FEED_ID);
                myIntent.putExtra(ListItem.KEY_MYFEED_ITEMTITLE, FEED_TITLE);
                a.startActivity(myIntent);
            }
        });

    }


    protected ArrayList<HashMap<String, String>> loadData(int limit) {

        String url = "http://services.zoaish.com/services/getfeedlist";
        //http://www.learn2crack.com/2013/11/listview-from-json-example.html
        //http://api.learn2crack.com/android/jsonos/
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(new  Array("userid ", "1"));
        // params.add(new Array("pagefrom ", "0"));
        // params.add(new Array("pagesize ", "10"));
        String result = JsonHelper.getJSONUrl(url, params);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray data = jsonObject.getJSONArray("data");
            Log.d(TAGSERVICE, "aaaaaa..." + data.length());
            String str = null;

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);
                map = new HashMap<String, String>();
                map.put(ListItem.KEY_MYFEED_ITEMID, c.getString(ListItem.KEY_MYFEED_ITEMID));
                map.put(ListItem.KEY_MYFEED_ITEMUSERID, c.getString(ListItem.KEY_MYFEED_ITEMUSERID));
                map.put(ListItem.KEY_MYFEED_ITEMUSERIMG, c.getString(ListItem.KEY_MYFEED_ITEMUSERIMG));
                map.put(ListItem.KEY_MYFEED_ITEMIMG, c.getString(ListItem.KEY_MYFEED_ITEMIMG));
                map.put(ListItem.KEY_MYFEED_ITEMTITLE, c.getString(ListItem.KEY_MYFEED_ITEMTITLE));
                map.put(ListItem.KEY_MYFEED_ITEMAREA, c.getString(ListItem.KEY_MYFEED_ITEMAREA));
                map.put(ListItem.KEY_MYFEED_ITEMDESCRIPTION, c.getString(ListItem.KEY_MYFEED_ITEMDESCRIPTION));
                this.sList.add(map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
/*

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYFEED_ITEMID, "1");
        map.put(ListItem.KEY_MYFEED_ITEMUSERID, "711");
        map.put(ListItem.KEY_MYFEED_ITEMUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        map.put(ListItem.KEY_MYFEED_ITEMIMG, "http://www.phonehip.com/oc-content/uploads/449.jpg");
        map.put(ListItem.KEY_MYFEED_ITEMTITLE, "จอLED 27-inch ของใหม่...");
        map.put(ListItem.KEY_MYFEED_ITEMAREA, "jatujak , 3.0 Km.");
        map.put(ListItem.KEY_MYFEED_ITEMDESCRIPTION, "ขาย จอ LED Apple Cinema Thunderbolt Display 27-inch ของใหม่ ยังไม่แกะกล่อง ประกันศูนย์ 1ปี พร้อมบริการหลังการขายระดับเทพครับ.");
        this.sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYFEED_ITEMID, "2");
        map.put(ListItem.KEY_MYFEED_ITEMUSERID, "711");
        map.put(ListItem.KEY_MYFEED_ITEMUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/s160x160/1234628_10200284569510711_494409731_a.jpg");
        map.put(ListItem.KEY_MYFEED_ITEMIMG, "http://www.macthai.com/wp-content/uploads/2012/11/2013-imac.jpeg");
        map.put(ListItem.KEY_MYFEED_ITEMTITLE, "ลพบุรี ขาย Imac 27 ตัวบาง สภาพ งามๆ...");
        map.put(ListItem.KEY_MYFEED_ITEMAREA, "bangkhen , 7.0 Km.");
        map.put(ListItem.KEY_MYFEED_ITEMDESCRIPTION, "ขาย iMac 27\" ตัว 2012 CPU intel core i5 Quad-Core 2.9 GHz แรม 8 Gb");
        this.sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYFEED_ITEMID, "3");
        map.put(ListItem.KEY_MYFEED_ITEMUSERID, "711");
        map.put(ListItem.KEY_MYFEED_ITEMUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash4/c44.44.550.550/s160x160/397061_346994718654706_1100389685_n.jpg");
        map.put(ListItem.KEY_MYFEED_ITEMIMG, "http://www.ukorat.com/wp-content/uploads/2013/05/iphone-5-app-store.jpg");
        map.put(ListItem.KEY_MYFEED_ITEMTITLE, "สภาพ 98 % สวยงาม ดูของได้ ที่ลพบุรีครับ...");
        map.put(ListItem.KEY_MYFEED_ITEMAREA, "jatujak , 1.0 Km.");
        map.put(ListItem.KEY_MYFEED_ITEMDESCRIPTION, "สภาพ 98 % สวยงาม ดูของได้ ที่ลพบุรีครับ\n" +
                "49500 บาท ถ้วน อุปกรณ์ ครบกล่องใหม่แน่นอน ประกันเหลือ 6 เดือน\n" +
                "เครื่องศูนย์ไทย แทบไม่ได้ใช้งาน ซื้อมาเปิดเพลงฟัง ชัดๆ.");
        this.sList.add(map);
        for (int i = 0; i < limit; i++) {
            //  Log.d(TAG, "i = " + i);
            map = new HashMap<String, String>();
            map.put(ListItem.KEY_MYFEED_ITEMID, "1");
            map.put(ListItem.KEY_MYFEED_ITEMUSERID, "711");
            map.put(ListItem.KEY_MYFEED_ITEMUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/s160x160/1234628_10200284569510711_494409731_a.jpg");
            map.put(ListItem.KEY_MYFEED_ITEMIMG, "http://www.propertycc.com/img/no-img.png");
            map.put(ListItem.KEY_MYFEED_ITEMTITLE, "Used macbook pro 98%...");
            map.put(ListItem.KEY_MYFEED_ITEMAREA, "jatujak , 3.0 Km.");
            map.put(ListItem.KEY_MYFEED_ITEMDESCRIPTION, "Lorem ipsum dolor sit amet, posuere nullam euismod, non nec ac, et justo eros sapien consectetuer.");
            this.sList.add(map);
        }*/

        return this.sList;
    }

    private List<String> downloadData() {
        this.execute();
        return l;
    }

    @Override
    protected void onPreExecute() {
        Animation anim = AnimationUtils.loadAnimation(context,
                R.anim.swipe_motion);
        insertPoint.setVisibility(View.VISIBLE);
        insertPoint.startAnimation(anim);
    }

    private List<String> feedData(String value) {
        l.add(value);
        Log.d(TAG, "List = " + l.size());
        return l;
    }

    @Override
    protected Object doInBackground(String... args) {

        sList = this.loadData(this.limit + PERPAGE);

        flag_loading = false;


        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        // Pass the result data back to the main activity
        myfeed = new myfeedAdapter(this.a, this.sList);
        if (insertPoint.VISIBLE != View.INVISIBLE) {
            Animation anim = AnimationUtils.loadAnimation(context,
                    R.anim.swipe_motionout);
            insertPoint.startAnimation(anim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    lv.setAdapter(myfeed);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    insertPoint.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

    }

    private class LoadMoreTask extends AsyncTask<String, Void, Object> {
        protected int currentPage;

        @Override
        protected Object doInBackground(String... args) {
            loadMore(this.currentPage);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // lv.setAdapter(myfeed);
            //myfeed.notifyDataSetChanged();
            if (insertPoint.VISIBLE != View.INVISIBLE) {
                Animation anim = AnimationUtils.loadAnimation(context,
                        R.anim.swipe_motionout);
                insertPoint.startAnimation(anim);

                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        myfeed.notifyDataSetChanged();
                        insertPoint.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

        }

        @Override
        protected void onPreExecute() {
            Animation anim = AnimationUtils.loadAnimation(context,
                    R.anim.swipe_motion);
            insertPoint.setVisibility(View.VISIBLE);
            insertPoint.startAnimation(anim);

        }

        private void execute(int x) {
            this.currentPage = x;
            this.execute();
        }
    }

    private void loadMore(int _currentPage) {
        //_currentPage mean จำนวน items ปัจจุบันที่เราดึงมา อย่างเช่นรอบแรกดึง 1-10 -> รอบต่อไปก็จะเป็น 11 - 20 โดยเพิ่งทีละ 10
        // Log.d(TAG, "this.currentPage = " + _currentPage);
        for (int i = _currentPage; i < _currentPage + PERPAGE; i++) {
            // Log.d(TAG, "i = " + i);
            map = new HashMap<String, String>();
            map.put(ListItem.KEY_MYFEED_ITEMID, "1");
            map.put(ListItem.KEY_MYFEED_ITEMUSERID, "711");
            map.put(ListItem.KEY_MYFEED_ITEMUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/s160x160/1234628_10200284569510711_494409731_a.jpg");
            map.put(ListItem.KEY_MYFEED_ITEMIMG, "http://www.propertycc.com/img/no-img.png");
            map.put(ListItem.KEY_MYFEED_ITEMTITLE, "Used macbook pro 98%...");
            map.put(ListItem.KEY_MYFEED_ITEMAREA, "Jatujak , 3.0 Km.");
            map.put(ListItem.KEY_MYFEED_ITEMDESCRIPTION, "Lorem ipsum dolor sit amet, posuere nullam euismod, non nec ac, et justo eros sapien consectetuer.");
            sList.add(map);
        }
    }
}
