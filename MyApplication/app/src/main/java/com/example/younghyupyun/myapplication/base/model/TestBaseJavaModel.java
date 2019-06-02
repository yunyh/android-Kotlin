package com.example.younghyupyun.myapplication.base.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TestBaseJavaModel implements Parcelable {

    public static final Creator<TestBaseJavaModel> CREATOR = new Creator<TestBaseJavaModel>() {
        @Override
        public TestBaseJavaModel createFromParcel(Parcel in) {
            return new TestBaseJavaModel(in);
        }

        @Override
        public TestBaseJavaModel[] newArray(int size) {
            return new TestBaseJavaModel[size];
        }
    };
    private String dataName;

    public TestBaseJavaModel() {
        dataName = "asdfjsdif";
    }

    protected TestBaseJavaModel(Parcel in) {
        dataName = in.readString();
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataName);
    }
}
