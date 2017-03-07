package com.example.mindrate.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import com.example.mindrate.gson.AllSensorEventListener;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.TriggerEventManager;

import java.util.ArrayList;
import java.util.List;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE;
import static android.hardware.Sensor.TYPE_GRAVITY;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_LIGHT;
import static android.hardware.Sensor.TYPE_LINEAR_ACCELERATION;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;
import static android.hardware.Sensor.TYPE_ORIENTATION;
import static android.hardware.Sensor.TYPE_PRESSURE;
import static android.hardware.Sensor.TYPE_PROXIMITY;
import static android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY;
import static android.hardware.Sensor.TYPE_ROTATION_VECTOR;

public class DeviceSensorService extends Service {
    private static final String TAG = "DeviceSensorService";
    private SensorManager sensorManager;
    private List<Sensor> allSensors;
    private List<AllSensorEventListener> allSensorEventListeners;
    private TriggerEventManager triggerEventManager;
    private List<Sensor> usedSensorList;
    private boolean[] usedSensor;
    //private MyBinder mBinder = new MyBinder();


    public DeviceSensorService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.usedSensor = new boolean[12];
        this.allSensors = new ArrayList<>();
        this.allSensorEventListeners = new ArrayList<>();
        this.usedSensorList = new ArrayList<>();
        //this.triggerEventManager =null;
        if (sensorManager == null) {
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        }
        allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);//maybe speciall sensor
        this.triggerEventManager = TriggerEventManager.getTriggerEventManager();
        this.setUsedSensor();
        this.setUsedSensorList();

        // usedSensorList is not initialized
        // TODO: should initialize it using allQuestionnaireList from OverviewActivity
        for (Sensor sensor : usedSensorList) {
            this.addSensorEventListener(sensor);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        for (AllSensorEventListener listener : allSensorEventListeners) {
            this.sensorManager.registerListener(listener, listener.getSensor(), SensorManager
                    .SENSOR_DELAY_UI);
        }
        Log.i(TAG, "Service onStart_____");


        // other things?
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (sensorManager != null) {
            for (AllSensorEventListener listener : allSensorEventListeners) {
                sensorManager.unregisterListener(listener);
            }

        }
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "return Binder");
        //return mBinder;
        return null;
    }


   /* public class MyBinder extends Binder{
        private TriggerEventManager savedTriggerEventManager;
        public void setTriggerEventManager(TriggerEventManager triggerEventManager){
            Log.d(TAG,"setTEM");
            this.savedTriggerEventManager = triggerEventManager;
        }
        public TriggerEventManager getSavedTriggerEventManager(){
            return this.savedTriggerEventManager;
        }
        public void transferTriggerEventManager(){
            Log.d(TAG,"tranTEM");
            triggerEventManager = this.getSavedTriggerEventManager();
        }

    }*/

    public void addSensorEventListener(Sensor sensor) {


        switch (sensor.getType()) {
            case TYPE_ACCELEROMETER:
                AllSensorEventListener accelerometerSensor = new AllSensorEventListener(
                        this.triggerEventManager,
                        sensor);
                this.allSensorEventListeners.add(accelerometerSensor);

                break;
            case TYPE_AMBIENT_TEMPERATURE:
                AllSensorEventListener ambientTemperatureSensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(ambientTemperatureSensor);
                //
                break;
            case TYPE_GRAVITY:
                AllSensorEventListener gravitySensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(gravitySensor);
                //
                break;
            case TYPE_GYROSCOPE:
                AllSensorEventListener gyroscopeSensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(gyroscopeSensor);
                //
                break;
            case TYPE_LIGHT:
                AllSensorEventListener lightSensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(lightSensor);
                //
                break;
            case TYPE_LINEAR_ACCELERATION:
                AllSensorEventListener linearAccelerationSensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(linearAccelerationSensor);
                //
                break;
            case TYPE_MAGNETIC_FIELD:
                AllSensorEventListener magneticFieldSensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(magneticFieldSensor);
                //
                break;
            case TYPE_ORIENTATION:
                AllSensorEventListener orientationSensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(orientationSensor);
                //
                break;
            case TYPE_PRESSURE:
                AllSensorEventListener pressureSensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(pressureSensor);
                //
                break;
            case TYPE_PROXIMITY:
                AllSensorEventListener proximitySensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(proximitySensor);
                //
                break;
            case TYPE_RELATIVE_HUMIDITY:
                AllSensorEventListener relativeHumiditySensor = new AllSensorEventListener
                        (this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(relativeHumiditySensor);
                //
                break;
            default:
                AllSensorEventListener rotationVectorSensor = new AllSensorEventListener(
                        this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(rotationVectorSensor);
                //
                break;


        }


    }

    public void setUsedSensor() {
        List<Questionnaire> questionnaireList = this.triggerEventManager
                .getQuestionnaireList();

        for (Questionnaire questionnaire : questionnaireList) {
                questionnaire.getTriggerEvent().setSensor();
                boolean[] sensorList = questionnaire.getTriggerEvent().getSensorList();
                for (int i = 0; i < sensorList.length; i++) {
                    if (sensorList[i]) {
                        this.usedSensor[i] = true;
                    }
                }
        }
    }

    public void setUsedSensorList() {
        for (int i = 0; i < this.usedSensor.length; i++) {
            if (this.usedSensor[i]) {
                switch (i) {
                    case 0:
                        if (this.sensorManager.getDefaultSensor(TYPE_ACCELEROMETER) != null) {
                            this.usedSensorList
                                    .add(this.sensorManager.getDefaultSensor(TYPE_ACCELEROMETER));
                        }
                        break;
                    case 1:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_AMBIENT_TEMPERATURE) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_AMBIENT_TEMPERATURE));
                        }
                        break;
                    case 2:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_GRAVITY) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_GRAVITY));
                        }
                        break;
                    case 3:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_GYROSCOPE) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_GYROSCOPE));
                        }
                        break;
                    case 4:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_LIGHT) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_LIGHT));
                        }
                        break;
                    case 5:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_LINEAR_ACCELERATION) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_LINEAR_ACCELERATION));
                        }
                        break;
                    case 6:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_MAGNETIC_FIELD) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_MAGNETIC_FIELD));
                        }
                        break;
                    case 7:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_ORIENTATION) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_ORIENTATION));
                        }
                        break;
                    case 8:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_PRESSURE) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_PRESSURE));
                        }
                        break;
                    case 9:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_PROXIMITY) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_PROXIMITY));
                        }
                        break;
                    case 10:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_RELATIVE_HUMIDITY) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_RELATIVE_HUMIDITY));
                        }
                        break;
                    case 11:
                        if (this.sensorManager.getDefaultSensor
                                (TYPE_ROTATION_VECTOR) != null) {
                            this.usedSensorList.add(this.sensorManager.getDefaultSensor
                                    (TYPE_ROTATION_VECTOR));
                        }
                        break;

                }
            }
        }


    }
}


