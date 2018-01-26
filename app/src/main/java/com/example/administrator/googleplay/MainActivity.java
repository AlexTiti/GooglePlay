package com.example.administrator.googleplay;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.pagerTitle)
    PagerTitleStrip pagerTitle;
    private FragAdapter mFragAdapter;
    private List<Fragment1> mListFragment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mFragAdapter = new FragAdapter(getSupportFragmentManager());
        Fragment1 fragmenta = Fragment1.getInstance("AAA");
        Fragment1 fragmentb = Fragment1.getInstance("BBB");
        Fragment1 fragmentc = Fragment1.getInstance("CCC");
        mListFragment.add(fragmenta);
        mListFragment.add(fragmentb);
        mListFragment.add(fragmentc);
        mFragAdapter.setmListFragment(mListFragment);
        viewpager.setAdapter(mFragAdapter);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                viewpager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        for (int i = 0; i < 3; i++) {
            actionBar.addTab(actionBar.newTab().setText("Tab" + (i + 1)).setTabListener(tabListener));
        }

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pagerTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DrawableActivity.class));
            }
        });

        pagerTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                return true;
            }
        });



    }










}
