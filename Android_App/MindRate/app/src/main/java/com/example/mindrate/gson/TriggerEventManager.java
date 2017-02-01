package com.example.mindrate.gson;

import java.util.Observable;
/**
 * Created by Renhan on 2017/1/25.
 */

public class TriggerEventManager extends Observable{
    private boolean isQuestionnaireValid;

    public TriggerEventManager(){
        this.isQuestionnaireValid = false;
    }

    public boolean getIsQuestionnaireValid() {
        return isQuestionnaireValid;
    }

    public void setQuestionnaireValid(boolean questionnaireValid) {
        isQuestionnaireValid = questionnaireValid;
    }



    public void notifyToQuestionnaire(){


    }





}
