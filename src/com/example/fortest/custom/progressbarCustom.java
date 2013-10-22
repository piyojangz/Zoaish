package com.example.fortest.custom;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.fortest.R;
import com.example.fortest.activity.myfeedActivity;

/**
 * Created by ebiz_asc1 on 10/21/13.
 */
public class progressbarCustom extends myfeedActivity {

    public void showDialog() {
        Animation anim = AnimationUtils.loadAnimation(context,
                R.anim.swipe_motion);
        insertPoint.setVisibility(View.VISIBLE);
        insertPoint.startAnimation(anim);

    }

    public void endDialog() {
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
                    lv.setAdapter(myfeed);
                    //myfeed.notifyDataSetChanged();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }
}
