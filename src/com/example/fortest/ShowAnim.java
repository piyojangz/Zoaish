package com.example.zoaish;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

/**
 * Created by ebiz_asc1 on 10/8/13.
 */
public class ShowAnim extends Animation {
    int targetMarginLeft;
    RelativeLayout view;

    public ShowAnim(RelativeLayout view, int targetMarginLeft) {
        this.view = view;
        this.targetMarginLeft = targetMarginLeft;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.leftMargin = (int) (targetMarginLeft * interpolatedTime);
        view.setLayoutParams (layoutParams);
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
