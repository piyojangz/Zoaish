package com.example.fortest.activity;

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
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.fortest.ListItem;
import com.example.fortest.R;
import com.example.fortest.custom.mymessageAdapter;
import com.example.fortest.helper.GifMovieView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ebiz_asc1 on 10/2/13.
 */
public class itemActivity extends AsyncTask<String, Void, Object> {

    public static String userid;
    protected Context context;
    protected Handler handler;
    protected List<String> l;
    protected ArrayList<HashMap<String, String>> sList;
    protected HashMap<String, String> map;
    protected ListView lv;
    protected Activity a;
    private static final String TAG = "MyActivity";
    private mymessageAdapter myfeed;
    private ProgressDialog dialog;
    protected int limit = 0;
    private boolean flag_loading = false;
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    private RelativeLayout insertPoint;
    protected static final int PERPAGE = 10;
    private View rootView;
    private GifMovieView gifView;

    public void create_feed(Activity a, Context context, ListView lv, View rootView) {
        this.context = context;
        this.a = a;
        this.lv = lv;
        this.rootView = rootView;
        this.l = new ArrayList<String>();
        this.sList = new ArrayList<HashMap<String, String>>();
        this.insertPoint = (RelativeLayout) this.rootView.findViewById(R.id.customloadingGround);
        this.gifView = new GifMovieView(this.context);
        this.insertPoint.addView(this.gifView);

        this.downloadData();
     /*   handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                downloadData();
            }
        }, 5000);*/


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
                    new LoadMoreTask().execute(currentPage + PERPAGE);

                    loading = true;
                }
            }
        });
    }


    protected ArrayList<HashMap<String, String>> loadData(int limit) {

        Log.d(TAG, "loadData...");

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYMESSAGEID, "1");
        map.put(ListItem.KEY_MYMESSAGEUSERID, "711");
        map.put(ListItem.KEY_MYMESSAGEUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash4/c44.44.550.550/s160x160/397061_346994718654706_1100389685_n.jpg");
        map.put(ListItem.KEY_MYMESSAGETITLE, "ตัวท๊อปนี่ปกติเท่าไหร่ครับ");
        map.put(ListItem.KEY_MYMESSAGEDESC, "ตัวนี้ลดได้อีกเท่าไหร่ครับ");
        map.put(ListItem.KEY_MYMESSAGEITEMID, "788737");
        this.sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYMESSAGEID, "1");
        map.put(ListItem.KEY_MYMESSAGEUSERID, "711");
        map.put(ListItem.KEY_MYMESSAGEUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
        map.put(ListItem.KEY_MYMESSAGETITLE, "อยากแลกไอแพดมินิ มือสอง");
        map.put(ListItem.KEY_MYMESSAGEDESC, "รับแลกไหมครับ พอดีผมมีเกิน 1 ตัว");
        map.put(ListItem.KEY_MYMESSAGEITEMID, "178555");
        this.sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYMESSAGEID, "1");
        map.put(ListItem.KEY_MYMESSAGEUSERID, "711");
        map.put(ListItem.KEY_MYMESSAGEUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/s160x160/1394120_3426753522850_1369128147_a.jpg");
        map.put(ListItem.KEY_MYMESSAGETITLE, "สนใจตัว 27 นิ้ว");
        map.put(ListItem.KEY_MYMESSAGEDESC, "สรุปนัดเจอที่ bts จตุจักรนะครับ");
        map.put(ListItem.KEY_MYMESSAGEITEMID, "547321");
        this.sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYMESSAGEID, "1");
        map.put(ListItem.KEY_MYMESSAGEUSERID, "711");
        map.put(ListItem.KEY_MYMESSAGEUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc3/c66.66.828.828/s160x160/972108_10200102406106592_1931031467_n.jpg");
        map.put(ListItem.KEY_MYMESSAGETITLE, "สอบถามหน่อยครับ");
        map.put(ListItem.KEY_MYMESSAGEDESC, "พอดีเห็นมันขึ้นหน้า feed เลยจะถาม");
        map.put(ListItem.KEY_MYMESSAGEITEMID, "099288");
        this.sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYMESSAGEID, "1");
        map.put(ListItem.KEY_MYMESSAGEUSERID, "711");
        map.put(ListItem.KEY_MYMESSAGEUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn1/c33.33.414.414/s160x160/996822_3245921564722_159886767_n.jpg");
        map.put(ListItem.KEY_MYMESSAGETITLE, "สนใจซื้อ ผ้าขาวม้าเอาไว้ห่อควายครับ");
        map.put(ListItem.KEY_MYMESSAGEDESC, "นัดจ่ายตังที่แยกเกษตรนะครับ");
        map.put(ListItem.KEY_MYMESSAGEITEMID, "236890");
        this.sList.add(map);

        map = new HashMap<String, String>();
        map.put(ListItem.KEY_MYMESSAGEID, "1");
        map.put(ListItem.KEY_MYMESSAGEUSERID, "711");
        map.put(ListItem.KEY_MYMESSAGEUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/s160x160/1234628_10200284569510711_494409731_a.jpg");
        map.put(ListItem.KEY_MYMESSAGETITLE, "ไม่ทราบว่าตัวนี้ลดได้อีกไหม");
        map.put(ListItem.KEY_MYMESSAGEDESC, "สรุปเดี๋ยวผมขับรถไปเอานะครับ");
        map.put(ListItem.KEY_MYMESSAGEITEMID, "658734");
        this.sList.add(map);
        for (int i = 0; i < limit; i++) {
            Log.d(TAG, "i = " + i);
            map = new HashMap<String, String>();
            map.put(ListItem.KEY_MYMESSAGEID, "1");
            map.put(ListItem.KEY_MYMESSAGEUSERID, "711");
            map.put(ListItem.KEY_MYMESSAGEUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
            map.put(ListItem.KEY_MYMESSAGETITLE, "สอบถามราคาสินค้า");
            map.put(ListItem.KEY_MYMESSAGEDESC, "สวัสดีจ้า");
            map.put(ListItem.KEY_MYMESSAGEITEMID, "788737");
            this.sList.add(map);
        }

        return this.sList;
    }

    private List<String> downloadData() {

        this.dialog = ProgressDialog.show(this.context, "Downloading Data..", "Please wait", true, false);
        this.execute();
        return l;
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
        myfeed = new mymessageAdapter(this.a, this.sList);
        this.lv.setAdapter(myfeed);
        // Pass the result data back to the main activity


        //Log.d(TAG, "done");
        if (this.dialog != null) {
            this.dialog.dismiss();
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
            // Log.d(TAG, "done");
            myfeed.notifyDataSetChanged();
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
                        insertPoint.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

        }

        private void execute(int x) {
            //dialog = ProgressDialog.show(context, "Downloading Data..", "Please wait", true, false);
            Animation anim = AnimationUtils.loadAnimation(context,
                    R.anim.swipe_motion);
            insertPoint.setVisibility(View.VISIBLE);
            insertPoint.startAnimation(anim);
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
            map = new HashMap<String, String>();
            map.put(ListItem.KEY_MYMESSAGEID, "1");
            map.put(ListItem.KEY_MYMESSAGEUSERID, "711");
            map.put(ListItem.KEY_MYMESSAGEUSERIMG, "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg");
            map.put(ListItem.KEY_MYMESSAGETITLE, "สอบถามราคาสนงค้า หมายเลข 12");
            map.put(ListItem.KEY_MYMESSAGEITEMID, "788737");
            map.put(ListItem.KEY_MYMESSAGEDESC, "สวัสดีจ้า");
            sList.add(map);
        }
    }
}
