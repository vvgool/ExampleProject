package org.project.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import org.project.helper.ImageLoaderHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wiesen on 16-8-25.
 */
public class PictureBrowsePagerAdapter extends PagerAdapter {
    private Map<String,ImageView> mViewCollection = new HashMap<>();
    private List<String> mImageUrls = new ArrayList<>();
    private Context mContext;

    public PictureBrowsePagerAdapter(Context context) {
        mContext = context;
    }

    public void addAllData(List<String> data){
        mImageUrls.clear();
        mImageUrls.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(String url){
        mImageUrls.add(url);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImageUrls.size();
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = mImageUrls.get(position);
        ImageView imageView;
        if (mViewCollection.containsKey(url)){
            imageView = mViewCollection.get(url);
        }else {
            imageView = new ImageView(mContext);
            ImageLoaderHelper.loadImage(mContext,"file://"+url,imageView);
            mViewCollection.put(url,imageView);
        }
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


}
