package com.example.mindrate.gson;


import java.util.ArrayList;
import java.util.Date;

/**
 * This class aims to model a questionnaire's answer that consists of questions' answer
 *
 * Project: MindRate
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/1/30:13:10</br>
 * </p>
 */

public class QuestionnaireAnswer  {

    private String questionnaireID;
    private ArrayList<QuestionAnswer> questionAnswerList;
    private Date submitTime;
    private String submitTimeString;
    private String probandID;

    /**
     * Constructor
     *
     * @param questionnaireID questionnaire's id
     * @param probandID proband's id
     */
    public QuestionnaireAnswer(String questionnaireID, String probandID) {
        this.questionnaireID = questionnaireID;
        this.probandID = probandID;
        this.questionAnswerList = new ArrayList<>();
    }

    // ================= setters and getters ===============================================

    public String getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(String questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public ArrayList<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(ArrayList questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public void addQuestionAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswerList.add(questionAnswer);
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitTimeString() {
        return submitTimeString;
    }

    public void setSubmitTimeString(String submitTimeString) {
        this.submitTimeString = submitTimeString;
    }

    public String getProbandID() {
        return probandID;
    }

    public void setProbandID(String probandID) {
        this.probandID = probandID;
    }
}
