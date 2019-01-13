package com.example.teamdevhackers.encomm.Utils;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.teamdevhackers.encomm.Account.LoginActivity;
import com.example.teamdevhackers.encomm.Complaints.ComplaintsActivity;
import com.example.teamdevhackers.encomm.Council.CouncilActivity;
import com.example.teamdevhackers.encomm.Feedback.Feedback;
import com.example.teamdevhackers.encomm.HomeActivity;
import com.example.teamdevhackers.encomm.Mess.MessActivity;
import com.example.teamdevhackers.encomm.R;
import com.example.teamdevhackers.encomm.Resources.ResourceActivity;
import com.example.teamdevhackers.encomm.Settings.SettingsActivity;
import com.example.teamdevhackers.encomm.User.ProfileActivity;
import com.example.teamdevhackers.encomm.User.UpdatePassword;


public class CommonFunctions {

    public static boolean navigationItemSelect(MenuItem item, final Activity activity) {
        int id = item.getItemId();


        switch (id) {

            case R.id.nav_home: {
                activity.startActivity(new Intent().setClass(activity, HomeActivity.class));
                break;

            }

            case R.id.nav_mess: {
                activity.startActivity(new Intent().setClass(activity, MessActivity.class));
                break;

            }

            case R.id.nav_complaint: {
                activity.startActivity(new Intent().setClass(activity, ComplaintsActivity.class));
                break;

            }

            case R.id.nav_council: {

                activity.startActivity(new Intent().setClass(activity, CouncilActivity.class));
                break;

            }

            case R.id.nav_resource: {

                activity.startActivity(new Intent().setClass(activity, ResourceActivity.class));
                break;

            }


            case R.id.nav_profile: {
                activity.startActivity(new Intent().setClass(activity, ProfileActivity.class));
                break;

            }
            case R.id.nav_pass: {
                activity.startActivity(new Intent().setClass(activity, UpdatePassword.class));
                break;

            }
            case R.id.nav_logout: {
                PreferenceManager preferenceManager=new PreferenceManager(activity.getApplicationContext());
                preferenceManager.setIsLoggedIn(false);
                preferenceManager.setLoginCredentials("email","password");
                FirebaseAuth loginAuth=FirebaseAuth.getInstance();
                loginAuth.signOut();

                Toast.makeText(activity.getApplicationContext(), "User successfully logged out.", Toast.LENGTH_SHORT).show();
                activity.startActivity(new Intent().setClass(activity, LoginActivity.class));
                break;

            }

            case R.id.nav_settings: {
                activity.startActivity(new Intent().setClass(activity, SettingsActivity.class));
                break;

            }

            }

            DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }

    public static void setUser(Activity activity){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser!=null){
            NavigationView navigationView = activity.findViewById(R.id.nav_view);
            LinearLayout drawerHeader = (LinearLayout) navigationView.getHeaderView(0);

            TextView emailTextView = drawerHeader.findViewById(R.id.nav_header_email);
            TextView usernameTextView = drawerHeader.findViewById(R.id.nav_header_username);
            ImageView userImage=drawerHeader.findViewById(R.id.nav_header_imageView);

            emailTextView.setText(firebaseUser.getEmail());
            usernameTextView.setText(firebaseUser.getDisplayName());
            Glide.with(activity.getApplicationContext())
                    .load(firebaseUser.getPhotoUrl())
                    .into(userImage);
            Log.d("Nav_Image","Reached");

        };
    }


    }



