package com.example.contactsapp.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContactsListManager {
    private final String TAG = ContactsListManager.class.getName();

    public List<ContactDataBean> mGetContactsListData(ContentResolver contentResolver){
        List<ContactDataBean> list = new ArrayList<>();
        try {
            Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                        while (cursorInfo.moveToNext()) {
                            ContactDataBean info = new ContactDataBean(id,
                                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                                    cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                            list.add(info);
                        }

                        cursorInfo.close();
                    }
                }
                cursor.close();
            }
        } catch (Exception ex){
            Log.e(TAG,"Error : " +ex.getMessage());
        }
        return list;
    }
}
