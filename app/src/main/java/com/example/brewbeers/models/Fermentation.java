package com.example.brewbeers.models;

import com.example.brewbeers.models.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Fermentation {

    @SerializedName("temp")
    @Expose
    private Temp_ temp;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Fermentation() {
    }

    /**
     * 
     * @param temp
     */
    public Fermentation(Temp_ temp) {
        super();
        this.temp = temp;
    }

    public Temp_ getTemp() {
        return temp;
    }

    public void setTemp(Temp_ temp) {
        this.temp = temp;
    }

}
