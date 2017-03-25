package com.example.mindrate.service;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.mindrate.gson.TriggerEventManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * Created by Renhan on 2017/3/20.
 */
@RunWith(AndroidJUnit4.class)
public class DeviceSensorServiceTest {
    private DeviceSensorService deviceSensorService;
    private TriggerEventManager triggerEventManager;

    @Rule
    public final ServiceTestRule deviceSensorServiceRule =
            ServiceTestRule.withTimeout(60L, TimeUnit.SECONDS);;

    @Before
    public void setUp() throws Exception {


    }


    @After
    public void tearDown() throws Exception {
        //TriggerEventManager.getTriggerEventManager().setQuestionnaireList(null);


    }

    @Test
    public void testWithBoundService() throws Exception {
        Intent intent = new Intent(InstrumentationRegistry.getTargetContext(),
                DeviceSensorService.class);
        intent.putExtra("ServiceID","test");
        deviceSensorServiceRule.startService(intent);
        sleep(1000);
        IBinder binder=deviceSensorServiceRule.bindService(intent);
        DeviceSensorService service = ((DeviceSensorService.ServiceBinder) binder).getService();
        assertEquals("test",service.getServiceID());
    }


    //can be deleted.


    @Test
    public void testSetUsedSensor()throws Exception{

        /*triggerEventManager = TriggerEventManager.getTriggerEventManager();
        Questionnaire test1 = new Questionnaire("ABC");
        TriggerEvent triggerEvent = new TriggerEvent("ABC");
        triggerEvent.setRelativeHumidity(true);
        test1.setTriggerEvent(triggerEvent);
        List<Questionnaire> qList = new ArrayList<>();
        qList.add(test1);
        triggerEventManager.setQuestionnaireList(qList);*/


        Intent intent = new Intent(InstrumentationRegistry.getTargetContext(),
                DeviceSensorService.class);
        deviceSensorServiceRule.startService(intent);
        IBinder binder =deviceSensorServiceRule.bindService(intent);
        //((DeviceSensorService.ServiceBinder) binder).renewTriggerEventManager
        // (triggerEventManager);
        sleep(1000);
        DeviceSensorService service = ((DeviceSensorService.ServiceBinder) binder).getService();
        int index =-1;
        for(int i=0;i<service.getUsedSensor().length;i++){
            if(service.getUsedSensor()[i]){
                index = i;
                break;
            }
        }
        assertEquals(-1,index);


    }



    @Test
    public void testSetUsedSensorList()throws Exception{


        /*triggerEventManager = TriggerEventManager.getTriggerEventManager();
        Questionnaire test1 = new Questionnaire("ABC");
        TriggerEvent triggerEvent = new TriggerEvent("ABC");
        triggerEvent.setRelativeHumidity(true);
        test1.setTriggerEvent(triggerEvent);
        List<Questionnaire> qList = new ArrayList<>();
        qList.add(test1);
        triggerEventManager.setQuestionnaireList(qList);
        */

/*
        Intent intent = new Intent(InstrumentationRegistry.getTargetContext(),
                DeviceSensorService.class);
        deviceSensorServiceRule.startService(intent);
        IBinder binder =deviceSensorServiceRule.bindService(intent);
        //((DeviceSensorService.ServiceBinder) binder).renewTriggerEventManager
        // (triggerEventManager);
        DeviceSensorService service = ((DeviceSensorService.ServiceBinder) binder).getService();
        assertEquals(0,service.getUsedSensorList().size());
*/

    }



}