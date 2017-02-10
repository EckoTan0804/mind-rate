package com.example.mindrate.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;

import com.example.mindrate.gson.AllSensorEventListener;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.TriggerEvent;
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

public class DeviceSensorService extends Service {
    private SensorManager sensorManager;
    private List<Sensor> allSensors;
    private List<AllSensorEventListener> allSensorEventListeners;
    private TriggerEventManager triggerEventManager;
    private List<Sensor> usedSensorList;


    public DeviceSensorService(SensorManager sensorManager,TriggerEventManager triggerEventManager) {
        this.sensorManager = sensorManager;
        this.triggerEventManager = triggerEventManager;
        this.allSensors = new ArrayList<>() ;
        this.allSensorEventListeners = new ArrayList<>();
        this.usedSensorList = new ArrayList<>();

    }

    @Override
    public void onCreate(){
        super.onCreate();
        if(sensorManager == null){
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        }
        allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);//maybe speciall sensor
        for(Sensor sensor:allSensors){
            this.addSensorEventListener(sensor);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        for(AllSensorEventListener listener : allSensorEventListeners){
            this.sensorManager.registerListener(listener,listener.getSensor(),SensorManager
                    .SENSOR_DELAY_NORMAL);
        }

        // other things?
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (sensorManager!= null) {
            for(AllSensorEventListener listener : allSensorEventListeners ) {
                sensorManager.unregisterListener(listener);
            }

        }
    }

    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void  addSensorEventListener(Sensor sensor){

        switch(sensor.getType()){
            case TYPE_ACCELEROMETER:
                AllSensorEventListener accelerometerSensor = new AllSensorEventListener(this.triggerEventManager,
                        sensor);
                this.allSensorEventListeners.add(accelerometerSensor);

                break;
            case TYPE_AMBIENT_TEMPERATURE:
                AllSensorEventListener ambientTemperatureSensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(ambientTemperatureSensor);
                //
                break;
            case TYPE_GRAVITY:
                AllSensorEventListener gravitySensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(gravitySensor);
                //
                break;
            case TYPE_GYROSCOPE:
                AllSensorEventListener gyroscopeSensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(gyroscopeSensor);
                //
                break;
            case TYPE_LIGHT:
                AllSensorEventListener lightSensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(lightSensor);
                //
                break;
            case TYPE_LINEAR_ACCELERATION:
                AllSensorEventListener linearAccelerationSensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(linearAccelerationSensor);
                //
                break;
            case TYPE_MAGNETIC_FIELD:
                AllSensorEventListener magneticFieldSensor = new AllSensorEventListener(this.triggerEventManager, sensor);
                this.allSensorEventListeners.add(magneticFieldSensor);
                //
                break;
            case TYPE_ORIENTATION:
                AllSensorEventListener orientationSensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(orientationSensor);
                //
                break;
            case TYPE_PRESSURE:
                AllSensorEventListener pressureSensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(pressureSensor);
                //
                break;
            case TYPE_PROXIMITY:
                AllSensorEventListener proximitySensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(proximitySensor);
                //
                break;
            case TYPE_RELATIVE_HUMIDITY:
                AllSensorEventListener relativeHumiditySensor = new AllSensorEventListener
                        (this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(relativeHumiditySensor);
                //
                break;
            default :
                AllSensorEventListener rotationVectorSensor = new AllSensorEventListener(this.triggerEventManager,sensor);
                this.allSensorEventListeners.add(rotationVectorSensor);
                //
                break;




        }


    }

    public void setUsedSensor(){
        for(Questionnaire questionnaire:this.triggerEventManager.getQuestionnaireList()){
            TriggerEvent triggerEvent = questionnaire.getTriggerEvent();
            //get every used sensor

            }
    }
}


