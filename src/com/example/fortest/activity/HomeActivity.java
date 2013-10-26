package com.example.fortest.activity;

import com.easy.facebook.android.data.User;
import com.example.fortest.ListItem;
import com.example.fortest.ListUser;
import com.example.fortest.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fortest.business.BuUserdata;
import com.example.fortest.custom.progressbarCustom;
import com.example.fortest.helper.GifMovieView;
import com.example.fortest.helper.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ebiz_asc1
 */

public class HomeActivity extends AsyncTask<String, Void, Object> {
    private ImageView imgProfile = null;
    private ImageView imageBgProfile = null;
    private Activity a;
    private Context context;
    private View rootView;
    public RelativeLayout insertPoint;
    private GifMovieView gifView;
    private TextView txtFullName;
    private TextView txtCustomdesc;
    private SessionManager session;
    public void setInstance(Activity a, Context context, View rootView,SessionManager session) {
        this.context = context;
        this.a = a;
        this.session = session;
        this.rootView = rootView;
        this.insertPoint = (RelativeLayout) this.rootView.findViewById(R.id.customloadingGround);
        this.txtFullName = (TextView) this.rootView.findViewById(R.id.txtFullName);
        this.txtCustomdesc = (TextView) this.rootView.findViewById(R.id.txtCustomdesc);
        this.gifView = new GifMovieView(this.context);
        this.insertPoint.addView(this.gifView);
        this.imgProfile = (ImageView) this.rootView.findViewById(R.id.imgProfile);
        this.imageBgProfile = (ImageView) this.rootView.findViewById(R.id.imageBgProfile);
        ArrayList<HashMap<String, String>> MyArrList;
        MyArrList = BuUserdata.getusermemberbyfacebookid(this.session.getUserDetails().get("facebookid"));
        Log.d(ListItem.TAG,"MyArrList = " + MyArrList);
        this.txtFullName.setText(MyArrList.get(0).get("name"));
        this.txtCustomdesc.setText(MyArrList.get(0).get("about"));
        Picasso.with(context).load(MyArrList.get(0).get("cover_photo")).into(imageBgProfile);
        Picasso.with(context).load("http://graph.facebook.com/" + this.session.getUserDetails().get("facebookid") + "/picture?type=large").into(imgProfile);
        this.execute();
    }

    @Override
    protected void onPreExecute() {
        Animation anim = AnimationUtils.loadAnimation(context,
                R.anim.swipe_motion);
        insertPoint.setVisibility(View.VISIBLE);
        insertPoint.startAnimation(anim);
    }

    @Override
    protected Object doInBackground(String... args) {
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
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


}


