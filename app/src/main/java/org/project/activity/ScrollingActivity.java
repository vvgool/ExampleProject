package org.project.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.project.R;
import org.project.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ScrollingActivity extends BaseActivity {
    private static final String TAG = "Scrolling";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;

    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitle("Ok");
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mToolbarLayout.setBackgroundResource(R.drawable.backgroud);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i(TAG,"offset:"+verticalOffset);
                if (verticalOffset == 0){
                    mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
                }else {
                    mToolbar.setNavigationIcon(null);
                }
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scrolling;
    }

    @OnClick(R.id.fab)
    void onFabClickListener(){
        Snackbar.make(mFab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG,"press back bt");
    }
}
