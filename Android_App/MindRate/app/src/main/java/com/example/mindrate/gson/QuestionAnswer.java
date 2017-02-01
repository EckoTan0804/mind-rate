package com.example.mindrate.gson;


/**
 * Project: MindRate
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/1/30:13:09</br>
 * </p>
 */

public class QuestionAnswer {

    private String questionID;
    private String answerContent;

    public QuestionAnswer(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}
