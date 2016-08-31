package org.project.helper;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.project.R;

/**
 * Created by wiesen on 16-8-25.
 */
public class ImageLoaderHelper {

    public static DisplayImageOptions initImageLoaderImageOptions(){
        return new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.user_icon)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

    }
}
