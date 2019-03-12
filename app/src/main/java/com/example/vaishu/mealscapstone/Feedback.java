package com.example.vaishu.mealscapstone;

/**
 * Created by Vaishu on 13-01-2019.
 */

public class Feedback {
    String reviewid;
    String feedback;
    public Feedback()
    {
    }
    public Feedback(String reviewid, String feedback) {
        this.reviewid = reviewid;
        this.feedback = feedback;
    }
    public String getReviewid() {
        return reviewid;
    }
    public String getFeedback() {
        return feedback;
    }
}
