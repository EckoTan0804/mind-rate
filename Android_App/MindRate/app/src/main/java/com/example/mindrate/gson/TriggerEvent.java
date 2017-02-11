package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:18:37
 */

public class TriggerEvent implements Parcelable {

    private String questionnaireID;
    private Date minTimeSpace;
    private Date time;
    private Date dateTime;
    private boolean triggeredWhenCalendarEventBegins;
    private boolean triggeredWhenCalendarEventEnds;
    private boolean triggeredWhenFacebookNotificationComes;
    private boolean triggeredWhenWhatsAppNotificationComes;
    private boolean linearAcceleration;
    private boolean gravity;
    private boolean rotation;
    private boolean airTemperature;
    private boolean airPressure;
    private boolean light;
    private boolean relativeHumidity;
    private boolean orientation;
    private boolean magneticField;
    private boolean proximity;



    private boolean accelerometer;
    private boolean gyroscope;



    private static final int TYPE_ACCELEROMETER = 0;
    private static final int  TYPE_AMBIENT_TEMPERATURE = 1;
    private static final int  TYPE_GRAVITY = 2;
    private static final int  TYPE_GYROSCOPE = 3;
    private static final int  TYPE_LIGHT = 4;
    private static final int  TYPE_LINEAR_ACCELERATION = 5;
    private static final int  TYPE_MAGNETIC_FIELD = 6;
    private static final int  TYPE_ORIENTATION = 7;
    private static final int  TYPE_PRESSURE = 8;
    private static final int  TYPE_PROXIMITY = 9;
    private static final int  TYPE_RELATIVE_HUMIDITY = 10;
    private static final int  TYPE_ROTATION_VECTOR = 11;
    private boolean[] sensorList;

    public TriggerEvent(String  questionnaireID){
        this.questionnaireID = questionnaireID;
        this.sensorList = new boolean[12];
        for(int i=0;i<sensorList.length;i++){
            this.sensorList[i]=false;
        }
        this.linearAcceleration =false;
        this.gravity = false;
        this.rotation= false;
        this.airTemperature= false;
        this.airPressure= false;
        this.light= false;
        this.relativeHumidity= false;
        this.orientation= false;
        this.magneticField= false;
        this.proximity= false;
        this.accelerometer=false;
        this.gyroscope =false;

    }

    public TriggerEvent(String questionnaireID, Date minTimeSpace, Date time, Date dateTime,
                        boolean triggeredWhenCalendarEventBegins, boolean
                                triggeredWhenCalendarEventEnds, boolean
                                triggeredWhenFacebookNotificationComes, boolean
                                triggeredWhenWhatsAppNotificationComes, boolean
                                linearAcceleration, boolean gravity, boolean rotation, boolean
                                airTemperature, boolean airPressure, boolean light, boolean
                                relativeHumidity, boolean magneticField, boolean orientation,
                        boolean proximity, boolean accelerometer, boolean gyroscope, boolean[]
                                sensorList) {
        this.questionnaireID = questionnaireID;
        this.minTimeSpace = minTimeSpace;
        this.time = time;
        this.dateTime = dateTime;
        this.triggeredWhenCalendarEventBegins = triggeredWhenCalendarEventBegins;
        this.triggeredWhenCalendarEventEnds = triggeredWhenCalendarEventEnds;
        this.triggeredWhenFacebookNotificationComes = triggeredWhenFacebookNotificationComes;
        this.triggeredWhenWhatsAppNotificationComes = triggeredWhenWhatsAppNotificationComes;
        this.linearAcceleration = linearAcceleration;
        this.gravity = gravity;
        this.rotation = rotation;
        this.airTemperature = airTemperature;
        this.airPressure = airPressure;
        this.light = light;
        this.relativeHumidity = relativeHumidity;
        this.magneticField = magneticField;
        this.orientation = orientation;
        this.proximity = proximity;
        this.accelerometer = accelerometer;
        this.gyroscope = gyroscope;
        this.sensorList = sensorList;
    }

