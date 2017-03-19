package com.example.mindrate.gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Renhan on 2017/3/18.
 */
public class TriggerEventTest {
    private TriggerEvent triggerEvent;
    /*
    private String questionnaireID;
    //==========Trigger Time==============
    private int minTimeSpace;
    private String time;
    private Date dateTime;

    private boolean ambientTemperature;
    private boolean pressure;
    private boolean light;
    private boolean relativeHumidity;
    private boolean proximity;
    private float ambientTemperatureMinValue;
    private float ambientTemperatureMaxValue;
    private float lightMinValue;
    private float lightMaxValue;
    private float pressureMinValue;
    private float pressureMaxValue;
    //=================================================
    private float proximityMinValue;
    private float proximityMaxValue;
    //================================================
    private float relativeHumidityMinValue;
    private float relativeHumidityMaxValue;
    private boolean[] sensorList;
    */

    @Before
    public void setUp() throws Exception {
        triggerEvent = new TriggerEvent("ABC");
        /*
        this.questionnaireID = "ABC";
        this.sensorList = new boolean[12];
        minTimeSpace = 600;
        time = "18-00-00";
        Calendar cal = Calendar.getInstance();
        cal.set(1989,10,26,7,0,1);
        dateTime = cal.getTime();
        ambientTemperature = true;
        pressure = false;
        light = true;
        relativeHumidity = false;
        proximity = false;
        ambientTemperatureMinValue = 10;
        ambientTemperatureMaxValue = 11;
        lightMinValue = 1000;
        lightMaxValue = 2000;
        */

    }

    @After
    public void tearDown() throws Exception {
        triggerEvent = null;

    }

    @Test
    public void getTime1() throws Exception {
        assertNull(triggerEvent.getTime());
    }

    @Test
    public void setTime() throws Exception {
        triggerEvent.setTime("18-00-00");
        assertEquals(triggerEvent.getTime(),"18-00-00");

    }

    @Test
    public void getDateTime() throws Exception {
        assertNull(triggerEvent.getDateTime());

    }

