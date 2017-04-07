package com.example.mindrate.gson;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE;
import static android.hardware.Sensor.TYPE_GRAVITY;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_LIGHT;
import static android.hardware.Sensor.TYPE_LINEAR_ACCELERATION;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;
import static android.hardware.Sensor.TYPE_PRESSURE;
import static android.hardware.Sensor.TYPE_PROXIMITY;
import static android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY;
import static android.hardware.Sensor.TYPE_ROTATION_VECTOR;

/**
 * this class represents Sensor Event Listener.
 * <br>Used for receiving notifications from the SensorManager when there is new sensor data.</br>
 * <br>Created by Renhan on 2017/2/8.</br>
 */
public class AllSensorEventListener implements SensorEventListener{
    //==============for test and debug=============================
    private static final String TAG = "AllSensorEventListener";


    //==========================================================
    private Sensor sensor;
    private TriggerEventManager triggerEventManager;
    private float[] dataOfSensor;

    /**
     * Instantiates a new sensor event listener.
     *
     * @param triggerEventManager the trigger event manager
     * @param sensor              the specific sensor
     */
    public AllSensorEventListener(TriggerEventManager triggerEventManager,Sensor sensor){
        this.triggerEventManager = triggerEventManager;
        this.sensor = sensor;
        this.dataOfSensor = new float[3];
        for(int i=0;i<dataOfSensor.length;i++){
            dataOfSensor[i] = -100000;
        }

    }


    public void onSensorChanged(SensorEvent event){
        switch (event.sensor.getType()){
            case TYPE_ACCELEROMETER:
                float xValueAcceleration = event.values[0];
                float yValueAcceleration = event.values[1];
                float zValueAcceleration = event.values[2];
                //
                //this.setDataOfSensor(0,xValueAcceleration,yValueAcceleration,zValueAcceleration);
                this.dataOfSensor[0]= xValueAcceleration;
                this.dataOfSensor[1]= yValueAcceleration;
                this.dataOfSensor[2]= zValueAcceleration;
                this.triggerEventManager.setDataOfSensor(0,this.dataOfSensor);

                break;
            case TYPE_GRAVITY:
                float xValueGravity = event.values[0];
                float yValueGravity  = event.values[1];
                float zValueGravity  = event.values[2];
                this.dataOfSensor[0]= xValueGravity;
                this.dataOfSensor[1]= yValueGravity;
                this.dataOfSensor[2]= zValueGravity;
                this.triggerEventManager.setDataOfSensor(2,this.dataOfSensor);
                //
                break;
            case TYPE_GYROSCOPE:
                float xValueAngularSpeed = event.values[0];
                float yValueAngularSpeed  = event.values[1];
                float zValueAngularSpeed  = event.values[2];
                this.dataOfSensor[0]= xValueAngularSpeed;
                this.dataOfSensor[1]= yValueAngularSpeed;
                this.dataOfSensor[2]= zValueAngularSpeed;
                this.triggerEventManager.setDataOfSensor(3,this.dataOfSensor);

                //
                break;
            case TYPE_LIGHT:
                float light = event.values[0];
                this.dataOfSensor[0]= light;
                this.triggerEventManager.setDataOfSensor(4,this.dataOfSensor);

                break;
            case TYPE_LINEAR_ACCELERATION:
                float xValueLinearAcceleration = event.values[0];
                float yValueLinearAcceleration = event.values[1];
                float zValueLinearAcceleration = event.values[2];
                this.dataOfSensor[0]= xValueLinearAcceleration;
                this.dataOfSensor[1]= yValueLinearAcceleration;
                this.dataOfSensor[2]= zValueLinearAcceleration;
                this.triggerEventManager.setDataOfSensor(5,this.dataOfSensor);
                //
                break;
            case TYPE_MAGNETIC_FIELD:
                float xValueMagneticField = event.values[0];
                float yValueMagneticField  = event.values[1];
                float zValueMagneticField = event.values[2];
                this.dataOfSensor[0]= xValueMagneticField;
                this.dataOfSensor[1]= yValueMagneticField;
                this.dataOfSensor[2]= zValueMagneticField;
                this.triggerEventManager.setDataOfSensor(6,this.dataOfSensor);
                //
                break;
            case TYPE_AMBIENT_TEMPERATURE:
                float temperature = event.values[0];
                this.dataOfSensor[0]=temperature;
                this.triggerEventManager.setDataOfSensor(1,this.dataOfSensor);
                //
                break;
            case TYPE_PRESSURE:
                float pressure = event.values[0];
                this.dataOfSensor[0]= pressure;
                this.triggerEventManager.setDataOfSensor(8,this.dataOfSensor);
                //
                break;
            case TYPE_PROXIMITY:
                float proximity = event.values[0];
                this.dataOfSensor[0]= proximity;
                this.triggerEventManager.setDataOfSensor(9,this.dataOfSensor);
                //
                break;
            case TYPE_RELATIVE_HUMIDITY:
                float humidity = event.values[0];
                this.dataOfSensor[0]=humidity;
                this.triggerEventManager.setDataOfSensor(10,this.dataOfSensor);


                //
                break;
            case TYPE_ROTATION_VECTOR:
                float xValueRotationVector = event.values[0];
                float yValueRotationVector  = event.values[1];
                float zValueRotationVector = event.values[2];
                this.dataOfSensor[0]= xValueRotationVector;
                this.dataOfSensor[1]= yValueRotationVector;
                this.dataOfSensor[2]= zValueRotationVector;
                this.triggerEventManager.setDataOfSensor(11,this.dataOfSensor);
                //
                break;
            default:
                //TYPE_ORIENTATION:


                //
                break;


        }

    }
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    /**
     * Gets sensor.
     *
     * @return the sensor
     */
//=====================getter and setter=============================
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * Sets sensor.
     *
     * @param sensor the sensor
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    /**
     * Get data of sensor float [ ].
     *
     * @return the float [ ]
     */
    public float[] getDataOfSensor() {
        return dataOfSensor;
    }

    /**
     * Sets data of sensor.
     *
     * @param dataOfSensor the data of sensor
     */
    public void setDataOfSensor(float[] dataOfSensor) {
        this.dataOfSensor = dataOfSensor;
    }

//===================================================================

}
