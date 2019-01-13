package com.example.teamdevhackers.encomm.Account;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.example.teamdevhackers.encomm.HomeActivity;
import com.example.teamdevhackers.encomm.Mess.messnotification.AlarmBootReceiver;
import com.example.teamdevhackers.encomm.Mess.messnotification.BreakfastAlarmMaker;
import com.example.teamdevhackers.encomm.Mess.messnotification.DinnerAlarmMaker;
import com.example.teamdevhackers.encomm.Mess.messnotification.LunchAlarmMaker;
import com.example.teamdevhackers.encomm.Mess.messnotification.TiffinAlarmMaker;
import com.example.teamdevhackers.encomm.R;
import com.example.teamdevhackers.encomm.Utils.PreferenceManager;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
//import com.allandroidprojects.ecomsample.R;
import com.example.teamdevhackers.encomm.R;
import com.example.teamdevhackers.encomm.HomeActivity;

public class SplashActivity extends Activity implements Animation.AnimationListener {
    public static String TAG= SplashActivity.class.getSimpleName();
    Animation animFadeIn;
    LinearLayout linearLayout;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);
        PreferenceManager splashPrefManager = new PreferenceManager(this);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } else {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
        }
        // load the animation
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_in);
        // set animation listener
        animFadeIn.setAnimationListener(this);
        // animation for image
        linearLayout = (LinearLayout) findViewById(R.id.layout_linear);
        // start the animation
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.startAnimation(animFadeIn);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        //under Implementation
    }

    public void onAnimationEnd(Animation animation) {
        // Start Main Screen

    final Intent mainIntent = new Intent(SplashActivity.this, IntroScreen.class);
                            SplashActivity.this.startActivity(mainIntent);
                            SplashActivity.this.finish();



    }


    @Override
    public void onAnimationRepeat(Animation animation) {
        //under Implementation
    }

}





