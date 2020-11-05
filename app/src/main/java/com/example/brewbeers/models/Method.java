package com.example.brewbeers.models;

import java.util.List;
import com.example.brewbeers.models.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Method {

    @SerializedName("mash_temp")
    @Expose
    private List<MashTemp> mashTemp = null;
    @SerializedName("fermentation")
    @Expose
    private Fermentation fermentation;
    @SerializedName("twist")
    @Expose
    private String twist;

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
    public Method(List<MashTemp> mashTemp, Fermentation fermentation, String twist) {
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
        if(twist == null){
            return "Private Info";
        }else {
            return twist;
        }
    }

    public void setTwist(String twist) {
        this.twist = twist;
    }

}