    @Test
    public void setDateTime() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(1986,9,16,10,0,1);
        triggerEvent.setDateTime(cal.getTime());
        assertEquals(triggerEvent.getDateTime().getTime(),cal.getTime().getTime());
        cal.add(Calendar.SECOND,1);
        assertTrue(triggerEvent.getDateTime().before(cal.getTime()));
    }

    @Test
    public void getMinTimeSpace() throws Exception {
        assertEquals(triggerEvent.getMinTimeSpace(),0);

    }

    @Test
    public void setMinTimeSpace() throws Exception {
        triggerEvent.setMinTimeSpace(600);
        assertEquals(triggerEvent.getMinTimeSpace(),600);

    }

    @Test
    public void getQuestionnaireID() throws Exception {
        assertEquals(triggerEvent.getQuestionnaireID(),"ABC");

    }

    @Test
    public void setQuestionnaireID() throws Exception {
        triggerEvent.setQuestionnaireID("AB");
        assertEquals(triggerEvent.getQuestionnaireID(),"AB");
        assertNotEquals(triggerEvent.getQuestionnaireID(),"ABC");

    }

    @Test
    public void isAmbientTemperature() throws Exception {
        assertFalse(triggerEvent.isAmbientTemperature());

    }

    @Test
    public void setAirTemperature() throws Exception {
        triggerEvent.setAirTemperature(true);
        assertTrue(triggerEvent.isAmbientTemperature());

    }

    @Test
    public void isPressure() throws Exception {
        assertFalse(triggerEvent.isPressure());
    }

    @Test
    public void setPressure() throws Exception {
        triggerEvent.setPressure(true);
        assertTrue(triggerEvent.isPressure());
    }

    @Test
    public void isLight() throws Exception {
        assertFalse(triggerEvent.isLight());

    }

    @Test
    public void setLight() throws Exception {
        triggerEvent.setLight(true);
        assertTrue(triggerEvent.isLight());
    }

    @Test
    public void isRelativeHumidity() throws Exception {
        assertFalse(triggerEvent.isRelativeHumidity());
    }

    @Test
    public void setRelativeHumidity() throws Exception {
        triggerEvent.setRelativeHumidity(true);
        assertTrue(triggerEvent.isRelativeHumidity());

    }

    @Test
    public void isProximity() throws Exception {
        assertFalse(triggerEvent.isProximity());
    }

    @Test
    public void setProximity() throws Exception {
        triggerEvent.setProximity(true);
        assertTrue(triggerEvent.isProximity());
    }

    @Test
    public void getAmbientTemperatureMinValue() throws Exception {
        assertEquals(0,triggerEvent.getAmbientTemperatureMinValue(),0);


    }

    @Test
    public void setAmbientTemperatureMinValue() throws Exception {
        triggerEvent.setAmbientTemperatureMinValue(10);
        assertEquals(10,triggerEvent.getAmbientTemperatureMinValue(),0.01);

    }

    @Test
    public void getAmbientTemperatureMaxValue() throws Exception {
        assertEquals(0,triggerEvent.getAmbientTemperatureMaxValue(),0);
    }

    @Test
    public void setAmbientTemperatureMaxValue() throws Exception {
        triggerEvent.setAmbientTemperatureMaxValue(11);
        assertEquals(11,triggerEvent.getAmbientTemperatureMaxValue(),0.01);
    }

    @Test
    public void getLightMinValue() throws Exception {
        assertEquals(0,triggerEvent.getLightMinValue(),0);
    }

    @Test
    public void setLightMinValue() throws Exception {
        triggerEvent.setLightMinValue(1100);
        assertEquals(1100,triggerEvent.getLightMinValue(),1);
    }

    @Test
    public void getLightMaxValue() throws Exception {
        assertEquals(0,triggerEvent.getLightMaxValue(),0);
    }

    @Test
    public void setLightMaxValue() throws Exception {
        triggerEvent.setLightMaxValue(1200);
        assertEquals(1200,triggerEvent.getLightMaxValue(),1);

    }

    @Test
    public void getPressureMinValue() throws Exception {
        assertEquals(0,triggerEvent.getPressureMinValue(),0);
    }

    @Test
    public void setPressureMinValue() throws Exception {
        triggerEvent.setPressureMinValue(50);
        assertEquals(50,triggerEvent.getPressureMinValue(),1);

    }

    @Test
    public void getPressureMaxValue() throws Exception {
        assertEquals(0,triggerEvent.getPressureMaxValue(),0);
    }

    @Test
    public void setPressureMaxValue() throws Exception {
        triggerEvent.setPressureMaxValue(60);
        assertEquals(60,triggerEvent.getPressureMaxValue(),1);
    }

    @Test
    public void getProximityMinValue() throws Exception {
        assertEquals(0,triggerEvent.getProximityMinValue(),0);
    }

    @Test
    public void setProximityMinValue() throws Exception {
        triggerEvent.setProximityMinValue(10);
        assertEquals(10,triggerEvent.getProximityMinValue(),0.1);

    }

    @Test
    public void getProximityMaxValue() throws Exception {
        assertEquals(0,triggerEvent.getProximityMaxValue(),0);
    }

    @Test
    public void setProximityMaxValue() throws Exception {
        triggerEvent.setProximityMaxValue(20);
        assertEquals(20,triggerEvent.getProximityMaxValue(),0.1);

    }

    @Test
    public void getRelativeHumidityMinValue() throws Exception {
        assertEquals(0,triggerEvent.getRelativeHumidityMinValue(),0);
    }

    @Test
    public void setRelativeHumidityMinValue() throws Exception {
        triggerEvent.setRelativeHumidityMinValue(25);
        assertEquals(25,triggerEvent.getRelativeHumidityMinValue(),0.1);
    }

    @Test
    public void getRelativeHumidityMaxValue() throws Exception {
        assertEquals(0,triggerEvent.getRelativeHumidityMaxValue(),0);
}

    @Test
    public void setRelativeHumidityMaxValue() throws Exception {
        triggerEvent.setRelativeHumidityMaxValue(30);
        assertEquals(30,triggerEvent.getRelativeHumidityMaxValue(),0.1);
    }

    @Test
    public void setSensorList() throws Exception {
        assertEquals(12,triggerEvent.getSensorList().length);
        boolean[] newSensorList = new boolean[12];
        newSensorList[1] = true;
        triggerEvent.setSensorList(newSensorList);
        assertTrue(triggerEvent.getSensorList()[1]);

    }

    @Test
    public void getSensorList() throws Exception {
        assertEquals(12,triggerEvent.getSensorList().length);
        for(int i =0;i<triggerEvent.getSensorList().length;i++){
            assertFalse(triggerEvent.getSensorList()[i]);
        }

    }

    @Test
    public void setSensor() throws Exception {
        int index = -1;
        triggerEvent.setSensor();
        for(int i=0;i<triggerEvent.getSensorList().length;i++){
            assertFalse(triggerEvent.getSensorList()[i]);
        }
        triggerEvent.setLight(true);
        triggerEvent.setSensor();
        for (int i=0;i<triggerEvent.getSensorList().length;i++){
            if(triggerEvent.getSensorList()[i]){
                index = i;
                break;
            }
        }
        assertEquals(4,index);

    }

}