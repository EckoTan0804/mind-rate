package com.example.mindrate.gson;


/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:32
 */

import java.util.Collection;

/**
 * This class aims to model the participant of this study.
 */
public class Proband {

    /**
     * It represents the participant's ID number.
     */
    private String probandID;
    /**
     * It represents the study's ID number,which will be told to participant in advance
     */
    private String studyID;
    /**
     * It represents the gender of the participant
     */
    private String gender;
    /**
     * It represents the age of the participant
     */
    private int age;
    /**
     * It represents the occupation of the participant
     */
    private String occupation;
    /**
     * It represents the list of the questionaires,which the participant must answer
     */
    private Collection<Questionnaire> questionaires;
    /**
     * It represents the log-in status of the participant,true if the participant has at least
     * once logged in
     */
    private boolean isLogIn = false;

    /**
     * Constructor
     *

     * @param probandID the unique id for every proband
     * @param studyID the already received studyID
     * @param gender male / female
     * @param age age of the proband
     * @param occupation occupation of the proband
     */
    public Proband(String probandID, String studyID, String gender, int age, String occupation) {
        this.probandID = probandID;
        this.studyID = studyID;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.logIn();
    }

    /**
     *
     */
    public void logIn() {
        if (!isLogIn) {
            isLogIn = true;
        }
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
