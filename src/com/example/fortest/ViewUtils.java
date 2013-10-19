package com.example.fortest;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Utility methods for Views.
 */
public class ViewUtils {
    private static final String TAG = "Menu";
    private ViewUtils() {
    }

    public static void setViewWidths(View view, View[] views) {
        int w = view.getWidth();
        int h = view.getHeight();
        for (int i = 0; i < views.length; i++) {
            View v = views[i];
            v.layout((i + 1) * w, 0, (i + 2) * w, h);
            printView("view[" + i + "]", v);

            Log.d(TAG,"view[" + i + "]");
        }
    }

    public static void printView(String msg, View v) {
        System.out.println(msg + "=" + v);
        if (null == v) {
            return;
        }
        Log.d(TAG,"[" + v.getLeft());
        Log.d(TAG,", " + v.getTop());
        Log.d(TAG,", w=" + v.getWidth());
        Log.d(TAG,", h=" + v.getHeight() + "]");
        Log.d(TAG,"mw=" + v.getMeasuredWidth() + ", mh=" + v.getMeasuredHeight());
        Log.d(TAG,"scroll [" + v.getScrollX() + "," + v.getScrollY() + "]");



    }

    public static void initListView(Context context, ListView listView, String prefix, int numItems, int layout) {
        // By using setAdpater method in listview we an add string array in list.
        String[] arr = new String[numItems];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = prefix + (i + 1);

            Log.d(TAG,"Move + " + i);
        }
        listView.setAdapter(new ArrayAdapter<String>(context, layout, arr));
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = view.getContext();
                String msg = "item[" + position + "]=" + parent.getItemAtPosition(position);
                Toast.makeText(context, msg, 1000).show();

                Log.d(TAG,"msg = " + msg);
            }
        });
    }
}
