package com.example.fortest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Breeshy on 10/3/13.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    private static final String TAG = "MyActivity";
    public DownloadImageTask(ImageView bmImage) {

        Log.d(TAG," bmImage : " + bmImage);


        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String[] urls) {
        String urldisplay = urls[0];

        Log.d(TAG," urldisplay : " + urldisplay);


        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            Log.d(TAG," in : " + in);

            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        Log.d(TAG," mIcon11 : " + mIcon11);
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {

        Log.d(TAG," result : " + result);
        BitmapDrawable img = new BitmapDrawable(result);
        bmImage.setImageBitmap(ImageHelper.getRoundedCornerBitmap(img.getBitmap()));
    }
}