package org.project.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.project.R;
import org.project.adapter.PictureParentAdapter;
import org.project.base.BaseActivity;
import org.project.helper.PictureHelper;
import org.project.oop.PictureOOP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wiesen on 2016/8/19.
 */
public class PictureActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_pictures)
    RecyclerView mPictureParent;
    private PictureParentAdapter mPictureAdapter;

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

        mPictureParent.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        mPictureParent.setItemAnimator(new DefaultItemAnimator());

        mPictureAdapter = new PictureParentAdapter(this);

        mPictureParent.setAdapter(mPictureAdapter);
        initPictures();

//        mPictureParent.addOnScrollListener(new RecycleScrollListener());

    }

    private void initPictures(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<PictureOOP> pictureOOPs = new ArrayList<>();
                PictureHelper.getSysPictures(pictureOOPs);
                Message message = mHandler.obtainMessage();
                message.what = 1;
                message.obj  = pictureOOPs;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mPictureAdapter.addAllData((List<PictureOOP>) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.content_picture;
    }


    private  class RecycleScrollListener extends RecyclerView.OnScrollListener{

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState){
                case RecyclerView.SCROLL_STATE_IDLE: // The RecyclerView is not currently scrolling.
                    //对于滚动不加载图片的尝试
                    mPictureAdapter.notifyDataSetChanged();
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING: // The RecyclerView is currently being dragged by outside input such as user touch input.
//                    mPictureAdapter.isInterrupt(true);
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING: // The RecyclerView is currently animating to a final position while not under
//                    mPictureAdapter.isInterrupt(false);
                    break;
            }
        }
    }
}
