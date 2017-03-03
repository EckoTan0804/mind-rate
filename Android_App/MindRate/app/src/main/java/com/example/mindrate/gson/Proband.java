package com.example.mindrate.gson;


/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:32
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

/**
 * This class aims to model the participant of this study.
 */
public class Proband implements Parcelable {


    @SerializedName("probandID")
    private String probandID;

    @SerializedName("studyID")
    private String studyID;

    @SerializedName("gender")
    private String gender;

    private Birthday birthday;

    @SerializedName("occupation")
    private String occupation;

    @Expose
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

    public String getProbandID() {
        return probandID;
    }

    public void setProbandID(String probandID) {
        this.probandID = probandID;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Collection<Questionnaire> getQuestionaires() {
        return questionaires;
    }

    public void setQuestionaires(Collection<Questionnaire> questionaires) {
        this.questionaires = questionaires;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.probandID);
        dest.writeString(this.studyID);
        dest.writeString(this.gender);
        dest.writeParcelable(this.birthday, flags);
        dest.writeString(this.occupation);
//        dest.writeParcelable(this.questionaires, flags);
    }

    protected Proband(Parcel in) {
        this.probandID = in.readString();
        this.studyID = in.readString();
        this.gender = in.readString();
        this.birthday = in.readParcelable(Birthday.class.getClassLoader());
        this.occupation = in.readString();
//        this.questionaires = in.readParcelable(Collection<Questionnaire>.class.getClassLoader());
    }

    public static final Parcelable.Creator<Proband> CREATOR = new Parcelable.Creator<Proband>() {
        @Override
        public Proband createFromParcel(Parcel source) {
            return new Proband(source);
        }

        @Override
        public Proband[] newArray(int size) {
            return new Proband[size];
        }
    };
}
