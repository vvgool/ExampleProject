package org.project.helper;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.project.R;

import java.io.File;

/**
 * Created by wiesen on 16-8-25.
 */
public class ImageLoaderHelper {
    private static final String FILE = "file://";

    public static void loadImage(Context context, String url, ImageView view){
        if (isFile(url)){
            loadImageByFile(context,url,view,-1);
        }else {
            loadImageByUrl(context, url, view, -1);
        }
    }

    public static void loadImageByUrl(Context context, String url, ImageView view, int defaultImage){
        Glide.with(context)
                .load(url)
                .error(defaultImage == -1? R.drawable.user_icon:defaultImage)
                .centerCrop()
                .into(view);

    }

    public static void loadImageByFile(Context context,String filePath,ImageView view,int defaultImage){
        Glide.with(context)
                .load(new File(filePath.replace(FILE,"")))
                .error(defaultImage == -1? R.drawable.user_icon:defaultImage)
                .centerCrop()
                .into(view);
    }

    private static boolean isFile(String url){
        return !TextUtils.isEmpty(url)&&url.contains(FILE);
    }
}
