package com.example.projectotp;

public class feedbackModel {
    String Feedback;


    public feedbackModel (){

    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public feedbackModel(String feedback){
        this.Feedback = feedback;
    }
}
