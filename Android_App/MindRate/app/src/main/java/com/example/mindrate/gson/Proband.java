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
 * This class represents a participant,who had participated in a psychological research.
 * <p>This class represents a participant,who have a Study's ID(@param studyID) in advance gotten.<br>
 *     The participant should at first input his information,@param probandID,@param gender,@param age and @param occupation.
 *     (s)he has also a list of @param questionaires,which (s)he must answer.
 *     And each time when (s)he finished the questionaire,(s)he must submit his answers.
 */
public class Proband {
    /**It represents the participant's ID number.*/
    private String probandID;
    /** It represents the study's ID number,which will be told to participant in advance*/
    private String studyID;
    /** It represents the gender of the participant*/
    private String gender;
    /** It represents the age of the participant*/
    private int age;
    /** It represents the occupation of the participant*/
    private String occupation;
    /** It represents the list of the questionaires,which the participant must answer */
    private Collection<Questionnaire> questionaires;
    /** It represents the log-in status of the participant,true if the participant has at least once logged in*/
    private boolean isLogIn;
    /** */
    public Proband(String probandID,String studyID,String gender,int age,String occupation){

    }

    /**
     *Exception 如果输入的信息格式有错 则创建账户失败，login依旧为false
     */
    public void logIn(){

    }

    /**
     * With this method answers will be submitted and saved by participant.(?)
     *<p> when this method is called, questionaire's answers will be submitted and they will also saved locally. <br>
     *     firstly a Questionaire's number will be inputted,than the number will be looked up in the list of questionaires
     *     (?)整个过程是怎么样的 比如输入的参数只是问卷ID？
     *
     * @param QuestionaireID the number of a Questionaire in the Questionaire's list.
     * @return there is no return's value
     */
    public void submitAnswer(int QuestionaireID){

    }

    /**
     * This method is used for choices of Questionaires by participant.
     *<p> when this method is called,a required questionaire will be chosen.<br>
     *     firstly a Questionaire's number will be inputted,than the number will be looked up in the list of questionaires
     *     if the questionaire'number exists in the list of questionaires,then the questionaire will be chosen and return true.
     *     it the questionaire'number doesn't exist,then return false
     *
     * @param QuestionaireID the number of a Questionaire in the Questionaire's list.
     * @return true if the required questionaire exists and will be chosen,false if the required questionaire doesn't exist
     */
    public boolean chooseQuestionaire(int QuestionaireID){

    }

    /**
     * With this method a required Questionaire will be answered.
     *<p> when this method is called,a required questionaire will be chosen and it will also be answered. <br>
     *     firstly a Questionaire's number will be inputted,than the number will be looked up in the list of questionaires
     *     if the questionaire'number exists in the list of questionaires,then the questionaire will be chosen and return true.Now participant can answer this questionaire
     *     it the questionaire'number doesn't exist,then return false
     * @param QuestionaireID the number of a Questionaire in the Questionaire's list.
     * @return true if the required questionaire exists and will be chosen and answered,false if the required questionaire doesn't exist
     */
    public boolean answerQuestionaire(int QuestionaireID){

    }

    /**
     * (methode)
     *<p> (methode) <br>
     *     2
     * @param QuestionaireID
     * @param QuestionID
     * @return there is no return's value
     */
    public void answerQuestionaire(int QuestionaireID,int QuestionID){

    }



}
