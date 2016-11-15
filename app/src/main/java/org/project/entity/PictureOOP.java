package org.project.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiesen on 16-8-22.
 */
public class PictureOOP implements Serializable{
   public String mParentFileName;
   public List<String> mPictureUrls;

    public PictureOOP() {
        mPictureUrls = new ArrayList<>();
    }
}
