package com.example.apiservices;

import android.os.Parcel;
import android.os.Parcelable;

public class Teams implements Parcelable {
    private int id;
    private String nombre;
    private String tla;

    public Teams(int id, String nombre, String tla) {
        this.id = id;
        this.nombre = nombre;
        this.tla = tla;
    }

    protected Teams(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        tla = in.readString();
    }

    public static final Creator<Teams> CREATOR = new Creator<Teams>() {
        @Override
        public Teams createFromParcel(Parcel in) {
            return new Teams(in);
        }

        @Override
        public Teams[] newArray(int size) {
            return new Teams[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTla() {
        return tla;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(tla);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
