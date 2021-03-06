package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is to model proband's birthday.
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/1/18:23:18</br>
 */

public class Birthday implements Parcelable {

    private int year;
    private int month;
    private int day;

    /**
     * Constructor
     *
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     */
    public Birthday(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // ================= setters and getters =========================

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


    // =================== Parcelable =================================

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.year);
        dest.writeInt(this.month);
        dest.writeInt(this.day);
    }

    protected Birthday(Parcel in) {
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
    }

    public static final Parcelable.Creator<Birthday> CREATOR = new Parcelable.Creator<Birthday>() {
        @Override
        public Birthday createFromParcel(Parcel source) {
            return new Birthday(source);
        }

        @Override
        public Birthday[] newArray(int size) {
            return new Birthday[size];
        }
    };

    // ================================================================
}
