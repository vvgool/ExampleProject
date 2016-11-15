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
import org.project.entity.NewsOOP;
import org.project.module.news.NewsManager;
import org.project.net.config.CallBackResponse;
import org.project.net.news.NewsEntity;

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
        NewsManager.Companion.getNewsByType(NewsManager.Companion.getTOP(), new CallBackResponse<NewsEntity>() {
            @Override
            public void onSuccess(NewsEntity newsEntity) {
                mNewsAdapter.addAllData(newsEntity.getData());
            }

            @Override
            public void onFail(String msg) {

            }
        });

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
