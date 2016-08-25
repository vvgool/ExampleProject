package org.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.project.R;
import org.project.activity.PhotoActivity;
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
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED);
    }

    @Override
    public int getItemViewId() {
        return R.layout.recy_item_pic_parent_item;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final PictureOOP pictureOOP = getItemData(position);
        if (pictureOOP != null){
            if (pictureOOP.mPictureUrls != null && pictureOOP.mPictureUrls.size() >0){
                PictureImageView view = holder.getView(R.id.iv_pic_foot);
                String url = "file://"+pictureOOP.mPictureUrls.get(0);
                ImageLoader.getInstance().displayImage(url,view,mDisplayImageOptions.build());
//                Bitmap bitmap = BitmapFactory.decodeFile(pictureOOP.mPictureUrls.get(0));
//                if (bitmap != null ) {
//                    view.setImageBitmap(bitmap);
//                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.picture_anim);
                        v.startAnimation(animation);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Intent intent = new Intent(mContext, PhotoActivity.class);
                                intent.putExtra("photos",pictureOOP);
                                mContext.startActivity(intent);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }
                });
            }
            if (!pictureOOP.mParentFileName.isEmpty()){
                ((TextView)holder.getView(R.id.tv_parent_name)).setText(pictureOOP.mParentFileName);
            }
        }
    }



}
