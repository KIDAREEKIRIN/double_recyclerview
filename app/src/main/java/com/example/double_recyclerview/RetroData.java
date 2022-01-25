package com.example.double_recyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetroData {

    @SerializedName("number") private int number;


    @SerializedName("title") private String title;


    @SerializedName("step1") private String step1;


    @SerializedName("step2") private String step2;

    private List<RetroDataStep> retroDataStepList;


    public RetroData(int number, String title,List<RetroDataStep> retroDataStepList) {
        this.number = number;
        this.title = title;
        this.retroDataStepList = retroDataStepList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RetroDataStep> getStepList() {
        return retroDataStepList;
    }

    public void setStepList(List<RetroDataStep> retroDataStepList) {
        this.retroDataStepList = retroDataStepList;
    }
}
