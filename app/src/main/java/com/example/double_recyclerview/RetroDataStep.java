package com.example.double_recyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetroDataStep {

    @Expose
    @SerializedName("number") private int number;

    @Expose
    @SerializedName("step") private String step;

    @Expose
    @SerializedName("step1") private String step1;

    @Expose
    @SerializedName("step2") private String step2;



    public RetroDataStep(int number, String step, String step1, String step2) {
        this.number = number;
        this.step = step;
        this.step1 = step1;
        this.step2 = step2;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }
}
