package com.example.mindrate.gson;


import com.google.gson.annotations.SerializedName;

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

    @SerializedName("questionAnswer")
    private ArrayList<QuestionAnswer> questionAnswerList;

//    @Expose(serialize = false, deserialize = false)

    private transient Date finishTime;

    private Time submitTime;

    private String probandID;
    private boolean isValid;
    private HashMap<String,Float> sensorValues;


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
        this.isValid = true;
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

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }


    public String getProbandID() {
        return probandID;
    }

    public void setProbandID(String probandID) {
        this.probandID = probandID;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Time getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Time submitTime) {
        this.submitTime = submitTime;
    }
}
