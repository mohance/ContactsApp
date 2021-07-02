package com.example.contactsapp.presenter;


import android.content.ContentResolver;
import android.content.Context;

import com.example.contactsapp.model.ContactDataBean;
import com.example.contactsapp.model.ContactsListManager;

import java.util.List;

public class ContactsListPresenter {
    private final String TAG = ContactsListPresenter.class.getName();
    private View view;
    private Context context;
    private ContactsListManager contactsListManager;
    public ContactsListPresenter(View view, Context context){
        this.view = view;
        this.context = context;
        contactsListManager = new ContactsListManager();
    }
    public void mGetContactsListData(){
        view.showProgressBar();
        ContentResolver contentResolver = context.getContentResolver();
        view.updateContactsListData(contactsListManager.mGetContactsListData(contentResolver));
        view.hideProgressBar();
    }

    public interface View{
        void updateContactsListData(List<ContactDataBean> list);
        void showProgressBar();
        void hideProgressBar();

    }
}
