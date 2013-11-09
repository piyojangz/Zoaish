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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.fortest.business.BuUserdata;
import com.example.fortest.custom.progressbarCustom;
import com.example.fortest.helper.GifMovieView;
import com.example.fortest.helper.SessionManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ebiz_asc1
 */

public class HomeActivity extends AsyncTask<String, Void, Object> implements Transformation {
    private ImageView imgProfile = null;
    private ImageView imageBgProfile = null;
    private Activity a;
    private Context context;
    private View rootView;
    public RelativeLayout insertPoint;
    private GifMovieView gifView;
    private TextView txtFullName;
    private TableLayout tb_shopfollowing;
    private SessionManager session;
    private LinearLayout frameimgProfile;
    private ImageView imgView;
    private TableRow tr;
    LayoutInflater mInflater;
    private static LayoutInflater inflater = null;

    public void setInstance(Activity a, Context context, View rootView, SessionManager session) {
        this.context = context;
        this.a = a;
        this.session = session;
        this.rootView = rootView;
        this.insertPoint = (RelativeLayout) this.rootView.findViewById(R.id.customloadingGround);
        this.txtFullName = (TextView) this.rootView.findViewById(R.id.txtFullName);
        this.tb_shopfollowing = (TableLayout) this.rootView.findViewById(R.id.tb_shopfollowing);
        this.gifView = new GifMovieView(this.context);
        this.insertPoint.addView(this.gifView);
        this.imgProfile = (ImageView) this.rootView.findViewById(R.id.imgProfile);
        this.frameimgProfile = (LinearLayout) this.rootView.findViewById(R.id.frameimgProfile);
        this.imageBgProfile = (ImageView) this.rootView.findViewById(R.id.imageBgProfile);

        ArrayList<HashMap<String, String>> MyArrList;
        MyArrList = BuUserdata.getusermemberbyfacebookid(this.session.getUserDetails().get("facebookid"));
        this.txtFullName.setText(MyArrList.get(0).get("name"));
        Picasso.with(context).load(MyArrList.get(0).get("cover_photo")).into(imageBgProfile);
        Picasso.with(context).load("http://graph.facebook.com/" + this.session.getUserDetails().get("facebookid") + "/picture?type=large").transform(this).into(imgProfile);


       /* String[] imgsrc =  {"https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn1/c66.66.825.825/s160x160/936292_10151382072052691_142916506_n.jpg", "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg", "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/c42.42.524.524/s160x160/1236460_10200487087965988_1178539083_n.jpg", "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/c50.50.621.621/s160x160/296864_4122090932581_473985757_n.jpg"};
        for (int x = 0; x < 4; x++) {
            tr = new TableRow(a);
            for (int i = 0; i < 4; i++) {
                tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                mInflater = LayoutInflater.from(this.context);
                View imgfollowingview = mInflater.inflate(R.layout.myimgshopfollowing, null,true);
                ImageView imgShopfollowing = (ImageView) imgfollowingview.findViewById(R.id.imgShopfollowing);
                LinearLayout frameimgShopfollowing = (LinearLayout) imgfollowingview.findViewById(R.id.frameimgShopfollowing);
                Log.d(ListItem.TAG," imgShopfollowing.getId() = " +  frameimgShopfollowing);
                Picasso.with(context).load(imgsrc[i]).into(imgShopfollowing);
                tr.addView(frameimgShopfollowing, i);
            }
            tb_shopfollowing.addView(tr,x);
        }
*/

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

    @Override
    public Bitmap transform(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);


        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());


        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);


        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));


        if (output != bitmap) {
            bitmap.recycle();
        }


        return output;


    }

    @Override
    public String key() {
        return "circle()";
    }
}


