package org.project.helper;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.project.R;


/**
 * Created by wiesen on 16-8-25.
 */
public class ImageLoaderHelper {

    public static void loadImageByCenterCrop(Context context, String url, ImageView view) {
        loadImageByCenterCrop(context, url, view, 200, 200);
    }

    public static void loadImageByCenterCrop(Context context, String url, ImageView view, int width, int height) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.user_icon)
                .resize(width, height)
                .centerCrop()
                .into(view);
    }

    public static void loadImageByFit(Context context, String url, ImageView view) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.user_icon)
                .fit()
                .into(view);
    }

    public static void loadImageNormal(Context context, String url, ImageView view) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.user_icon)
                .into(view);
    }


}
