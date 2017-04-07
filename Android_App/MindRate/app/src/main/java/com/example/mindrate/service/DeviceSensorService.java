package com.example.mindrate.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
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

/**
 * This class  represents a service for calling sensor and transferring the data of sensor.
 * <p>
 *     When users open this app,this service can be automatic called.Then listener for sensor can
 *     be created and added.
 *
 */
public class DeviceSensorService extends Service {
    //==============for test and debug================
    private static final String TAG = "DeviceSensorService";
    PowerManager.WakeLock wakeLock =null;
    //================================================

    private SensorManager sensorManager;
    private List<Sensor> allSensors;
    private List<AllSensorEventListener> allSensorEventListeners;
    private TriggerEventManager triggerEventManager;

    public List<Sensor> getUsedSensorList() {
        return usedSensorList;
    }

    private List<Sensor> usedSensorList;

    public boolean[] getUsedSensor() {
        return usedSensor;
    }

    private boolean[] usedSensor;
    private  final ServiceBinder serviceBinder =  new ServiceBinder();

    public String getServiceID() {
        return serviceID;
    }

    private String serviceID;


    /**
     * Instantiates a new Device sensor service.
     */
    public DeviceSensorService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.usedSensor = new boolean[12];
        this.allSensors = new ArrayList<>();
        this.allSensorEventListeners = new ArrayList<>();
        this.usedSensorList = new ArrayList<>();
        if (sensorManager == null) {
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        }
        allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);//maybe speciall sensor
        this.triggerEventManager = TriggerEventManager.getTriggerEventManager();
        this.setUsedSensor();
        this.setUsedSensorList();

        for (Sensor sensor : usedSensorList) {
            this.addSensorEventListener(sensor);
        }
        //===========for power manager=======================

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, DeviceSensorService.class
                .getName());
        wakeLock.acquire();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getStringExtra("ServiceID")!=null) {
            this.serviceID = intent.getStringExtra("ServiceID");
        }
        for (AllSensorEventListener listener : allSensorEventListeners) {
            this.sensorManager.registerListener(listener, listener.getSensor(), SensorManager
                    .SENSOR_DELAY_UI);
        }
        //=======for test and debug===========
        Log.i(TAG, "Service onStart_____");

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (sensorManager != null) {
            for (AllSensorEventListener listener : allSensorEventListeners) {
                sensorManager.unregisterListener(listener);
            }

        }
        if (wakeLock != null) {
            wakeLock.release();
            wakeLock = null;
        }
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "return Binder");
        //return mBinder;
        return serviceBinder;

    }


    /**
     * Add listener for specific sensor.
     *
     * @param sensor the specific sensor
     */
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

    /**
     * Helper Method.Set value of list usedSensor.
     * <p>
     * <br>Find all sensors,that will be called.</br>
     * <br>if a sensor(with index i)will be called,then set usedSensor[i]=true.</br>
     */
    public void setUsedSensor() {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        if(this.triggerEventManager
                .getQuestionnaireList()!=null) {
            questionnaireList = this.triggerEventManager
                    .getQuestionnaireList();
        }

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

    /**
     * Helper method.Set value of list usedSensorList.
     * <p>
     * <br>After setting the value of list usedSensor, a list of sensors will be got,which
     * includes all called sensor.</br>
     *
     */
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

    /**
     *
     */
    public class ServiceBinder extends Binder {
        public DeviceSensorService getService()throws Exception{
            DeviceSensorService service =null;
            while(service ==null) {
                //sleep(10000);
                service = DeviceSensorService.this;
            }
            return service;//DeviceSensorService.this;
        }


    }
}


