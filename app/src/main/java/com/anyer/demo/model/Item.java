package com.anyer.demo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anyer on 8/17/15.
 */
public class Item implements Parcelable {
    private String mText;
    private boolean mIsDone;

    public Item(String text) {
        mText = text;
        mIsDone = false;
    }

    public String getText() {
        return mText;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setIsDone(boolean mIsDone) {
        this.mIsDone = mIsDone;
    }


    /*
    * Parcelable Implementation
    * */
    protected Item(Parcel in) {
        mText = in.readString();
        mIsDone = in.readByte() != 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mText);
        dest.writeByte((byte) (mIsDone ? 1 : 0));
    }
}
