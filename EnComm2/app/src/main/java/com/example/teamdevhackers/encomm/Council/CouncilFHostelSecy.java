package com.example.teamdevhackers.encomm.Council;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.example.teamdevhackers.encomm.R;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

import java.util.ArrayList;


public class CouncilFHostelSecy extends AppCompatActivity
        {

    private android.support.v7.app.ActionBar FHostelActionBar;
    private PagerAdapter adapter;
    private UltraViewPager.Orientation gravity_indicator;
    private ArrayList<CouncilUserClass> FHostelList;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.council_warden);

        UltraViewPager ultraViewPager = (UltraViewPager) findViewById(R.id.council_view_pager);
        FHostelList =new ArrayList<>();
        CouncilUserClass jrHostelSecy=new CouncilUserClass("S U Swakath","Jr Hostel Secretary","9629469172"," 180020036@iitdh.ac.in",R.drawable.swakath);

        FHostelList.add(jrHostelSecy);

        //main code starts here
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        adapter = new UltraPagerAdapter(true,this, FHostelList);
        ultraViewPager.setAdapter(adapter);

        ultraViewPager.setMultiScreen(0.8f);
        ultraViewPager.setItemRatio(1f);
        ultraViewPager.setRatio(0.75f);
        ultraViewPager.setAutoMeasureHeight(true);
        gravity_indicator = UltraViewPager.Orientation.HORIZONTAL;
        ultraViewPager.setPageTransformer(false, new UltraDepthScaleTransformer());



    }



    protected void onResume() {
        super.onResume();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        FHostelActionBar = getSupportActionBar();
        assert FHostelActionBar != null;
        FHostelActionBar.setHomeButtonEnabled(true);
        FHostelActionBar.setDisplayHomeAsUpEnabled(true);
        FHostelActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5cae80")));
        FHostelActionBar.setTitle(Html.fromHtml("<font color='#ffffff'>Council</font>"));
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;

    }


}
