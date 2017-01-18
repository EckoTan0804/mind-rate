package com.example.mindrate.gson;


/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:32
 */

import com.google.gson.annotations.SerializedName;

import java.util.Collection;

/**
 * This class aims to model the participant of this study.
 */
public class Proband {



    @SerializedName("probandID")
    private String probandID;

    @SerializedName("studyID")
    private String studyID;

    @SerializedName("gender")
    private String gender;

    private Birthday birthday;

//    public class Birthday {
//        @SerializedName("year")
//        public String year;
//        @SerializedName("month")
//        public String month;
//        @SerializedName("day")
//        public String day;
//    }

    @SerializedName("occupation")
    private String occupation;

    private Collection<Questionnaire> questionaires;


    public Proband(String studyID, String probandID,  Birthday
            birthday,String gender, String occupation) {
        this.occupation = occupation;
        this.probandID = probandID;
        this.studyID = studyID;
        this.gender = gender;
        this.birthday = birthday;
    }

    /**
     * This method aims to:
     * <li>firstly save the answer of questionnaire locally</li>
     * <li>upload the answer to the server if the network is available</li>
     *
     * @param questionnaireID the id of the just answered questionnaire
     */
    public void submitAnswer(int questionnaireID) {
        // 1. Questionnaire q = chooseQuestionnaire();
        // 2. q.saveAnswerLocally();
        // 3. q.uploadAnswers();
    }

    /**
     * The participants can choose a questionnaire they want to answer using this method.
     * <p> when this method is called,a specified questionnaire will be chosen and this
     * questionniare object will be returned.</p>
     *
     * @param questionnaireID the id of a questionnaire in the questionnaire's list
     * @return the Questionnaire object whose id is {@code questionnaireID}
     */
    public Questionnaire chooseQuestionnaire(int questionnaireID) {
        // TODO
        return null;
    }

    /**
     * The participant can answer the questions of the chosen questionnaire using this method.
     *
     * @param questionnaireID the id of a questionnaire in the questionnaire's list
     */
    public void answerQuestionnaire(int questionnaireID) {
        // TODO: proband answer questions and the answer will be recorded.
        // questionnaire = chooseQuestionnaire(questionnaireID)
        // for (Question q : questionnaire.questions) {
        //     answerQuestion(questionnaireID, q.ID);
        // }
    }

    /**
     * With this method the proband can answer the specified question in specified questionnaire.
     *
     * @param questionnaireID the id of a questionnaire in the questionnaire's list.
     * @param questionID the id of the question in the questionniare with {@code questionnaireID}
     */
    public void answerQuestion(int questionnaireID, int questionID) {

    }


}
