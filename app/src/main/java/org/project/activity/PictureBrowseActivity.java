package org.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.project.R;
import org.project.adapter.PictureBrowsePagerAdapter;
import org.project.base.BaseActivity;
import org.project.entity.PictureEntity;

import butterknife.BindView;

/**
 * Created by wiesen on 16-8-25.
 */
public class PictureBrowseActivity extends BaseActivity {
    @BindView(R.id.tb_picture_browse_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.vp_picture_show)
    ViewPager mViewPager;

    PictureBrowsePagerAdapter mPictureBrowsePagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null){
            finish();
        }
        PictureEntity pictureEntity = (PictureEntity) intent.getSerializableExtra("photos_browse");
        int currentItem = intent.getIntExtra("current",0);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setTitle(pictureEntity.mParentFileName);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mPictureBrowsePagerAdapter = new PictureBrowsePagerAdapter(this);
        mPictureBrowsePagerAdapter.addAllData(pictureEntity.mPictureUrls);
        mViewPager.setAdapter(mPictureBrowsePagerAdapter);
        mViewPager.setCurrentItem(currentItem);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_picture_browse;
    }
}
