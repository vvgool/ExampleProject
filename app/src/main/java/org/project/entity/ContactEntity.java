package org.project.entity;

import java.io.Serializable;

/**
 * Created by wiesen on 16-8-29.
 */
public class ContactEntity implements Serializable {
    public String mHostName;
    public String mNumber;

    public ContactEntity(String mHostName, String mNumber) {
        this.mHostName = mHostName;
        this.mNumber = mNumber;
    }
}
