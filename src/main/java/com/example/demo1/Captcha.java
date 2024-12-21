package com.example.demo1;

public class Captcha {
    private String question ;
    private int ans;
    Captcha(String question, int ans){
        this.question = question;
        this.ans  = ans;
    }
    int getAns(){
        return ans;
    }

    public String getQuestion() {
        return question;
    }

//    public void setQuestion(String question) {
//        this.question = question;
//    }
}
