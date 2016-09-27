package org.project.helper;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;

import org.project.base.App;
import org.project.oop.ContactOOP;

import java.util.List;

/**
 * Created by wiesen on 16-8-30.
 */
public class ContactHelper {
    private static final String[] CONTACT_SELECTION = {ContactsContract.CommonDataKinds.Phone._ID,
                                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                                ContactsContract.CommonDataKinds.Phone.NUMBER
                                                };

    public static boolean queryContacts(List<ContactOOP> contactOOPs){

        if (contactOOPs == null) return false;

        ContentResolver contentResolver = App.getInstance().getContentResolver();

        Cursor query = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                CONTACT_SELECTION, null,null,null);
        try {
            if (query != null) {
                while (query.moveToNext()) {
                    String phoneNumber = query.getString(query.getColumnIndex(CONTACT_SELECTION[2]));
                    if (TextUtils.isEmpty(phoneNumber)){
                        continue;
                    }
                    String hostName = query.getString(query.getColumnIndex(CONTACT_SELECTION[1]));
                    ContactOOP contactOOP = new ContactOOP(hostName,phoneNumber);
                    contactOOPs.add(contactOOP);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if (query != null){
                query.close();
                query = null;
            }
        }
        return true;
    }
}
