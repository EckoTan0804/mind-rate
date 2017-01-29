package com.example.mindrate.sensor;


import android.hardware.Sensor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:18:37
 */

public class TriggerEvent implements Parcelable {

    private List<Sensor> sensorList;

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.sensorList);
    }

    public TriggerEvent() {
    }

    protected TriggerEvent(Parcel in) {
        this.sensorList = new ArrayList<Sensor>();
        in.readList(this.sensorList, Sensor.class.getClassLoader());
    }

    public static final Parcelable.Creator<TriggerEvent> CREATOR = new Parcelable
            .Creator<TriggerEvent>() {
        @Override
        public TriggerEvent createFromParcel(Parcel source) {
            return new TriggerEvent(source);
        }

        @Override
        public TriggerEvent[] newArray(int size) {
            return new TriggerEvent[size];
        }
    };
}
