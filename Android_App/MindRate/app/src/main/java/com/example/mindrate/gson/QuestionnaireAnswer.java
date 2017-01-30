package com.example.mindrate.gson;


import java.util.ArrayList;
import java.util.List;

/**
 * Project: MindRate
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/1/30:13:10</br>
 * </p>
 */

public class QuestionnaireAnswer {

    private Questionnaire questionnaire;
    private List<QuestionAnswer> questionAnswerList;

    public QuestionnaireAnswer(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
        this.questionAnswerList = new ArrayList<>();
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public void addQuestionAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswerList.add(questionAnswer);
    }
}
