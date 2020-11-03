package com.example.brewbeers.models;

import com.example.brewbeers.models.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoilVolume {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("unit")
    @Expose
    private String unit;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BoilVolume() {
    }

    /**
     * 
     * @param unit
     * @param value
     */
    public BoilVolume(Integer value, String unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
