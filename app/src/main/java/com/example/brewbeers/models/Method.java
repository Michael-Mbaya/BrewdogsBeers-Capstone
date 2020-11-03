package com.example.brewbeers.models;

import java.util.List;
import com.example.brewbeers.models.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Method {

    @SerializedName("mash_temp")
    @Expose
    private List<MashTemp> mashTemp = null;
    @SerializedName("fermentation")
    @Expose
    private Fermentation fermentation;
    @SerializedName("twist")
    @Expose
    private Object twist;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Method() {
    }

    /**
     * 
     * @param mashTemp
     * @param fermentation
     * @param twist
     */
    public Method(List<MashTemp> mashTemp, Fermentation fermentation, Object twist) {
        super();
        this.mashTemp = mashTemp;
        this.fermentation = fermentation;
        this.twist = twist;
    }

    public List<MashTemp> getMashTemp() {
        return mashTemp;
    }

    public void setMashTemp(List<MashTemp> mashTemp) {
        this.mashTemp = mashTemp;
    }

    public Fermentation getFermentation() {
        return fermentation;
    }

    public void setFermentation(Fermentation fermentation) {
        this.fermentation = fermentation;
    }

    public Object getTwist() {
        return twist;
    }

    public void setTwist(Object twist) {
        this.twist = twist;
    }

}
