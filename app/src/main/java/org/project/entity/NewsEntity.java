package org.project.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by wiesen on 2016/8/18.
 */
public class NewsEntity implements Serializable {
    public String mNewsTitle;
    public Bitmap mSourceIcon;
    public String mSourceName;
    public int mCommentCount;

}
