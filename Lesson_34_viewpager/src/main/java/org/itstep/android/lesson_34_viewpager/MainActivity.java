package org.itstep.android.lesson_34_viewpager;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.viewpagerindicator.CirclePageIndicator;

public class MainActivity extends ActionBarActivity implements View.OnClickListener,
        ActionBar.TabListener {

    private static final String[] URLS = new String[] {
            "http://www.joomlaworks.net/images/demos/galleries/abstract/7.jpg",
            "http://assets3.parliament.uk/iv/main-large//ImageVault/Images/id_7382/scope_0/ImageVaultHandler.aspx.jpg",
            "http://www.online-image-editor.com//styles/2014/images/example_image.png",
            "http://www.jpl.nasa.gov/spaceimages/images/mediumsize/PIA17011_ip.jpg",
            "http://www.joomlaworks.net/images/demos/galleries/abstract/7.jpg",
            "http://assets3.parliament.uk/iv/main-large//ImageVault/Images/id_7382/scope_0/ImageVaultHandler.aspx.jpg",
            "http://www.online-image-editor.com//styles/2014/images/example_image.png",
            "http://www.jpl.nasa.gov/spaceimages/images/mediumsize/PIA17011_ip.jpg",
            "http://www.joomlaworks.net/images/demos/galleries/abstract/7.jpg",
            "http://assets3.parliament.uk/iv/main-large//ImageVault/Images/id_7382/scope_0/ImageVaultHandler.aspx.jpg",
            "http://www.online-image-editor.com//styles/2014/images/example_image.png",
            "http://www.jpl.nasa.gov/spaceimages/images/mediumsize/PIA17011_ip.jpg"
    };
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.addTab(actionBar.newTab().setText("Tab 1").setTabListener(this));
//        actionBar.addTab(actionBar.newTab().setText("Tab 2").setTabListener(this));
//        actionBar.addTab(actionBar.newTab().setText("Tab 3").setTabListener(this));

        final FragmentPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), URLS);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset,
                                       final int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled: position: " + position
                        + ", positionOffset: " + positionOffset
                        + ", positionOffsetPixels: " + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(final int position) {
                Log.i(TAG, "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(final int state) {
                Log.i(TAG, "onPageScrollStateChanged: " + state);
            }
        });

        final CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.titles);
        indicator.setViewPager(mViewPager);
        indicator.setRadius(25);

        final PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);

        findViewById(R.id.previous).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.previous:
                final int prevPosition = mViewPager.getCurrentItem() - 1;
                mViewPager.setCurrentItem(prevPosition);
                break;
            case R.id.next:
                final int nextPosition = mViewPager.getCurrentItem() + 1;
                mViewPager.setCurrentItem(nextPosition);
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }

    @Override
    public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
        final int position = tab.getPosition();
        if (mViewPager != null) {
            mViewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {

    }
}