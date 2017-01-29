package com.example.mindrate.gson;

import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Created by Renhan on 2017/1/23.
 */

public class GravitySensor {
    private SensorManager sM;
    public GravitySensor(TriggerEvent tE){
        sM = tE.getSensorManager();
    }


}
