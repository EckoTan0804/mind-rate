package com.example.mindrate.gson;

/**
 * Created by Renhan on 2017/1/9.
 */

public abstract class QuestionType<T> {

    public T answer;

    public T getAnswer() {
        return answer;
    }

    public void setAnswer(T answer) {
        this.answer = answer;
    }
}
