package org.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.project.R;
import org.project.adapter.ViewPagerAdapter;
import org.project.base.BaseActivity;
import org.project.fragment.DynamicFragment;
import org.project.fragment.MovieFragment;
import org.project.fragment.MusicFragment;
import org.project.fragment.NewsFragment;
import org.project.fragment.NoteFragment;
import org.project.fragment.RecommendFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class NavigationActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    ActionBarDrawerToggle mToggle;

    @BindView(R.id.tl_navigation)
    TabLayout mTabLayout;

    @BindView(R.id.vp_fragment)
    ViewPager mViewPager;

    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolBar);


        mToggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        initViewPager();
    }

    private void initViewPager(){
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new NewsFragment());
        mViewPagerAdapter.addFragment(new RecommendFragment());
        mViewPagerAdapter.addFragment(new MusicFragment());
        mViewPagerAdapter.addFragment(new MovieFragment());
        mViewPagerAdapter.addFragment(new DynamicFragment());
        mViewPagerAdapter.addFragment(new NoteFragment());

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_navigation;
    }

    @OnClick(R.id.fab)
    void OnFabClickListener() {
        Snackbar.make(mFab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                startActivity(new Intent(this,PictureActivity.class));
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_friends_list:
                startActivity(new Intent(this,ContactActivity.class));
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                startActivity(new Intent(this,ScrollingActivity.class));
                break;
            case  R.id.nav_send:
                startActivity(new Intent(this,FeedBackActivity.class));
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDrawer != null && mToggle != null) {
            mDrawer.removeDrawerListener(mToggle);
        }
    }

}
