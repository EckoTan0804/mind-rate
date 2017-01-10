package com.example.mindrate.gson;


import android.hardware.Sensor;

import java.util.List;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:18:37
 */

public class SensorManager {

    private List<Sensor> sensorList;

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }
}
