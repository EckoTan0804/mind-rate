package com.example.mindrate.gson;


/**
 * This class aims to model the proband who participate in the study
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: ecko0804@gmail.com</br>
 * <br>Created at 2017/1/8:23:32</br>
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Proband implements Parcelable {


    @SerializedName("probandID")
    private String probandID;

    @SerializedName("studyID")
    private String studyID;

    @SerializedName("gender")
    private String gender;

    private String birthday;

    @SerializedName("occupation")
    private String occupation;


    /**
     * Constructor
     *
     * @param studyID the id of the study the proband participates in
     * @param probandID id of the proband
     * @param birthday proband's birthday
     * @param gender proband's gender
     * @param occupation proband's occupation
     */
    public Proband(String studyID, String probandID,  String
            birthday,String gender, String occupation) {
        this.occupation = occupation;
        this.probandID = probandID;
        this.studyID = studyID;
        this.gender = gender;
        this.birthday = birthday;
    }

    /**
     * Constructor
     *
     * @param probandID proband's id
     * @param studyID the id of the study the proband participates in
     */
    public Proband(String probandID, String studyID) {
        this.probandID = probandID;
        this.studyID = studyID;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    // ================== Parcelable =============================================

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.probandID);
        dest.writeString(this.studyID);
        dest.writeString(this.gender);
        dest.writeString(this.birthday);
        dest.writeString(this.occupation);
    }

    protected Proband(Parcel in) {
        this.probandID = in.readString();
        this.studyID = in.readString();
        this.gender = in.readString();
        this.birthday = in.readString();
        this.occupation = in.readString();
    }

    public static final Creator<Proband> CREATOR = new Creator<Proband>() {
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
