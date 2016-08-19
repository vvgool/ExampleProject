package org.project.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.project.R;
import org.project.adapter.NewsAdapter;
import org.project.base.App;
import org.project.base.BaseFragment;
import org.project.base.DividerItemDecoration;
import org.project.oop.NewsOop;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ljdy on 2016/8/16.
 */
public class NewsFragment extends BaseFragment {

    @BindView(R.id.rv_news)
    RecyclerView mRecyclerView;

    NewsAdapter mNewsAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mNewsAdapter = new NewsAdapter(getContext());
        initData();
        mRecyclerView.setAdapter(mNewsAdapter);
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL) );
    }


    private void initData() {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            NewsOop newsOop = new NewsOop();
            newsOop.mNewsTitle = "宋喆的妻子杨慧，关键人物很少发声，或许事情不是这么简单" + i;
            newsOop.mCommentCount = random.nextInt(100);
            newsOop.mSourceName = "大众娱乐";
            newsOop.mSourceIcon = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
            mNewsAdapter.addData(newsOop);
        }

    }


    @Override
    public String getTitle() {
        return App.getInstance().getString(R.string.news_fragment_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
