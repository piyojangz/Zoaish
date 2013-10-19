package com.example.fortest.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fortest.ListItem;
import com.example.fortest.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.HashMap;


public class MenuAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    private static final String TAG = "BallLogs";
    private Typeface typeFace;

    public MenuAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
        typeFace = Typeface.createFromAsset(this.activity.getAssets(), "fonts/HelveticaNeueLight.ttf");
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.menulist_row, parent, false);

        TextView txt_title = (TextView) convertView.findViewById(R.id.title);
        TextView txt_notificationcount = (TextView) convertView.findViewById(R.id.txt_notificationcount);
        LinearLayout lnotification = (LinearLayout) convertView.findViewById(R.id.lnotification);
        ImageView list_image = (ImageView) convertView.findViewById(R.id.list_image);

        txt_title.setTypeface(this.typeFace);
        //ImageView imgUser = (ImageView) convertView.findViewById(R.id.imgUser);
        HashMap<String, String> smart = new HashMap<String, String>();
        smart = data.get(position);


        txt_title.setText(smart.get(ListItem.KEY_TITLE));
        int id = this.activity.getResources().getIdentifier("com.example.fortest:drawable/" + smart.get(ListItem.KEY_IMG), null, null);
        list_image.setImageResource(id);
        if (smart.get(ListItem.KEY_NOTIFICAIONCOUNT) != "0") {
            txt_notificationcount.setText(smart.get(ListItem.KEY_NOTIFICAIONCOUNT));
        } else {
            lnotification.setVisibility(View.INVISIBLE);
        }

        Log.d(TAG, "Log");

        // String url = smart.get(ListItem.KEY_IMG).toString();


        // Context context = activity;

        // Picasso.with(context).load(url).transform(this).into(imgUser);


        return convertView;
    }


}