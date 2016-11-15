package org.project.entity;

import java.io.Serializable;

/**
 * Created by wiesen on 16-8-29.
 */
public class ContactOOP implements Serializable {
    public String mHostName;
    public String mNumber;

    public ContactOOP(String mHostName, String mNumber) {
        this.mHostName = mHostName;
        this.mNumber = mNumber;
    }
}
