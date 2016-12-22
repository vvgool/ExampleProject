package org.project.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiesen on 16-8-22.
 */
public class PictureEntity implements Serializable{
   public String mParentFileName;
   public List<String> mPictureUrls;

    public PictureEntity() {
        mPictureUrls = new ArrayList<>();
    }
}
