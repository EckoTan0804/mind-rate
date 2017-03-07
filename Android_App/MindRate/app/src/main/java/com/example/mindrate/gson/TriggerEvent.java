package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:18:37
 */

public class TriggerEvent implements Parcelable {

    private static final String TAG = "TriggerEvent";
    //    private Date minTimeSpace;
    //    private Date time;
    //    private Date dateTime;
    private String questionnaireID;


    private int minTimeSpace;
    @SerializedName("time")
    private String time;


    private Date dateTime;
    private boolean triggeredWhenCalendarEventBegins;
    private boolean triggeredWhenCalendarEventEnds;
    private boolean triggeredWhenFacebookNotificationComes;
    private boolean triggeredWhenWhatsAppNotificationComes;
    //==================Sensor==================================
    private boolean linearAcceleration;
    private boolean gravity;
    private boolean rotation;
    private boolean ambientTemperature;
    private boolean pressure;
    private boolean light;
    private boolean relativeHumidity;
    private boolean orientation;
    private boolean magneticField;
    private boolean proximity;
    private boolean accelerometer;
    private boolean gyroscope;
    //===========================================================

    //=============================Index of Sensor=========================
    private static final int TYPE_ACCELEROMETER = 0;
    private static final int TYPE_AMBIENT_TEMPERATURE = 1;
    private static final int TYPE_GRAVITY = 2;
    private static final int TYPE_GYROSCOPE = 3;
    private static final int TYPE_LIGHT = 4;
    private static final int TYPE_LINEAR_ACCELERATION = 5;
    private static final int TYPE_MAGNETIC_FIELD = 6;
    private static final int TYPE_ORIENTATION = 7;
    private static final int TYPE_PRESSURE = 8;
    private static final int TYPE_PROXIMITY = 9;
    private static final int TYPE_RELATIVE_HUMIDITY = 10;
    private static final int TYPE_ROTATION_VECTOR = 11;


    //========================================================
    //=========Max and Min-Value of Sensor to trigger Questionnaire=====
    private float accelerometerMinXValue;
    private float accelerometerMinYValue;
    private float accelerometerMinZValue;

    private float accelerometerMaxXValue;
    private float accelerometerMaxYValue;
    private float accelerometerMaxZValue;
    //=================================================
    private float ambientTemperatureMinValue;
    private float ambientTemperatureMaxValue;
    //===============================================
    private float gravityMinXValue;
    private float gravityMinYValue;
    private float gravityMinZValue;

    private float gravityMaxXValue;
    private float gravityMaxYValue;
    private float gravityMaxZValue;
    //============================================
    private float gyroscopeMinXValue;
    private float gyroscopeMinYValue;
    private float gyroscopeMinZValue;

    private float gyroscopeMaxXValue;
    private float gyroscopeMaxYValue;
    private float gyroscopeMaxZValue;
    //============================================
    private float lightMinValue;
    private float lightMaxValue;
    //================================================
    private float linearAccelerationMinXValue;
    private float linearAccelerationMinYValue;
    private float linearAccelerationMinZValue;

    private float linearAccelerationMaxXValue;
    private float linearAccelerationMaxYValue;
    private float linearAccelerationMaxZValue;
    //================================================
    private float magneticFieldMinXValue;
    private float magneticFieldMinYValue;
    private float magneticFieldMinZValue;

    private float magneticFieldMaxXValue;
    private float magneticFieldMaxYValue;
    private float magneticFieldMaxZValue;
    //===============================================
    private float orientationMinXValue;
    private float orientationMinYValue;
    private float orientationMinZValue;

    // noch nicht fertig!
    private float orientationMaxXValue;
    private float orientationMaxYValue;
    private float orientationMaxZValue;
    //===============================================
    private float pressureMinValue;
    private float pressureMaxValue;
    //=================================================
    private float proximityMinValue;
    private float proximityMaxValue;
    //================================================
    private float relativeHumidityMinValue;
    private float relativeHumidityMaxValue;
    //=====================================================
    private float rotationVectorMinXValue;
    private float rotationVectorMinYValue;
    private float rotationVectorMinZValue;