    public String getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(String questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getMinTimeSpace() {
        return minTimeSpace;
    }

    public void setMinTimeSpace(Date minTimeSpace) {
        this.minTimeSpace = minTimeSpace;
    }

    public boolean isTriggeredWhenCalendarEventBegins() {
        return triggeredWhenCalendarEventBegins;
    }

    public void setTriggeredWhenCalendarEventBegins(boolean triggeredWhenCalendarEventBegins) {
        this.triggeredWhenCalendarEventBegins = triggeredWhenCalendarEventBegins;
    }

    public boolean isTriggeredWhenCalendarEventEnds() {
        return triggeredWhenCalendarEventEnds;
    }

    public void setTriggeredWhenCalendarEventEnds(boolean triggeredWhenCalendarEventEnds) {
        this.triggeredWhenCalendarEventEnds = triggeredWhenCalendarEventEnds;
    }

    public boolean isTriggeredWhenWhatsAppNotificationComes() {
        return triggeredWhenWhatsAppNotificationComes;
    }

    public void setTriggeredWhenWhatsAppNotificationComes(boolean triggeredWhenWhatsAppNotificationComes) {
        this.triggeredWhenWhatsAppNotificationComes = triggeredWhenWhatsAppNotificationComes;
    }

    public boolean isTriggeredWhenFacebookNotificationComes() {
        return triggeredWhenFacebookNotificationComes;
    }

    public void setTriggeredWhenFacebookNotificationComes(boolean triggeredWhenFacebookNotificationComes) {
        this.triggeredWhenFacebookNotificationComes = triggeredWhenFacebookNotificationComes;
    }

    public boolean isLinearAcceleration() {
        return linearAcceleration;
    }

    public void setLinearAcceleration(boolean linearAcceleration) {
        this.linearAcceleration = linearAcceleration;
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public boolean isRotation() {
        return rotation;
    }

    public void setRotation(boolean rotation) {
        this.rotation = rotation;
    }

    public boolean isAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(boolean airTemperature) {
        this.airTemperature = airTemperature;
    }

    public boolean isAirPressure() {
        return airPressure;
    }

    public void setAirPressure(boolean airPressure) {
        this.airPressure = airPressure;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public boolean isRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(boolean relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }
    public boolean isAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(boolean accelerometer) {
        this.accelerometer = accelerometer;
    }

    public boolean isGyroscope() {
        return gyroscope;
    }

    public void setGyroscope(boolean gyroscope) {
        this.gyroscope = gyroscope;
    }
    public boolean isOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public boolean isMagneticField() {
        return magneticField;
    }

    public void setMagneticField(boolean magneticField) {
        this.magneticField = magneticField;
    }

    public boolean isProximity() {
        return proximity;
    }



    public void setProximity(boolean proximity) {
        this.proximity = proximity;
    }

    public boolean[] getSensorList() {
        return sensorList;
    }
    public void setSensor(){
        this.sensorList[TYPE_ACCELEROMETER]=isAccelerometer();
        this.sensorList[TYPE_AMBIENT_TEMPERATURE]= isAirTemperature();
        this.sensorList[TYPE_GRAVITY]= isGravity();
        this.sensorList[TYPE_GYROSCOPE]=isGyroscope();
        this.sensorList[TYPE_LIGHT]= isLight();
        this.sensorList[TYPE_LINEAR_ACCELERATION ]=isLinearAcceleration();
        this.sensorList[TYPE_MAGNETIC_FIELD] = isMagneticField();
        this.sensorList[TYPE_ORIENTATION]=isOrientation();
        this.sensorList[TYPE_PRESSURE]=isAirPressure();
        this.sensorList[TYPE_PROXIMITY]=isProximity();
        this.sensorList[TYPE_RELATIVE_HUMIDITY]=isRelativeHumidity();
        this.sensorList[TYPE_ROTATION_VECTOR]=isRotation();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.questionnaireID);
        dest.writeLong(this.minTimeSpace != null ? this.minTimeSpace.getTime() : -1);
        dest.writeLong(this.time != null ? this.time.getTime() : -1);
        dest.writeLong(this.dateTime != null ? this.dateTime.getTime() : -1);
        dest.writeByte(this.triggeredWhenCalendarEventBegins ? (byte) 1 : (byte) 0);
        dest.writeByte(this.triggeredWhenCalendarEventEnds ? (byte) 1 : (byte) 0);
        dest.writeByte(this.triggeredWhenFacebookNotificationComes ? (byte) 1 : (byte) 0);
        dest.writeByte(this.triggeredWhenWhatsAppNotificationComes ? (byte) 1 : (byte) 0);
        dest.writeByte(this.linearAcceleration ? (byte) 1 : (byte) 0);
        dest.writeByte(this.gravity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.rotation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.airTemperature ? (byte) 1 : (byte) 0);
        dest.writeByte(this.airPressure ? (byte) 1 : (byte) 0);
        dest.writeByte(this.light ? (byte) 1 : (byte) 0);
        dest.writeByte(this.relativeHumidity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.orientation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.magneticField ? (byte) 1 : (byte) 0);
        dest.writeByte(this.proximity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.accelerometer ? (byte) 1 : (byte) 0);
        dest.writeByte(this.gyroscope ? (byte) 1 : (byte) 0);
        dest.writeBooleanArray(this.sensorList);
    }

    protected TriggerEvent(Parcel in) {
        this.questionnaireID = in.readString();
        long tmpMinTimeSpace = in.readLong();
        this.minTimeSpace = tmpMinTimeSpace == -1 ? null : new Date(tmpMinTimeSpace);
        long tmpTime = in.readLong();
        this.time = tmpTime == -1 ? null : new Date(tmpTime);
        long tmpDateTime = in.readLong();
        this.dateTime = tmpDateTime == -1 ? null : new Date(tmpDateTime);
        this.triggeredWhenCalendarEventBegins = in.readByte() != 0;
        this.triggeredWhenCalendarEventEnds = in.readByte() != 0;
        this.triggeredWhenFacebookNotificationComes = in.readByte() != 0;
        this.triggeredWhenWhatsAppNotificationComes = in.readByte() != 0;
        this.linearAcceleration = in.readByte() != 0;
        this.gravity = in.readByte() != 0;
        this.rotation = in.readByte() != 0;
        this.airTemperature = in.readByte() != 0;
        this.airPressure = in.readByte() != 0;
        this.light = in.readByte() != 0;
        this.relativeHumidity = in.readByte() != 0;
        this.orientation = in.readByte() != 0;
        this.magneticField = in.readByte() != 0;
        this.proximity = in.readByte() != 0;
        this.accelerometer = in.readByte() != 0;
        this.gyroscope = in.readByte() != 0;
        this.sensorList = in.createBooleanArray();
    }

    public static final Creator<TriggerEvent> CREATOR = new Creator<TriggerEvent>() {
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
