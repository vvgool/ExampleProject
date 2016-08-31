package org.project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.project.R;
import org.project.base.RecyclerAdapter;
import org.project.helper.ImageLoaderHelper;
import org.project.helper.PictureHelper;
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
        ImageLoader.getInstance().loadImage(url,
                new ImageSize(400, 400),
                ImageLoaderHelper.initImageLoaderImageOptions(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {
                        imageView.setImageResource(R.drawable.user_icon);
                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
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
