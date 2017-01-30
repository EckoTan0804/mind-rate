package com.example.mindrate.gson;


/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:33
 */

public class Question {

    private String question;
    private QuestionType questionType;
    private String questionID;
    private boolean isAnswered;

    public Question(String question, String questionID) {
        this.question = question;
        this.questionID = questionID;
        this.isAnswered = false;
    }

    // ================ setters and getters ==================================

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

}
