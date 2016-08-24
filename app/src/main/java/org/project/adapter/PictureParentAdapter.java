package org.project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.project.R;
import org.project.base.RecyclerAdapter;
import org.project.oop.PictureOOP;
import org.project.weight.PictureImageView;

import java.io.File;

/**
 * Created by wiesen on 16-8-22.
 */
public class PictureParentAdapter extends RecyclerAdapter<PictureOOP> {
    private DisplayImageOptions.Builder mDisplayImageOptions;

    public PictureParentAdapter(Context context) {
        super(context);
        mDisplayImageOptions = new DisplayImageOptions.Builder()
        .showImageOnFail(R.drawable.user_icon)
        .cacheInMemory(true)
        .cacheOnDisk(true);
    }

    @Override
    public int getItemViewId() {
        return R.layout.recy_item_pic_parent_item;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        PictureOOP pictureOOP = getItemData(position);
        if (pictureOOP != null){
            if (pictureOOP.mPictureUrls != null && pictureOOP.mPictureUrls.size() >0){
                PictureImageView view = holder.getView(R.id.iv_pic_foot);
                File file = new File(pictureOOP.mPictureUrls.get(0));
                String url = "file://"+pictureOOP.mPictureUrls.get(0);
                ImageLoader.getInstance().displayImage(url,view,mDisplayImageOptions.build());
//                Bitmap bitmap = BitmapFactory.decodeFile(pictureOOP.mPictureUrls.get(0));
//                if (bitmap != null ) {
//                    view.setImageBitmap(bitmap);
//                }
            }
            if (!pictureOOP.mParentFileName.isEmpty()){
                ((TextView)holder.getView(R.id.tv_parent_name)).setText(pictureOOP.mParentFileName);
            }
        }
    }


}
