package com.example.mindrate.gson;


import java.util.ArrayList;
import java.util.Date;

/**
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

    public QuestionnaireAnswer(String questionnaireID) {
        this.questionnaireID = questionnaireID;
        this.questionAnswerList = new ArrayList<>();
    }

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
}
