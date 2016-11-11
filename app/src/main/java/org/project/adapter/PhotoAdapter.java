package org.project.adapter;

import android.content.Context;
import android.view.View;


import org.project.R;
import org.project.base.RecyclerAdapter;
import org.project.helper.ImageLoaderHelper;
import org.project.interf.ItemClickListener;
import org.project.weight.GridImageView;

/**
 * Created by wiesen on 16-8-24.
 */
public class PhotoAdapter extends RecyclerAdapter<String> {


    private ItemClickListener mItemClickListener;

    public PhotoAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewId(int viewType) {
        return R.layout.item_photo;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        String itemData = getItemData(position);
        final GridImageView imageView = holder.getView(R.id.iv_photo);
        String url = "file://"+itemData;
        ImageLoaderHelper.loadImage(mContext,url,imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null){
                    mItemClickListener.onItemClickListener(imageView,position);
                }
            }
        });

    }

    public void setOnItemClickListener(ItemClickListener listener){
        mItemClickListener = listener;
    }
}
