package com.star.tablayout;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by star on 2017/7/4.
 */

public class CustomTabItemActivity extends AppCompatActivity {


    ViewPager mViewPager;

    ListFragment mFragment1;

    ListFragment mFragment2;

    PagerAdapter mPagerAdapter;

    private TabLayout mTabLayout;

    private ArrayList<String> titles = new ArrayList<>();

    private boolean isSelected = false;

    private int[] tabIcons = {
            R.mipmap.icon_down,
            R.mipmap.icon_down,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_tabitem_layout);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout = (TabLayout) findViewById(R.id.toolbar_tab);

        if (savedInstanceState == null) {
            mFragment1 = new ListFragment();
            mFragment1.initData('a', 'z');
            mFragment2 = new ListFragment();
            mFragment2.initData('A', 'Z');
        }
        titles.add("最新");
        titles.add("最热");

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabStatus(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabStatus(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void changeTabStatus(TabLayout.Tab tab, boolean selected) {
        View view = tab.getCustomView();
        final ImageView imgTitle = (ImageView) view.findViewById(R.id.img_title);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        imgTitle.setVisibility(View.VISIBLE);
        if (selected) {
            txtTitle.setTextColor(Color.parseColor("#0EA73C"));
            startPropertyAnim(imgTitle);
        } else {
            txtTitle.setTextColor(Color.parseColor("#7f7f7f"));
            imgTitle.setVisibility(View.INVISIBLE);
        }
    }

    public View getTabView(final int position) {
        final View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        final ImageView imgTitle = (ImageView) view.findViewById(R.id.img_title);
        imgTitle.setImageResource(tabIcons[position]);
        txtTitle.setText(titles.get(position));
        if (position == 0) {
            txtTitle.setTextColor(Color.parseColor("#057523"));
        } else {
            imgTitle.setVisibility(View.INVISIBLE);
            txtTitle.setTextColor(Color.parseColor("#ced0d3"));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPropertyAnim(imgTitle);
                mViewPager.setCurrentItem(position);
            }
        });
        return view;
    }

    private void startPropertyAnim(ImageView v) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", 180f, 360f);
        anim.setDuration(500);
        anim.start();
    }


    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return mFragment1;
            } else if (position == 1) {
                return mFragment2;
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}
