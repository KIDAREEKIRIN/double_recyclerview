package com.example.double_recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetroDataStep implements Parcelable {

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

    protected RetroDataStep(Parcel in) {
        number = in.readInt();
        step = in.readString();
        tips = in.readInt();
        tips_content = in.readString();
        file = in.readInt();
        filepath = in.readString();
    }

    public static final Creator<RetroDataStep> CREATOR = new Creator<RetroDataStep>() {
        @Override
        public RetroDataStep createFromParcel(Parcel in) {
            return new RetroDataStep(in);
        }

        @Override
        public RetroDataStep[] newArray(int size) {
            return new RetroDataStep[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(step);
        dest.writeInt(tips);
        dest.writeString(tips_content);
        dest.writeInt(file);
        dest.writeString(filepath);
    }
}
