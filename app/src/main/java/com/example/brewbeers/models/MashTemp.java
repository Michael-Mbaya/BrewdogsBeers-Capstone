package com.example.brewbeers.models;

import com.example.brewbeers.models.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class MashTemp {

    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("duration")
    @Expose
    private Integer duration;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MashTemp() {
    }

    /**
     * 
     * @param duration
     * @param temp
     */
    public MashTemp(Temp temp, Integer duration) {
        super();
        this.temp = temp;
        this.duration = duration;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
