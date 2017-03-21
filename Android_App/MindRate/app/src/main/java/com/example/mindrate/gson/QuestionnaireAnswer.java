package com.example.mindrate.gson;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This class aims to model a questionnaire's answer that consists of questions' answer
 * <p>
 * <br>Project: MindRate</br>
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
    private HashMap<String,Float> sensorValues;





    //======================================



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
        this.sensorValues = new HashMap<>();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof QuestionnaireAnswer) {
            QuestionnaireAnswer questionnaireAnswer = (QuestionnaireAnswer) obj;

            // different questionAnswerList's size --> not equal
            if (this.questionAnswerList.size() != questionnaireAnswer.questionAnswerList.size()) {
                return false;
            } else {
                // compare every list item
                for (int i = 0; i < this.questionAnswerList.size(); i++) {
                    if (!this.questionAnswerList.get(i).equals(questionnaireAnswer
                                                                       .questionAnswerList.get(i))) {
                        // if an list element not equals --> not equals
                        return false;
                    }
                }
                // questionAnswerList equals, then compare questionnaireID and probandID
                return this.probandID.equals(questionnaireAnswer.probandID) && this
                        .questionnaireID.equals(questionnaireAnswer.questionnaireID);
            }
        } else {
            return false;
        }
    }

    // ================= setters and getters ===============================================
    public HashMap<String, Float> getSensorValues() {
        return sensorValues;
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

    public String getProbandID() {
        return probandID;
    }

    public void setProbandID(String probandID) {
        this.probandID = probandID;
    }
}
