package com.example.double_recyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroData2 {
    @Expose
    @SerializedName("title") private String title;

    @Expose
    @SerializedName("step") private String step;

    public RetroData2(String title, String step) {
        this.title = title;
        this.step = step;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}