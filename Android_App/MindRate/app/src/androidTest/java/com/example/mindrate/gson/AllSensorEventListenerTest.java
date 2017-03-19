package com.example.mindrate.gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Renhan on 2017/3/17.
 */
public class AllSensorEventListenerTest {
    private AllSensorEventListener allSensorEventListener;
/*
    private Sensor ambientTemperature;
    private Sensor light;
    private Sensor pressure;
    private Sensor proximity;
    private Sensor relativeHumidity;
    private TriggerEventManager triggerEventManager;
    private float[] dataOfSensor;
    private SensorManager sensorManager;
    */
    @Before
    public void setUp() throws Exception {
        allSensorEventListener = new AllSensorEventListener(null,null);
        /*
        ambientTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        relativeHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        triggerEventManager = TriggerEventManager.getTriggerEventManager();
        this.dataOfSensor = new float[3];
        for(int i=0;i<dataOfSensor.length;i++){
            dataOfSensor[i] = -100000;
        }
        */





    }

    @After
    public void tearDown() throws Exception {
       allSensorEventListener = null;

    }



    @Test
    public void getSensor() throws Exception {
        assertNull(allSensorEventListener.getSensor());

    }




    @Test
    public void getDataOfSensor() throws Exception {
        assertEquals(3,allSensorEventListener.getDataOfSensor().length);
        for(int i = 0;i<allSensorEventListener.getDataOfSensor().length;i++){
            assertEquals(-100000,allSensorEventListener.getDataOfSensor()[i],0);
        }

    }

    @Test
    public void setDataOfSensor() throws Exception {
        int index = -1;
        float[] testDataOfSensor = new float[3];
        testDataOfSensor[0]=100;
        allSensorEventListener.setDataOfSensor(testDataOfSensor);
        for(int i =0;i<allSensorEventListener.getDataOfSensor().length;i++){
            if(allSensorEventListener.getDataOfSensor()[i]==100){
                index = i;
                break;
            }
        }
        assertEquals(0,index);


    }

}