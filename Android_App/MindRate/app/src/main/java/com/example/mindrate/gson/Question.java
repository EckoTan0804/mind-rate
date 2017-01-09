package com.example.mindrate.gson;


/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:33
 */

public class Question<T> {

    private String question;
    private QuestionStrategy questionStrategy;
    private int questionID;
    private boolean isAnswered;

    public void setNextQuestion() {

    }

    // ================ setters and getters ==================================

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionStrategy getQuestionStrategy() {
        return questionStrategy;
    }

    public void setQuestionStrategy(QuestionStrategy questionStrategy) {
        this.questionStrategy = questionStrategy;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

}
