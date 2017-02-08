package com.tik.httpdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.tik.httpdemo.base.BaseActivity;
import com.tik.httpdemo.fragment.HttpUrlConnFragment;
import com.tik.httpdemo.fragment.OkHttpFragment;
import com.tik.httpdemo.fragment.RetrofitFragment;
import com.tik.httpdemo.fragment.RxOkHttpFragment;
import com.tik.httpdemo.fragment.RxRetrofitFragment;
import com.tik.httpdemo.fragment.VolleyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected void beforeBindViews() {

    }

    @Override
    protected void afterBindViews() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HttpUrlConnFragment());
        fragments.add(new VolleyFragment());
        fragments.add(new OkHttpFragment());
        fragments.add(new RetrofitFragment());
        fragments.add(new RxRetrofitFragment());
        fragments.add(new RxOkHttpFragment());
        String[] titles = {
                "httpurlconn", "volley", "okhttp", "retrofit", "rxretrofit", "rxokhttp"
        };
        final MyStatePagerAdapter adapter = new MyStatePagerAdapter(getSupportFragmentManager(), fragments, titles);
//        final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        setTitle(titles[0]);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(adapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * Difference between FragmentPagerAdapter and FragmentStatePagerAdapter
     * Like the docs say, think about it this way.
     * If you were to do an application like a book reader,
     * you will not want to load all the fragments into memory at once.
     * You would like to load and destroy Fragments as the user reads.
     * In this case you will use FragmentStatePagerAdapter.
     * If you are just displaying 3 "tabs" that do not contain a lot of heavy data (like Bitmaps),
     * then FragmentPagerAdapter might suit you well.
     * Also, keep in mind that ViewPager by default will load 3 fragments into memory.
     * The first Adapter you mention might destroy View hierarchy and re load it when needed,
     * the second Adapter only saves the state of the Fragment and completely destroys it,
     * if the user then comes back to that page, the state is retrieved.
     */
    class MyStatePagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragments;

        private String[] mTitles;

        public MyStatePagerAdapter(FragmentManager fm, List<Fragment> mFragments, String[] mTitles) {
            super(fm);
            this.mFragments = mFragments;
            this.mTitles = mTitles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> mFragments;
        private String[] mTitles;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> mFragments, String[] mTitles) {
            super(fm);
            this.mFragments = mFragments;
            this.mTitles = mTitles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
