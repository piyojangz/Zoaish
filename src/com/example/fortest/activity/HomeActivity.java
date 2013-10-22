package com.example.fortest.activity;
import com.example.fortest.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fortest.custom.progressbarCustom;
import com.example.fortest.helper.GifMovieView;

/**
 * @author ebiz_asc1
 */
public class HomeActivity extends AsyncTask<String, Void, Object> {
    private ImageView imgProfile = null;
    private Activity a;
    private Context context;
    private View rootView;
    private ProgressDialog dialog;
    public RelativeLayout insertPoint;
    private GifMovieView gifView;
    private progressbarCustom objProgressDialog;

    public void setInstance(Activity a, Context context, View rootView) {
        this.context = context;
        this.a = a;
        this.rootView = rootView;
        this.insertPoint = (RelativeLayout) this.rootView.findViewById(R.id.customloadingGround);
        this.gifView = new GifMovieView(this.context);
        this.insertPoint.addView(this.gifView);
        this.imgProfile = (ImageView) this.rootView.findViewById(R.id.imgProfile);
        Bitmap bitMapProfile = ((BitmapDrawable) this.a.getResources().getDrawable(R.drawable.img_snoop)).getBitmap();
        this.imgProfile.setImageBitmap(bitMapProfile);


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


