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
    @SerializedName("tips") private int tips;

    @Expose
    @SerializedName("tips_content") private String tips_content;

    @Expose
    @SerializedName("file") private int file;

    @Expose
    @SerializedName("filepath") private String filepath;




    public RetroDataStep(int number, String step, int tips, String tips_content, int file, String filepath) {
        this.number = number;
        this.step = step;
        this.tips = tips;
        this.tips_content = tips_content;
        this.file = file;
        this.filepath = filepath;
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

    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }

    public String getTips_content() {
        return tips_content;
    }

    public void setTips_content(String tips_content) {
        this.tips_content = tips_content;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
