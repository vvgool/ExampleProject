package org.project.activity;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.project.R;
import org.project.adapter.PhotoAdapter;
import org.project.base.BaseActivity;
import org.project.interf.ItemClickListener;
import org.project.oop.PictureOOP;

import butterknife.BindView;

public class PhotoActivity extends BaseActivity implements ItemClickListener {
    @BindView(R.id.tb_photo_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_photos)
    RecyclerView mPhotoRecyclerView;

    PhotoAdapter mPhotoAdapter;
    PictureOOP mPictureOOP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null){
            finish();
        }
        mPictureOOP = (PictureOOP) intent.getSerializableExtra("photos");
        if (mPictureOOP == null){
            finish();
        }
        mToolbar.setTitle(mPictureOOP.mParentFileName);
        mToolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mPhotoAdapter = new PhotoAdapter(this);
        mPhotoAdapter.addAllData(mPictureOOP.mPictureUrls);
        mPhotoRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        mPhotoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPhotoRecyclerView.setAdapter(mPhotoAdapter);

        mPhotoAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_photo;
    }

    @Override
    public void onItemClickListener(View view, int position) {
        Intent intent = new Intent(this, PictureBrowseActivity.class);
        intent.putExtra("photos_browse",mPictureOOP);
        intent.putExtra("current",position);
        startActivity(intent);
    }
}