    private float rotationVectorMaxXValue;
    private float rotationVectorMaxYValue;
    private float rotationVectorMaxZValue;
    //======================================================

    //==================================================================
    private boolean[] sensorList;


    public TriggerEvent(String questionnaireID) {
        this.questionnaireID = questionnaireID;
        this.sensorList = new boolean[12];

        // for (int i = 0; i < sensorList.length; i++) {
        // Log.i(TAG,String.valueOf(this.sensorList[i]));
        //}

    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getMinTimeSpace() {
        return minTimeSpace;
    }

    public void setMinTimeSpace(int minTimeSpace) {
        this.minTimeSpace = minTimeSpace;
    }

    public String getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(String questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    //    public Date getTime() {
    //        return time;
    //    }
    //
    //    public void setTime(Date time) {
    //        this.time = time;
    //    }
    //
    //    public Date getDateTime() {
    //        return dateTime;
    //    }
    //
    //    public void setDateTime(Date dateTime) {
    //        this.dateTime = dateTime;
    //    }
    //
    //    public Date getMinTimeSpace() {
    //        return minTimeSpace;
    //    }
    //
    //    public void setMinTimeSpace(Date minTimeSpace) {
    //        this.minTimeSpace = minTimeSpace;
    //    }

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

    public void setTriggeredWhenWhatsAppNotificationComes(
            boolean triggeredWhenWhatsAppNotificationComes) {
        this.triggeredWhenWhatsAppNotificationComes = triggeredWhenWhatsAppNotificationComes;
    }

    public boolean isTriggeredWhenFacebookNotificationComes() {
        return triggeredWhenFacebookNotificationComes;
    }

    public void setTriggeredWhenFacebookNotificationComes(
            boolean triggeredWhenFacebookNotificationComes) {
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

    public boolean isAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAirTemperature(boolean airTemperature) {
        this.ambientTemperature = airTemperature;
    }

    public boolean isPressure() {
        return pressure;
    }

    public void setPressure(boolean pressure) {
        this.pressure = pressure;
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

    //======================================================================
    //=============getter and setter for triggerValue of Sensor==========
    public float getAccelerometerMinXValue() {
        return accelerometerMinXValue;
    }

    public void setAccelerometerMinXValue(float accelerometerMinXValue) {
        this.accelerometerMinXValue = accelerometerMinXValue;
    }

    public float getAccelerometerMinYValue() {
        return accelerometerMinYValue;
    }

    public void setAccelerometerMinYValue(float accelerometerMinYValue) {
        this.accelerometerMinYValue = accelerometerMinYValue;
    }

    public float getAccelerometerMinZValue() {
        return accelerometerMinZValue;
    }

    public void setAccelerometerMinZValue(float accelerometerMinZValue) {
        this.accelerometerMinZValue = accelerometerMinZValue;
    }

    public float getAccelerometerMaxXValue() {
        return accelerometerMaxXValue;
    }

    public void setAccelerometerMaxXValue(float accelerometerMaxXValue) {
        this.accelerometerMaxXValue = accelerometerMaxXValue;
    }

    public float getAccelerometerMaxYValue() {
        return accelerometerMaxYValue;
    }

    public void setAccelerometerMaxYValue(float accelerometerMaxYValue) {
        this.accelerometerMaxYValue = accelerometerMaxYValue;
    }

    public float getAccelerometerMaxZValue() {
        return accelerometerMaxZValue;
    }

    public void setAccelerometerMaxZValue(float accelerometerMaxZValue) {
        this.accelerometerMaxZValue = accelerometerMaxZValue;
    }

    public float getAmbientTemperatureMinValue() {
        return ambientTemperatureMinValue;
    }

    public void setAmbientTemperatureMinValue(float ambientTemperatureMinValue) {
        this.ambientTemperatureMinValue = ambientTemperatureMinValue;
    }

    public float getAmbientTemperatureMaxValue() {
        return ambientTemperatureMaxValue;
    }

    public void setAmbientTemperatureMaxValue(float ambientTemperatureMaxValue) {
        this.ambientTemperatureMaxValue = ambientTemperatureMaxValue;
    }

    public float getGravityMinXValue() {
        return gravityMinXValue;
    }

    public void setGravityMinXValue(float gravityMinXValue) {
        this.gravityMinXValue = gravityMinXValue;
    }

    public float getGravityMinYValue() {
        return gravityMinYValue;
    }

    public void setGravityMinYValue(float gravityMinYValue) {
        this.gravityMinYValue = gravityMinYValue;
    }

    public float getGravityMinZValue() {
        return gravityMinZValue;
    }

    public void setGravityMinZValue(float gravityMinZValue) {
        this.gravityMinZValue = gravityMinZValue;
    }

    public float getGravityMaxXValue() {
        return gravityMaxXValue;
    }

    public void setGravityMaxXValue(float gravityMaxXValue) {
        this.gravityMaxXValue = gravityMaxXValue;
    }

    public float getGravityMaxYValue() {
        return gravityMaxYValue;
    }

    public void setGravityMaxYValue(float gravityMaxYValue) {
        this.gravityMaxYValue = gravityMaxYValue;
    }

    public float getGravityMaxZValue() {
        return gravityMaxZValue;
    }

    public void setGravityMaxZValue(float gravityMaxZValue) {
        this.gravityMaxZValue = gravityMaxZValue;
    }

    public float getGyroscopeMinXValue() {
        return gyroscopeMinXValue;
    }

    public void setGyroscopeMinXValue(float gyroscopeMinXValue) {
        this.gyroscopeMinXValue = gyroscopeMinXValue;
    }

    public float getGyroscopeMinYValue() {
        return gyroscopeMinYValue;
    }

    public void setGyroscopeMinYValue(float gyroscopeMinYValue) {
        this.gyroscopeMinYValue = gyroscopeMinYValue;
    }

    public float getGyroscopeMinZValue() {
        return gyroscopeMinZValue;
    }

    public void setGyroscopeMinZValue(float gyroscopeMinZValue) {
        this.gyroscopeMinZValue = gyroscopeMinZValue;
    }

    public float getGyroscopeMaxXValue() {
        return gyroscopeMaxXValue;
    }

    public void setGyroscopeMaxXValue(float gyroscopeMaxXValue) {
        this.gyroscopeMaxXValue = gyroscopeMaxXValue;
    }

    public float getGyroscopeMaxYValue() {
        return gyroscopeMaxYValue;
    }

    public void setGyroscopeMaxYValue(float gyroscopeMaxYValue) {
        this.gyroscopeMaxYValue = gyroscopeMaxYValue;
    }

    public float getGyroscopeMaxZValue() {
        return gyroscopeMaxZValue;
    }

    public void setGyroscopeMaxZValue(float gyroscopeMaxZValue) {
        this.gyroscopeMaxZValue = gyroscopeMaxZValue;
    }

    public float getLightMinValue() {
        return lightMinValue;
    }

    public void setLightMinValue(float lightMinValue) {
        this.lightMinValue = lightMinValue;
    }

    public float getLightMaxValue() {
        return lightMaxValue;
    }

    public void setLightMaxValue(float lightMaxValue) {
        this.lightMaxValue = lightMaxValue;
    }

    public float getLinearAccelerationMinXValue() {
        return linearAccelerationMinXValue;
    }

    public void setLinearAccelerationMinXValue(float linearAccelerationMinXValue) {
        this.linearAccelerationMinXValue = linearAccelerationMinXValue;
    }

    public float getLinearAccelerationMinYValue() {
        return linearAccelerationMinYValue;
    }

    public void setLinearAccelerationMinYValue(float linearAccelerationMinYValue) {
        this.linearAccelerationMinYValue = linearAccelerationMinYValue;
    }

    public float getLinearAccelerationMinZValue() {
        return linearAccelerationMinZValue;
    }

    public void setLinearAccelerationMinZValue(float linearAccelerationMinZValue) {
        this.linearAccelerationMinZValue = linearAccelerationMinZValue;
    }

    public float getLinearAccelerationMaxXValue() {
        return linearAccelerationMaxXValue;
    }

    public void setLinearAccelerationMaxXValue(float linearAccelerationMaxXValue) {
        this.linearAccelerationMaxXValue = linearAccelerationMaxXValue;
    }

    public float getLinearAccelerationMaxYValue() {
        return linearAccelerationMaxYValue;
    }

    public void setLinearAccelerationMaxYValue(float linearAccelerationMaxYValue) {
        this.linearAccelerationMaxYValue = linearAccelerationMaxYValue;
    }

    public float getLinearAccelerationMaxZValue() {
        return linearAccelerationMaxZValue;
    }

    public void setLinearAccelerationMaxZValue(float linearAccelerationMaxZValue) {
        this.linearAccelerationMaxZValue = linearAccelerationMaxZValue;
    }

    public float getMagneticFieldMinXValue() {
        return magneticFieldMinXValue;
    }

    public void setMagneticFieldMinXValue(float magneticFieldMinXValue) {
        this.magneticFieldMinXValue = magneticFieldMinXValue;
    }

    public float getMagneticFieldMinYValue() {
        return magneticFieldMinYValue;
    }

    public void setMagneticFieldMinYValue(float magneticFieldMinYValue) {
        this.magneticFieldMinYValue = magneticFieldMinYValue;
    }

    public float getMagneticFieldMinZValue() {
        return magneticFieldMinZValue;
    }

    public void setMagneticFieldMinZValue(float magneticFieldMinZValue) {
        this.magneticFieldMinZValue = magneticFieldMinZValue;
    }

    public float getMagneticFieldMaxXValue() {
        return magneticFieldMaxXValue;
    }

    public void setMagneticFieldMaxXValue(float magneticFieldMaxXValue) {
        this.magneticFieldMaxXValue = magneticFieldMaxXValue;
    }

    public float getMagneticFieldMaxYValue() {
        return magneticFieldMaxYValue;
    }

    public void setMagneticFieldMaxYValue(float magneticFieldMaxYValue) {
        this.magneticFieldMaxYValue = magneticFieldMaxYValue;
    }

    public float getMagneticFieldMaxZValue() {
        return magneticFieldMaxZValue;
    }

    public void setMagneticFieldMaxZValue(float magneticFieldMaxZValue) {
        this.magneticFieldMaxZValue = magneticFieldMaxZValue;
    }

    public float getOrientationMinXValue() {
        return orientationMinXValue;
    }

    public void setOrientationMinXValue(float orientationMinXValue) {
        this.orientationMinXValue = orientationMinXValue;
    }

    public float getOrientationMinYValue() {
        return orientationMinYValue;
    }

    public void setOrientationMinYValue(float orientationMinYValue) {
        this.orientationMinYValue = orientationMinYValue;
    }

    public float getOrientationMinZValue() {
        return orientationMinZValue;
    }

    public void setOrientationMinZValue(float orientationMinZValue) {
        this.orientationMinZValue = orientationMinZValue;
    }

    public float getOrientationMaxXValue() {
        return orientationMaxXValue;
    }

    public void setOrientationMaxXValue(float orientationMaxXValue) {
        this.orientationMaxXValue = orientationMaxXValue;
    }

    public float getOrientationMaxYValue() {
        return orientationMaxYValue;
    }

    public void setOrientationMaxYValue(float orientationMaxYValue) {
        this.orientationMaxYValue = orientationMaxYValue;
    }

    public float getOrientationMaxZValue() {
        return orientationMaxZValue;
    }

    public void setOrientationMaxZValue(float orientationMaxZValue) {
        this.orientationMaxZValue = orientationMaxZValue;
    }

    public float getPressureMinValue() {
        return pressureMinValue;
    }

    public void setPressureMinValue(float pressureMinValue) {
        this.pressureMinValue = pressureMinValue;
    }

    public float getPressureMaxValue() {
        return pressureMaxValue;
    }

    public void setPressureMaxValue(float pressureMaxValue) {
        this.pressureMaxValue = pressureMaxValue;
    }

    public float getProximityMinValue() {
        return proximityMinValue;
    }

    public void setProximityMinValue(float proximityMinValue) {
        this.proximityMinValue = proximityMinValue;
    }

    public float getProximityMaxValue() {
        return proximityMaxValue;
    }

    public void setProximityMaxValue(float proximityMaxValue) {
        this.proximityMaxValue = proximityMaxValue;
    }

    public float getRelativeHumidityMinValue() {
        return relativeHumidityMinValue;
    }

    public void setRelativeHumidityMinValue(float relativeHumidityMinValue) {
        this.relativeHumidityMinValue = relativeHumidityMinValue;
    }

    public float getRelativeHumidityMaxValue() {
        return relativeHumidityMaxValue;
    }

    public void setRelativeHumidityMaxValue(float relativeHumidityMaxValue) {
        this.relativeHumidityMaxValue = relativeHumidityMaxValue;
    }

    public float getRotationVectorMinXValue() {
        return rotationVectorMinXValue;
    }

    public void setRotationVectorMinXValue(float rotationVectorMinXValue) {
        this.rotationVectorMinXValue = rotationVectorMinXValue;
    }

    public float getRotationVectorMinYValue() {
        return rotationVectorMinYValue;
    }

    public void setRotationVectorMinYValue(float rotationVectorMinYValue) {
        this.rotationVectorMinYValue = rotationVectorMinYValue;
    }

    public float getRotationVectorMinZValue() {
        return rotationVectorMinZValue;
    }

    public void setRotationVectorMinZValue(float rotationVectorMinZValue) {
        this.rotationVectorMinZValue = rotationVectorMinZValue;
    }

    public float getRotationVectorMaxXValue() {
        return rotationVectorMaxXValue;
    }

    public void setRotationVectorMaxXValue(float rotationVectorMaxXValue) {
        this.rotationVectorMaxXValue = rotationVectorMaxXValue;
    }

    public float getRotationVectorMaxYValue() {
        return rotationVectorMaxYValue;
    }

    public void setRotationVectorMaxYValue(float rotationVectorMaxYValue) {
        this.rotationVectorMaxYValue = rotationVectorMaxYValue;
    }

    public float getRotationVectorMaxZValue() {
        return rotationVectorMaxZValue;
    }

    public void setRotationVectorMaxZValue(float rotationVectorMaxZValue) {
        this.rotationVectorMaxZValue = rotationVectorMaxZValue;
    }

    public void setSensorList(boolean[] sensorList) {
        this.sensorList = sensorList;
    }

    //====================================================================

    public boolean[] getSensorList() {
        return sensorList;
    }

    public void setSensor() {
        this.sensorList[TYPE_ACCELEROMETER] = isAccelerometer();
        this.sensorList[TYPE_AMBIENT_TEMPERATURE] = isAmbientTemperature();
        this.sensorList[TYPE_GRAVITY] = isGravity();
        this.sensorList[TYPE_GYROSCOPE] = isGyroscope();
        this.sensorList[TYPE_LIGHT] = isLight();
        this.sensorList[TYPE_LINEAR_ACCELERATION] = isLinearAcceleration();
        this.sensorList[TYPE_MAGNETIC_FIELD] = isMagneticField();
        this.sensorList[TYPE_ORIENTATION] = isOrientation();
        this.sensorList[TYPE_PRESSURE] = isPressure();
        this.sensorList[TYPE_PROXIMITY] = isProximity();
        this.sensorList[TYPE_RELATIVE_HUMIDITY] = isRelativeHumidity();
        this.sensorList[TYPE_ROTATION_VECTOR] = isRotation();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.questionnaireID);
        dest.writeInt(this.minTimeSpace);
        dest.writeString(this.time);
        dest.writeLong(this.dateTime != null ? this.dateTime.getTime() : -1);
        dest.writeByte(this.triggeredWhenCalendarEventBegins ? (byte) 1 : (byte) 0);
        dest.writeByte(this.triggeredWhenCalendarEventEnds ? (byte) 1 : (byte) 0);
        dest.writeByte(this.triggeredWhenFacebookNotificationComes ? (byte) 1 : (byte) 0);
        dest.writeByte(this.triggeredWhenWhatsAppNotificationComes ? (byte) 1 : (byte) 0);
        dest.writeByte(this.linearAcceleration ? (byte) 1 : (byte) 0);
        dest.writeByte(this.gravity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.rotation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.ambientTemperature ? (byte) 1 : (byte) 0);
        dest.writeByte(this.pressure ? (byte) 1 : (byte) 0);
        dest.writeByte(this.light ? (byte) 1 : (byte) 0);
        dest.writeByte(this.relativeHumidity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.orientation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.magneticField ? (byte) 1 : (byte) 0);
        dest.writeByte(this.proximity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.accelerometer ? (byte) 1 : (byte) 0);
        dest.writeByte(this.gyroscope ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.accelerometerMinXValue);
        dest.writeFloat(this.accelerometerMinYValue);
        dest.writeFloat(this.accelerometerMinZValue);
        dest.writeFloat(this.accelerometerMaxXValue);
        dest.writeFloat(this.accelerometerMaxYValue);
        dest.writeFloat(this.accelerometerMaxZValue);
        dest.writeFloat(this.ambientTemperatureMinValue);
        dest.writeFloat(this.ambientTemperatureMaxValue);
        dest.writeFloat(this.gravityMinXValue);
        dest.writeFloat(this.gravityMinYValue);
        dest.writeFloat(this.gravityMinZValue);
        dest.writeFloat(this.gravityMaxXValue);
        dest.writeFloat(this.gravityMaxYValue);
        dest.writeFloat(this.gravityMaxZValue);
        dest.writeFloat(this.gyroscopeMinXValue);
        dest.writeFloat(this.gyroscopeMinYValue);
        dest.writeFloat(this.gyroscopeMinZValue);
        dest.writeFloat(this.gyroscopeMaxXValue);
        dest.writeFloat(this.gyroscopeMaxYValue);
        dest.writeFloat(this.gyroscopeMaxZValue);
        dest.writeFloat(this.lightMinValue);
        dest.writeFloat(this.lightMaxValue);
        dest.writeFloat(this.linearAccelerationMinXValue);
        dest.writeFloat(this.linearAccelerationMinYValue);
        dest.writeFloat(this.linearAccelerationMinZValue);
        dest.writeFloat(this.linearAccelerationMaxXValue);
        dest.writeFloat(this.linearAccelerationMaxYValue);
        dest.writeFloat(this.linearAccelerationMaxZValue);
        dest.writeFloat(this.magneticFieldMinXValue);
        dest.writeFloat(this.magneticFieldMinYValue);
        dest.writeFloat(this.magneticFieldMinZValue);
        dest.writeFloat(this.magneticFieldMaxXValue);
        dest.writeFloat(this.magneticFieldMaxYValue);
        dest.writeFloat(this.magneticFieldMaxZValue);
        dest.writeFloat(this.orientationMinXValue);
        dest.writeFloat(this.orientationMinYValue);
        dest.writeFloat(this.orientationMinZValue);
        dest.writeFloat(this.orientationMaxXValue);
        dest.writeFloat(this.orientationMaxYValue);
        dest.writeFloat(this.orientationMaxZValue);
        dest.writeFloat(this.pressureMinValue);
        dest.writeFloat(this.pressureMaxValue);
        dest.writeFloat(this.proximityMinValue);
        dest.writeFloat(this.proximityMaxValue);
        dest.writeFloat(this.relativeHumidityMinValue);
        dest.writeFloat(this.relativeHumidityMaxValue);
        dest.writeFloat(this.rotationVectorMinXValue);
        dest.writeFloat(this.rotationVectorMinYValue);
        dest.writeFloat(this.rotationVectorMinZValue);
        dest.writeFloat(this.rotationVectorMaxXValue);
        dest.writeFloat(this.rotationVectorMaxYValue);
        dest.writeFloat(this.rotationVectorMaxZValue);
        dest.writeBooleanArray(this.sensorList);
    }

    protected TriggerEvent(Parcel in) {
        this.questionnaireID = in.readString();
        this.minTimeSpace = in.readInt();
        this.time = in.readString();
        long tmpDateTime = in.readLong();
        this.dateTime = tmpDateTime == -1 ? null : new Date(tmpDateTime);
        this.triggeredWhenCalendarEventBegins = in.readByte() != 0;
        this.triggeredWhenCalendarEventEnds = in.readByte() != 0;
        this.triggeredWhenFacebookNotificationComes = in.readByte() != 0;
        this.triggeredWhenWhatsAppNotificationComes = in.readByte() != 0;
        this.linearAcceleration = in.readByte() != 0;
        this.gravity = in.readByte() != 0;
        this.rotation = in.readByte() != 0;
        this.ambientTemperature = in.readByte() != 0;
        this.pressure = in.readByte() != 0;
        this.light = in.readByte() != 0;
        this.relativeHumidity = in.readByte() != 0;
        this.orientation = in.readByte() != 0;
        this.magneticField = in.readByte() != 0;
        this.proximity = in.readByte() != 0;
        this.accelerometer = in.readByte() != 0;
        this.gyroscope = in.readByte() != 0;
        this.accelerometerMinXValue = in.readFloat();
        this.accelerometerMinYValue = in.readFloat();
        this.accelerometerMinZValue = in.readFloat();
        this.accelerometerMaxXValue = in.readFloat();
        this.accelerometerMaxYValue = in.readFloat();
        this.accelerometerMaxZValue = in.readFloat();
        this.ambientTemperatureMinValue = in.readFloat();
        this.ambientTemperatureMaxValue = in.readFloat();
        this.gravityMinXValue = in.readFloat();
        this.gravityMinYValue = in.readFloat();
        this.gravityMinZValue = in.readFloat();
        this.gravityMaxXValue = in.readFloat();
        this.gravityMaxYValue = in.readFloat();
        this.gravityMaxZValue = in.readFloat();
        this.gyroscopeMinXValue = in.readFloat();
        this.gyroscopeMinYValue = in.readFloat();
        this.gyroscopeMinZValue = in.readFloat();
        this.gyroscopeMaxXValue = in.readFloat();
        this.gyroscopeMaxYValue = in.readFloat();
        this.gyroscopeMaxZValue = in.readFloat();
        this.lightMinValue = in.readFloat();
        this.lightMaxValue = in.readFloat();
        this.linearAccelerationMinXValue = in.readFloat();
        this.linearAccelerationMinYValue = in.readFloat();
        this.linearAccelerationMinZValue = in.readFloat();
        this.linearAccelerationMaxXValue = in.readFloat();
        this.linearAccelerationMaxYValue = in.readFloat();
        this.linearAccelerationMaxZValue = in.readFloat();
        this.magneticFieldMinXValue = in.readFloat();
        this.magneticFieldMinYValue = in.readFloat();
        this.magneticFieldMinZValue = in.readFloat();
        this.magneticFieldMaxXValue = in.readFloat();
        this.magneticFieldMaxYValue = in.readFloat();
        this.magneticFieldMaxZValue = in.readFloat();
        this.orientationMinXValue = in.readFloat();
        this.orientationMinYValue = in.readFloat();
        this.orientationMinZValue = in.readFloat();
        this.orientationMaxXValue = in.readFloat();
        this.orientationMaxYValue = in.readFloat();
        this.orientationMaxZValue = in.readFloat();
        this.pressureMinValue = in.readFloat();
        this.pressureMaxValue = in.readFloat();
        this.proximityMinValue = in.readFloat();
        this.proximityMaxValue = in.readFloat();
        this.relativeHumidityMinValue = in.readFloat();
        this.relativeHumidityMaxValue = in.readFloat();
        this.rotationVectorMinXValue = in.readFloat();
        this.rotationVectorMinYValue = in.readFloat();
        this.rotationVectorMinZValue = in.readFloat();
        this.rotationVectorMaxXValue = in.readFloat();
        this.rotationVectorMaxYValue = in.readFloat();
        this.rotationVectorMaxZValue = in.readFloat();
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
