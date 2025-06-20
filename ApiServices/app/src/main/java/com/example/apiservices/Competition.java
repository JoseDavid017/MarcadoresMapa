package com.example.apiservices;

import android.os.Parcel;
import android.os.Parcelable;

public class Competition implements Parcelable {
    private String id;
    private String area;
    private String code;
    private String name;


    public Competition(String id, String area, String code, String name) {
        this.id = id;
        this.area = area;
        this.code = code;
        this.name = name;
    }

    protected Competition(Parcel in) {
        id = in.readString();
        area = in.readString();
        code = in.readString();
        name = in.readString();
    }

    public static final Creator<Competition> CREATOR = new Creator<Competition>() {
        @Override
        public Competition createFromParcel(Parcel in) {
            return new Competition(in);
        }

        @Override
        public Competition[] newArray(int size) {
            return new Competition[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(code);
        parcel.writeString(name);
    }
}
