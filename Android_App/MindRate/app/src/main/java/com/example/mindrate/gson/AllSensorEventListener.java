package com.example.mindrate.gson;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
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
 * Created by Renhan on 2017/2/8.
 */

public class AllSensorEventListener implements SensorEventListener{


    private Sensor sensor;
    //private List<Float> dataOfSensor;

    public AllSensorEventListener(Sensor sensor){
        this.sensor = sensor;
        //this.dataOfSensor = new ArrayList <Float>();

    }
    public void onSensorChanged(SensorEvent event){
        switch (event.sensor.getType()){
            case TYPE_ACCELEROMETER:
                float xValueAcceleration = event.values[0];
                float yValueAcceleration = event.values[1];
                float zValueAcceleration = event.values[2];
                //
                break;
            case TYPE_GRAVITY:
                float xValueGravity = event.values[0];
                float yValueGravity  = event.values[1];
                float zValueGravity  = event.values[2];
                //
                break;
            case TYPE_GYROSCOPE:
                float xValueAngularSpeed = event.values[0];
                float yValueAngularSpeed  = event.values[1];
                float zValueAngularSpeed  = event.values[2];
                //
                break;
            case TYPE_LIGHT:
                float light = event.values[0];

                //
                break;
            case TYPE_LINEAR_ACCELERATION:
                float xValueLinearAcceleration = event.values[0];
                float yValueLinearAcceleration = event.values[1];
                float zValueLinearAcceleration = event.values[2];

                //
                break;
            case TYPE_MAGNETIC_FIELD:
                float xValueMagneticField = event.values[0];
                float yValueMagneticField  = event.values[1];
                float zValueMagneticField = event.values[2];
                //
                break;
            case TYPE_ORIENTATION:
                //
                break;
            case TYPE_PRESSURE:
                float pressure = event.values[0];
                //
                break;
            case TYPE_PROXIMITY:
                float proximity = event.values[0];
                //
                break;
            case TYPE_RELATIVE_HUMIDITY:
                float humidity = event.values[0];


                //
                break;
            case TYPE_ROTATION_VECTOR:
                float xValueRotationVector = event.values[0];
                float yValueRotationVector  = event.values[1];
                float zValueRotationVector = event.values[2];
                //
                break;
            default:
            //TYPE_AMBIENT_TEMPERATURE
                float temperature = event.values[0];
                //
                break;


        }

    }
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
//=====================getter and setter=============================
    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
//===================================================================

}
