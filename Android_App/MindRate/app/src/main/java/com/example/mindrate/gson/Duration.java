package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class aims to model a questionnaire's duration,
 * which means, how long does a questionnaire last.
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/3:12:33</br>
 * </p>
 */

public class Duration implements Parcelable {

    private int hour;
    private int minute;
    private int second;

    /**
     * Constructor
     * <p>e.g. If a questionnaire lasts 2 h 30 min and 30 sec
     * then hour = 2, minute = 30, second = 30</p>
     *
     * @param hour   how many hours lasts the questionnaire
     * @param minute how many minutes lasts the questionnaire on the basis of the hour
     * @param second how many seconds lasts the questionnaire on the basis of hour and minute
     */
    public Duration(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Duration) {
            Duration duration = (Duration) obj;
            return this.hour == duration.hour && this.minute == duration.minute && this.second ==
                    duration.second;
        } else {
            return false;
        }
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.hour);
        dest.writeInt(this.minute);
        dest.writeInt(this.second);
    }

    protected Duration(Parcel in) {
        this.hour = in.readInt();
        this.minute = in.readInt();
        this.second = in.readInt();
    }

    public static final Parcelable.Creator<Duration> CREATOR = new Parcelable.Creator<Duration>() {
        @Override
        public Duration createFromParcel(Parcel source) {
            return new Duration(source);
        }

        @Override
        public Duration[] newArray(int size) {
            return new Duration[size];
        }
    };
}
