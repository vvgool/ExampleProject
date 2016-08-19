package org.project.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wiesen on 2016/8/15.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mUnBinder=ButterKnife.bind(this);
    }

    protected abstract int getContentViewId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null){
            mUnBinder.unbind();
        }
    }
}
