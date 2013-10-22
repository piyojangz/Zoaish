package com.example.zoaish;

import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
/**
 * Created by ebiz_asc1 on 10/8/13.
 */
public class MyScaler extends ScaleAnimation {
    private static final String TAG = "MyActivity";
    private View mView;

    private RelativeLayout.LayoutParams mLayoutParams;

    private int mMarginLeftFromX, mMarginLeftToX;

    private boolean mVanishAfter = false;

    public MyScaler(float fromX, float toX, float fromY, float toY, int duration, View view,
                    boolean vanishAfter) {
        super(fromX, toX, fromY, toY);

        setDuration(duration);
        mView = view;
        mVanishAfter = vanishAfter;
        mLayoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        int width = mView.getWidth();
        mMarginLeftFromX = (int) (width * fromX) + mLayoutParams.leftMargin - width;
        mMarginLeftToX = (int) (0 - ((width * toX) + mLayoutParams.leftMargin)) - width;

        Log.d(TAG,"mMarginLeftFromX = " + mMarginLeftFromX);
        Log.d(TAG,"mMarginLeftToX = " + mMarginLeftToX);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime < 1.0f) {
            int newMargin = mMarginLeftFromX
                    + (int) ((mMarginLeftToX - mMarginLeftFromX) * interpolatedTime);

            Log.d(TAG,"new Margin = " + newMargin);

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mView.getLayoutParams();
            layoutParams.leftMargin = newMargin;

            Log.d(TAG,"mLayoutParams = " + layoutParams);
            mView.setLayoutParams(layoutParams);
            //mView.getParent().requestLayout();
        } else if (mVanishAfter) {
            mView.setVisibility(View.GONE);
        }
    }

}

