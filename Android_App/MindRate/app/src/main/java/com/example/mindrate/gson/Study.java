package com.example.mindrate.gson;


import java.util.List;

/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/20:17:02</br>
 * </p>
 */

public class Study {

    private List<Questionnaire> questionnaires;

    public Study(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }
}
