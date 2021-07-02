package com.example.contactsapp.model;

public class ContactDataBean {
    private String id, strName, strPhoneNo;

    public ContactDataBean(String id, String strName, String strPhoneNo){
        this.id = id;
        this.strName = strName;
        this.strPhoneNo = strPhoneNo;
    }

    public String getId() {
        return id;
    }

    public String getStrName() {
        return strName;
    }

    public String getStrPhoneNo() {
        return strPhoneNo;
    }
}
