package org.project.helper;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import org.project.base.App;
import org.project.entity.PictureEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wiesen on 16-8-22.
 */
public class PictureHelper {
    private static final int BITMAP_COMPRESS_SIZE = 100*1024;

    public static boolean getSysPictures(ArrayList<PictureEntity> pictureEntities){
        if (pictureEntities != null){
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
                PictureEntity pictureEntity = new PictureEntity();
                pictureEntity.mParentFileName = parentName;
                pictureEntity.mPictureUrls = pictures.get(parentName);
                pictureEntities.add(pictureEntity);
            }
            pictures.clear();
            pictures = null;

        }
        return true;
    }




    public static Bitmap clipBitmap(String filePath,int width,int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap clipBitmap=BitmapFactory.decodeFile(filePath,options);
        if (clipBitmap == null){
            return null;
        }
        options.inJustDecodeBounds = true;
        int btWidth = options.outWidth;
        int btHeight = options.outHeight;
        options.inJustDecodeBounds = false;
        int scaleSize = 1;
        if (btWidth > width && btHeight > height){
            if (btHeight > btWidth){
                scaleSize = (int)((float)btWidth/(float)width);
            }else {
                scaleSize = (int)((float)btHeight/(float)height);
            }
        }
        options.inSampleSize = scaleSize;
        clipBitmap = BitmapFactory.decodeFile(filePath,options);
        return pressBitmap(clipBitmap);
    }

    public static Bitmap pressBitmap(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int pressStride = 90;
        bitmap.compress(Bitmap.CompressFormat.PNG,pressStride,bos);
        while (bos.size() > BITMAP_COMPRESS_SIZE){
                pressStride -= 10;
            if (pressStride <0) {
                break;
            }
            bos.reset();
            bitmap.compress(Bitmap.CompressFormat.PNG,pressStride,bos);
        }
        return BitmapFactory.decodeByteArray(bos.toByteArray(),0,bos.toByteArray().length);
    }
}
