package com.allinmyapp.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.allinmyapp.news.Model.Constants;
import com.allinmyapp.news.Model.NewsEntity;
import com.allinmyapp.news.Model.NewsModel;
import com.allinmyapp.news.UI.NewsFragment;
import com.allinmyapp.news.UI.NewsMap;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NewsFragment.OnListFragmentInteractionListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        newsMap.add(new NewsMap("Top Headlines",NewsFragment.newInstance(Constants.TOP_HEADLINE)));
        newsMap.add(new NewsMap("Sports",NewsFragment.newInstance(Constants.SPORTS)));
        newsMap.add(new NewsMap("Politics",NewsFragment.newInstance(Constants.POLOTICS)));
        newsMap.add(new NewsMap("Nation",NewsFragment.newInstance(Constants.NATION)));
        newsMap.add(new NewsMap("Health",NewsFragment.newInstance(Constants.HEALTH)));
        newsMap.add(new NewsMap("Elections",NewsFragment.newInstance(Constants.ELECTIONS)));
        newsMap.add(new NewsMap("Entertainment",NewsFragment.newInstance(Constants.ENTERTAINMENT)));
        newsMap.add(new NewsMap("World",NewsFragment.newInstance(Constants.WORLD_TOPICS)));
        newsMap.add(new NewsMap("Business",NewsFragment.newInstance(Constants.BUSINESS)));
        newsMap.add(new NewsMap("Technology",NewsFragment.newInstance(Constants.TECHNOLOGY)));
        mNewsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), newsMap);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager.setAdapter(mNewsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(NewsEntity item) {

    }
}
