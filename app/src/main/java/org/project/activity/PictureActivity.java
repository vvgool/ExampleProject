package org.project.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.project.R;
import org.project.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by ljdy on 2016/8/19.
 */
public class PictureActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitle(R.string.picture);
        mToolbar.setNavigationContentDescription(R.string.tool_back);
        mToolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_picture;
    }

}
