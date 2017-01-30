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

    private Questionnaire questionnaire;
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

    public TriggerEvent(Questionnaire qn){
        this.questionnaire = qn;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.questionnaire, flags);
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
    }

    protected TriggerEvent(Parcel in) {
        this.questionnaire = in.readParcelable(Questionnaire.class.getClassLoader());
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
    }

    public static final Parcelable.Creator<TriggerEvent> CREATOR = new Parcelable.Creator<TriggerEvent>() {
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
