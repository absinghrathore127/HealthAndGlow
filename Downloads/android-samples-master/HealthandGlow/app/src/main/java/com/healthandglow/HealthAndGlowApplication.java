package com.healthandglow;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.healthandglow.utils.AppConstants;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @Author Abhimanyu singh on 9/17/2016.
 * @Company Docket Tech Solutions Pvt. Ltd.
 **/
public class HealthAndGlowApplication extends Application {

    public static final String TAG = HealthAndGlowApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static HealthAndGlowApplication mInstance;


    public static SharedPreferences countryCode_Prefs, launcherPrefs, docketPrefs;

    public static ArrayList<WeakReference<AppCompatActivity>> activity_stack = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();


        mInstance = this;

       /* launcherPrefs = getSharedPreferences(AppConstants.LauncherPrefs, MODE_PRIVATE);
        docketPrefs = getSharedPreferences(AppConstants.DocketPrefs, MODE_PRIVATE);*/
    }

    /**
     * Add the activity as weak reference to activity stack.
     *
     * @param act
     */
    public static void addToActivityStack(AppCompatActivity act) {
        WeakReference<AppCompatActivity> ref = new WeakReference<>(act);
        activity_stack.add(ref);

    }

    /**
     * Kill all the activities on activity stack
     **/
    public static void killAllActivities() {
        for (WeakReference<AppCompatActivity> ref : activity_stack) {
            if (ref != null && ref.get() != null) {
                ref.get().finish();
            }
        }
        activity_stack.clear(); // but clear all the activity references
    }

    public static synchronized HealthAndGlowApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


}