package org.project.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;


import org.project.R;
import org.project.activity.PhotoActivity;
import org.project.base.RecyclerAdapter;
import org.project.helper.ImageLoaderHelper;
import org.project.interf.ItemClickListener;
import org.project.oop.PictureOOP;
import org.project.weight.PictureImageView;


/**
 * Created by wiesen on 16-8-22.
 */
public class PictureParentAdapter extends RecyclerAdapter<PictureOOP> {

    private ItemClickListener mItemClickListener;

    public PictureParentAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewId(int viewType) {
        return R.layout.recy_item_pic_parent_item;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final PictureOOP pictureOOP = getItemData(position);
        if (pictureOOP != null){
            if (pictureOOP.mPictureUrls != null && pictureOOP.mPictureUrls.size() >0){
                PictureImageView view = holder.getView(R.id.iv_pic_foot);
                String url = "file://"+pictureOOP.mPictureUrls.get(0);
                ImageLoaderHelper.loadImage(mContext,url,view);
//                Bitmap bitmap = BitmapFactory.decodeFile(pictureOOP.mPictureUrls.get(0));
//                if (bitmap != null ) {
//                    view.setImageBitmap(bitmap);
//                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PhotoActivity.class);
                        intent.putExtra("photos",pictureOOP);
                        mContext.startActivity(intent);
                        ((Activity)mContext).overridePendingTransition(R.anim.picture_anim
                                ,R.anim.picture_anim);
                    }
                });
            }
            if (!pictureOOP.mParentFileName.isEmpty()){
                ((TextView)holder.getView(R.id.tv_parent_name)).setText(pictureOOP.mParentFileName);
            }
        }
    }

}
