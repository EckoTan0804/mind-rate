package com.example.mindrate.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.mindrate.gson.QuestionnaireAnswer;

import java.util.ArrayList;

public class UploadService extends IntentService {

    private static ArrayList<QuestionnaireAnswer> answerUploadList;

    private UploadBinder mBinder = new UploadBinder();

    public UploadService(String name) {
        super(name);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // upload answer
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    public static void addToAnswerUploadList(QuestionnaireAnswer questionnaireAnswer) {
        answerUploadList.add(questionnaireAnswer);
    }


    class UploadBinder extends Binder {

        public void startUpload() {

        }
    }

}
