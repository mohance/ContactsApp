package com.example.contactsapp.presenter;

import android.content.ContentResolver;
import android.content.Context;
import android.view.View;

import com.example.contactsapp.model.AddContactManager;
import com.example.contactsapp.model.ContactDataBean;

import java.util.List;


public class AddContactPresenter {
    private final String TAG = AddContactPresenter.class.getName();
    private View view;
    private Context context;
    private AddContactManager addContactManager;
    public AddContactPresenter(View view, Context context){
        this.view = view;
        this.context = context;
        addContactManager = new AddContactManager(this);
    }
    public void insertNewContact(String strUsername, String strPhoneNumber){
        ContentResolver contentResolver = context.getContentResolver();
        addContactManager.insertNewContact(contentResolver,strUsername,strPhoneNumber);
    }

    public void onSuccess(String strUsername){
        view.onSuccess(strUsername);
    }
    public void onFailure(String strErrorMsg){
        view.onFailure(strErrorMsg);
    }

    public interface View{
        void onSuccess(String strUsername);
        void onFailure(String strErrorMsg);
    }
}
