package com.example.mindrate.gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Renhan on 2017/3/19.
 */
public class TriggerEventManagerTest {
    private TriggerEventManager triggerEventManager;
    @Before
    public void setUp() throws Exception {
        triggerEventManager = TriggerEventManager.getTriggerEventManager();

    }

    @After
    public void tearDown() throws Exception {
        triggerEventManager.setQuestionnaireList(null);
        triggerEventManager.setOverviewActivity(null);
        for(int i = 0;i<triggerEventManager.getDataOfAllSensor().length;i++){
            float[] a = new float[3];
            triggerEventManager.setDataOfSensor(i,a);
        }

        triggerEventManager = null;
    }

    @Test
    public void getTriggerEventManager() throws Exception {
        assertNotNull(TriggerEventManager.getTriggerEventManager());

    }

    @Test
    public void getDataOfAllSensor() throws Exception {
        assertNotNull(triggerEventManager.getDataOfAllSensor());
        assertEquals(12,triggerEventManager.getDataOfAllSensor().length);
        for(int i =0;i<triggerEventManager.getDataOfAllSensor().length;i++){
            assertEquals(3,triggerEventManager.getDataOfAllSensor()[i].length);
        }
        for(int i =0;i<triggerEventManager.getDataOfAllSensor().length;i++){
            for(int j =0;j<triggerEventManager.getDataOfAllSensor()[i].length;j++){
                assertEquals(0,triggerEventManager.getDataOfAllSensor()[i][j],0);
            }

        }

    }

    @Test
    public void getShouldAnswerQuestionnaire() throws Exception {
        assertNotNull(triggerEventManager.getShouldAnswerQuestionnaire());

    }

    @Test
    public void getQuestionnaireList() throws Exception {
        assertNull(triggerEventManager.getQuestionnaireList());

    }

    @Test
    public void setQuestionnaireList() throws Exception {
        List<Questionnaire> TestList = new ArrayList<>();
        Questionnaire test1 = new Questionnaire();
        Questionnaire test2 = new Questionnaire("test2");
        Questionnaire test3 = new Questionnaire("study2","test3");
        TestList.add(test1);
        TestList.add(test2);
        TestList.add(test3);
        triggerEventManager.setQuestionnaireList(TestList);
        assertNotNull(triggerEventManager.getQuestionnaireList());
        assertEquals(3,triggerEventManager.getQuestionnaireList().size());
        assertEquals("test2",triggerEventManager.getQuestionnaireList().get(1).getQuestionnaireID());
        assertEquals("study2",triggerEventManager.getQuestionnaireList().get(2).getStudyID());
        assertEquals("test3",triggerEventManager.getQuestionnaireList().get(2).getQuestionnaireID());

    }

    @Test
    public void setDataOfSensor() throws Exception {
        float[] testData = new float[3];
        testData[0] = 1000;
        triggerEventManager.setDataOfSensor(4,testData);
        assertEquals(1000,triggerEventManager.getDataOfAllSensor()[4][0],0);

    }

    @Test
    public void addShouldAnswerQuestionnaire() throws Exception {


    }


}