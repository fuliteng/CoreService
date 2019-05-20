package com.sinohb.coreservice.bean.contact;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2018/4/11.
 */

public class ContactBean implements Parcelable {

    private String contactName;
    private String contactNumber;

    public ContactBean(String name, String number) {
        this.setName(name);
        this.setNumber(number);
    }


    protected ContactBean(Parcel in) {
        contactName = in.readString();
        contactNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contactName);
        dest.writeString(contactNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactBean> CREATOR = new Creator<ContactBean>() {
        @Override
        public ContactBean createFromParcel(Parcel in) {
            return new ContactBean(in);
        }

        @Override
        public ContactBean[] newArray(int size) {
            return new ContactBean[size];
        }
    };

    public String getNumber() {
        return contactNumber;
    }

    public void setNumber(String contact_number) {
        this.contactNumber = contact_number;
    }

    public String getName() {
        return contactName;
    }

    public void setName(String contact_name) {
        this.contactName = contact_name;
    }
}
