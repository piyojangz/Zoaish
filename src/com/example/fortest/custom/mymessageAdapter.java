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


public class mymessageAdapter extends BaseAdapter implements Transformation {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    private static final String TAG = "MyActivity";
    private  Typeface typeFace;
    public mymessageAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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

        convertView = inflater.inflate(R.layout.mymessage_list, parent, false);

        ImageView img_mymsg_profilepic = (ImageView) convertView.findViewById(R.id.img_mymsg_profilepic);
        TextView txt_msgtitle = (TextView) convertView.findViewById(R.id.txt_msgtitle);
        TextView txt_msgdesc = (TextView) convertView.findViewById(R.id.txt_msgdesc);
        TextView txt_msgitemid = (TextView) convertView.findViewById(R.id.txt_msgitemid);

        HashMap<String, String> smart = new HashMap<String, String>();
        Context context = this.activity;
        smart = data.get(position);
        String urlItem = smart.get(ListItem.KEY_MYMESSAGEUSERIMG).toString();
        txt_msgtitle.setText(smart.get(ListItem.KEY_MYMESSAGETITLE));
        txt_msgtitle.setTypeface(typeFace,Typeface.BOLD);
        txt_msgdesc.setText(smart.get(ListItem.KEY_MYMESSAGEDESC));
        txt_msgdesc.setTypeface(typeFace);
        txt_msgitemid.setText("#"+smart.get(ListItem.KEY_MYMESSAGEITEMID));
        txt_msgitemid.setTypeface(typeFace);
        Picasso.with(context).load(urlItem).into(img_mymsg_profilepic);
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