package com.example.mindrate.gson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mindrate.activity.OverviewActivity;

/**
 * This class represents broadcast receiver.
 * It receives and handles broadcast intents sent by sendBroadcast(Intent).
 */
public class TriggerBroadcastReceiver extends BroadcastReceiver {
    //============For test and debug===========================
    private static final String TAG = "TriggerBroadcastReceive";

    /**
     * Instantiates a new Trigger broadcast receiver.
     */
    public TriggerBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.i(TAG,"receive broadcast");
        String questionnaireID = intent.getStringExtra("questionnaireID");
        if(intent.getAction().equals("addQuestionnaireToList")){
            OverviewActivity.getInstance().addQuestionnaireToTriggeredQuestionnaireList
                    (questionnaireID);
        }
    }


}
