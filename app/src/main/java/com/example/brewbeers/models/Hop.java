package com.example.brewbeers.models;

import com.example.brewbeers.models.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Hop {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private Amount_ amount;
    @SerializedName("add")
    @Expose
    private String add;
    @SerializedName("attribute")
    @Expose
    private String attribute;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Hop() {
    }

    /**
     * 
     * @param add
     * @param amount
     * @param name
     * @param attribute
     */
    public Hop(String name, Amount_ amount, String add, String attribute) {
        super();
        this.name = name;
        this.amount = amount;
        this.add = add;
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount_ getAmount() {
        return amount;
    }

    public void setAmount(Amount_ amount) {
        this.amount = amount;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
