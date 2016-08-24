package org.project.helper;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import org.project.base.App;
import org.project.oop.PictureOOP;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wiesen on 16-8-22.
 */
public class PictureHelper {

    public static boolean getSysPictures(ArrayList<PictureOOP> pictureOOPs){
        if (pictureOOPs != null){
            Map<String,ArrayList<String>> pictures = new HashMap<>();
            Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver mContentResolver = App.getInstance().getContentResolver();

            //只查询jpeg和png的图片
            Cursor cursor = mContentResolver.query(mImageUri, null,
                    MediaStore.Images.Media.MIME_TYPE + "=? or "
                            + MediaStore.Images.Media.MIME_TYPE + "=?",
                    new String[] { "image/jpeg", "image/png" }, MediaStore.Images.Media.DATE_MODIFIED);
                try {
                    while (cursor.moveToNext()){
                        String imgFileUrl = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        File imgFile = new File(imgFileUrl);
                        if (imgFile.exists()){
                            String imgParentFileName = imgFile.getParentFile().getName();
                            if (pictures.containsKey(imgParentFileName)) {
                                pictures.get(imgParentFileName).add(imgFileUrl);
                            }else {
                                ArrayList<String> urls = new ArrayList<>();
                                urls.add(imgFileUrl);
                                pictures.put(imgParentFileName,urls);
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }finally {
                    cursor.close();
                }

            for (String parentName : pictures.keySet()){
                PictureOOP pictureOOP = new PictureOOP();
                pictureOOP.mParentFileName = parentName;
                pictureOOP.mPictureUrls = pictures.get(parentName);
                pictureOOPs.add(pictureOOP);
            }
            pictures.clear();
            pictures = null;

        }
        return true;
    }
}
