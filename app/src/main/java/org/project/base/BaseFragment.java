package org.project.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wiesen on 2016/8/16.
 */
public abstract class BaseFragment extends Fragment {
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewId(),null);
        mUnbinder = ButterKnife.bind(this,view);
        initSaveInstanceState(savedInstanceState);
        return view;
    }

    protected abstract int getContentViewId();

    protected void initSaveInstanceState(Bundle savedInstanceState){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    public String getTitle(){
        return null;
    }
}
