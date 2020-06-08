package com.jlu.jtee.domain;

import java.io.Serializable;

public class Option implements Serializable {
    Object num;
    Object answer;

    public Object getNum() {
        return num;
    }

    public void setNum(Object num) {
        this.num = num;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public Option(Object num, Object answer) {
        this.num = num;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Option{" +
                "num=" + num +
                ", answer=" + answer +
                '}';
    }
}
