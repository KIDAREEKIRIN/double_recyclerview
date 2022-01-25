package com.example.double_recyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroDataStep {

    @Expose
    @SerializedName("step") private String step;


    public RetroDataStep(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

}
