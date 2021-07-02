package com.example.contactsapp.model;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.contactsapp.presenter.AddContactPresenter;

import java.util.ArrayList;

public class AddContactManager {
    private final String TAG = AddContactManager.class.getName();
    private AddContactPresenter addContactPresenter;
    public AddContactManager(AddContactPresenter addContactPresenter){
        this.addContactPresenter = addContactPresenter;
    }
    public void insertNewContact(ContentResolver contentResolver, String strUsername, String strPhoneNumber){
        try {
            long lContactId = getRawContactId(contentResolver);
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
            // first and last names
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValue(ContactsContract.Data.RAW_CONTACT_ID, lContactId)
                    .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, strUsername)
                    .build());
            //phone number
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValue(ContactsContract.Data.RAW_CONTACT_ID, lContactId)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, strPhoneNumber).
                            withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
            contentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
            addContactPresenter.onSuccess(strUsername);
        }catch (Exception ex){
            Log.d(TAG,"Error on adding new contact : " +ex.getMessage());
            addContactPresenter.onFailure("Error on adding new contact : " +ex.getMessage());
        }
    }

    // This method is to get a system generated raw contact id.
    private long getRawContactId(ContentResolver contentResolver)
    {
        // Inser an empty contact.
        ContentValues contentValues = new ContentValues();
        Uri rawContactUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
        // Get the newly created contact raw id.
        long ret = ContentUris.parseId(rawContactUri);
        return ret;
    }
}
