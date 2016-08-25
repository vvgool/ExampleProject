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
import org.project.helper.PictureHelper;
import org.project.weight.GridImageView;

/**
 * Created by wiesen on 16-8-24.
 */
public class PhotoAdapter extends RecyclerAdapter<String> {

    private final DisplayImageOptions.Builder mDisplayImageOptions;

    public PhotoAdapter(Context context) {
        super(context);
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.user_icon)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY);
    }

    @Override
    public int getItemViewId() {
        return R.layout.item_photo;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        String itemData = getItemData(position);
        final GridImageView imageView = holder.getView(R.id.iv_photo);
        String url = "file://"+itemData;
        ImageLoader.getInstance().loadImage(url,
                new ImageSize(400, 400),
                mDisplayImageOptions.build(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });

    }
}
