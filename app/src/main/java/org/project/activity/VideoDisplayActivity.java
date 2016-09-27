package org.project.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.project.R;
import org.project.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by wiesen on 16-9-12.
 */
public class VideoDisplayActivity extends BaseActivity {
    @BindView(R.id.tb_video_display)
    Toolbar mToolbar;
    @BindView(R.id.rv_videos)
    RecyclerView mVideoDisplays;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setTitle(R.string.videos);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activiy_video_display;
    }
}
