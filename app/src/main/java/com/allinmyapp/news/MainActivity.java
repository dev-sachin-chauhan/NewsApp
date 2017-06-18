package com.allinmyapp.news;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allinmyapp.news.Model.Constants;
import com.allinmyapp.news.Model.NewsEntity;
import com.allinmyapp.news.Model.NewsModel;
import com.allinmyapp.news.UI.NewsFragment;
import com.allinmyapp.news.UI.NewsMap;

import java.util.ArrayList;

import static com.allinmyapp.news.DetailNewsFragment.FragmentTAG;


public class MainActivity extends AppCompatActivity implements NewsFragment.OnListFragmentInteractionListener , DetailNewsFragment.OnFragmentInteractionListener, TabLayout.OnTabSelectedListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private NewsPagerAdapter mNewsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ArrayList<NewsMap> newsMap = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private AppBarLayout mAppBar;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppBar = (AppBarLayout) findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        newsMap.add(new NewsMap("Top Headlines",NewsFragment.newInstance(Constants.TOP_HEADLINE)));
        newsMap.add(new NewsMap("National",NewsFragment.newInstance(Constants.NATION)));
        newsMap.add(new NewsMap("World",NewsFragment.newInstance(Constants.WORLD_TOPICS)));
        newsMap.add(new NewsMap("Business",NewsFragment.newInstance(Constants.BUSINESS)));
        newsMap.add(new NewsMap("Technology",NewsFragment.newInstance(Constants.TECHNOLOGY)));
        newsMap.add(new NewsMap("Sports",NewsFragment.newInstance(Constants.SPORTS)));
        newsMap.add(new NewsMap("Politics",NewsFragment.newInstance(Constants.POLOTICS)));
        newsMap.add(new NewsMap("Entertainment",NewsFragment.newInstance(Constants.ENTERTAINMENT)));
        newsMap.add(new NewsMap("Health",NewsFragment.newInstance(Constants.HEALTH)));

        mNewsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), newsMap);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager.setAdapter(mNewsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mFragmentManager = getSupportFragmentManager();
    }

    private void setTabTextStyle(int mGotoTabPosition, int style) {
        LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(mGotoTabPosition);
        TextView tabTextView = (TextView) tabLayout.getChildAt(1);
        tabTextView.setTypeface(null, style);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(NewsEntity item) {
        DetailNewsFragment detailNewsFragment = DetailNewsFragment.newInstance(item.getSrcUrl());
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragPage, detailNewsFragment, FragmentTAG);
        transaction.addToBackStack(FragmentTAG);
        transaction.commit();
        mFragmentManager.executePendingTransactions();
    }

    @Override
    public void onBackPress() {
        super.onBackPressed();
        mAppBar.setExpanded(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAppBar.setExpanded(true);
    }

    @Override
    public void hideActionBar() {
        mAppBar.setExpanded(false);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabTextStyle(tab.getPosition(), Typeface.BOLD);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabTextStyle(tab.getPosition(), Typeface.NORMAL);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
