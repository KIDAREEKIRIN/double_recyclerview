package com.example.double_recyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroData2 {
    @Expose
    @SerializedName("title") private String title;

    @Expose
    @SerializedName("step1") private String step1;

    @Expose
    @SerializedName("step2") private String step2;

    public RetroData2(String title, String step1, String step2) {
        this.title = title;
        this.step1 = step1;
        this.step2 = step2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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