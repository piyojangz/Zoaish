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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fortest.ListItem;
import com.example.fortest.R;
import com.example.fortest.custom.setAppFont;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.HashMap;


public class myfeedAdapter extends BaseAdapter implements Transformation {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    private static final String TAG = "MyActivity";
    private  Typeface typeFace;
    public myfeedAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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

        convertView = inflater.inflate(R.layout.myfeed_list, parent, false);


        LinearLayout myfeed_profile_bg = (LinearLayout) convertView.findViewById(R.id.myfeed_profile_bg);
        ImageView image_feed = (ImageView) convertView.findViewById(R.id.image_feed);
        ImageView img_myfeed_profilepic = (ImageView) convertView.findViewById(R.id.img_myfeed_profilepic);
        TextView txt_myfeed_title = (TextView) convertView.findViewById(R.id.txt_myfeed_title);
        TextView txt_myfeed_area = (TextView) convertView.findViewById(R.id.txt_myfeed_area);
        TextView txt_myfeed_description = (TextView) convertView.findViewById(R.id.txt_myfeed_description);


        if(position % 2 == 0){
            myfeed_profile_bg.setBackgroundResource(R.drawable.circle3);
        }
        else{
            myfeed_profile_bg.setBackgroundResource(R.drawable.circle4);
        }
        HashMap<String, String> smart = new HashMap<String, String>();
        Context context = this.activity;
        smart = data.get(position);
        String urlItem = smart.get(ListItem.KEY_MYFEED_ITEMIMG).toString();
        String url = smart.get(ListItem.KEY_MYFEED_ITEMUSERIMG).toString();
        txt_myfeed_title.setText(smart.get(ListItem.KEY_MYFEED_ITEMTITLE));
        txt_myfeed_title.setTypeface(typeFace);
        txt_myfeed_area.setText(smart.get(ListItem.KEY_MYFEED_ITEMAREA));
        txt_myfeed_area.setTypeface(typeFace);
        txt_myfeed_description.setText(smart.get(ListItem.KEY_MYFEED_ITEMDESCRIPTION));
        txt_myfeed_description.setTypeface(typeFace);
        Picasso.with(context).load(urlItem).into(image_feed);
        Picasso.with(context).load(url).transform(this).into(img_myfeed_profilepic);
        return convertView;
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