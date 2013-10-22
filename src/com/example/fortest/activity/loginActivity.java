package com.example.fortest.activity;
import com.easy.facebook.android.data.Images;
import com.easy.facebook.android.data.Location;
import com.example.fortest.ListItem;
import com.example.fortest.ListUser;
import com.example.fortest.R;
import  com.easy.facebook.android.apicall.GraphApi;
import com.easy.facebook.android.data.User;
import com.easy.facebook.android.error.EasyFacebookError;
import com.easy.facebook.android.facebook.FBLoginManager;
import com.easy.facebook.android.facebook.Facebook;
import com.easy.facebook.android.facebook.LoginListener;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
        import android.app.Activity;
        import android.os.Bundle;

public class loginActivity extends Activity implements LoginListener {
    /** Called when the activity is first created.. */

    private FBLoginManager fbLoginManager;
    //replace it with your own Facebook App ID
    public final String KODEFUNFBAPP_ID = "599156356796723";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        connectToFacebook();
    }

    public void connectToFacebook(){

        //read about Facebook Permissions here:
        //http://developers.facebook.com/docs/reference/api/permissions/
        String permissions[] = {
                "user_about_me",
                "user_activities",
                "user_birthday",
                "user_checkins",
                "user_education_history",
                "user_events",
                "user_groups",
                "user_hometown",
                "user_interests",
                "user_likes",
                "user_location",
                "user_notes",
                "user_online_presence",
                "user_photo_video_tags",
                "user_photos",
                "user_relationships",
                "user_relationship_details",
                "user_religion_politics",
                "user_status",
                "user_videos",
                "user_website",
                "user_work_history",
                "email",
                "read_friendlists",
                "read_insights",
                "read_mailbox",
                "read_requests",
                "read_stream",
                "xmpp_login",
                "ads_management",
                "create_event",
                "manage_friendlists",
                "manage_notifications",
                "offline_access",
                "publish_checkins",
                "publish_stream",
                "rsvp_event",
                "sms",
                //"publish_actions",

                "manage_pages"

        };

        fbLoginManager = new FBLoginManager(this,
                R.layout.login,
                KODEFUNFBAPP_ID,
                permissions);

        if(fbLoginManager.existsSavedFacebook()){
            fbLoginManager.loadFacebook();
        }
        else{
            fbLoginManager.login();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data){
        fbLoginManager.loginSuccess(data);
    }

    public void loginSuccess(Facebook facebook) {
        GraphApi graphApi = new GraphApi(facebook);
        User user = new User();
        Location locate = new Location();
        Intent returnIntent = new Intent();
        try{
            user = graphApi.getMyAccountInfo();
            locate = user.getLocation();
            returnIntent.putExtra("fbresult", "true");
            //update your status if logged in
            //graphApi.setStatus("Test post from Zoaish");

            //***********************************************
            ListUser.id = user.getId();
            ListUser.first_name = user.getFirst_name();
            ListUser.last_name = user.getLast_name();
            ListUser.about = user.getAbout();
            ListUser.street = locate.getStreet();
            ListUser.city = locate.getCity();
            ListUser.country = locate.getCountry();

            //***********************************************






        } catch(EasyFacebookError e){
            returnIntent.putExtra("fbresult", "false");
            Log.d("TAG: ", e.toString());
        }
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void logoutSuccess() {
        fbLoginManager.displayToast("Logout Success!");
    }

    public void loginFail() {
        fbLoginManager.displayToast("Login Epic Failed!");
    }
}