
package com.example.brewbeers.models;

import java.util.List;

import com.example.brewbeers.models.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class BeersModel {
    //
    private String pushId;
    //index
    String index;
    //
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("first_brewed")
    @Expose
    private String firstBrewed;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("abv")
    @Expose
    private Double abv;
    @SerializedName("ibu")
    @Expose
    private Double ibu;
    @SerializedName("target_fg")
    @Expose
    private Double targetFg;
    @SerializedName("target_og")
    @Expose
    private Double targetOg;
    @SerializedName("ebc")
    @Expose
    private Double ebc;
    @SerializedName("srm")
    @Expose
    private Double srm;
    @SerializedName("ph")
    @Expose
    private Double ph;
    @SerializedName("attenuation_level")
    @Expose
    private Double attenuationLevel;
    @SerializedName("volume")
    @Expose
    private Volume volume;
    @SerializedName("boil_volume")
    @Expose
    private BoilVolume boilVolume;
    @SerializedName("method")
    @Expose
    private Method method;
    @SerializedName("ingredients")
    @Expose
    private Ingredients ingredients;
    @SerializedName("food_pairing")
    @Expose
    private List<String> foodPairing = null;
    @SerializedName("brewers_tips")
    @Expose
    private String brewersTips;
    @SerializedName("contributed_by")
    @Expose
    private String contributedBy;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BeersModel() {
    }

    /**
     * 
     * @param contributedBy
     * @param targetOg
     * @param method
     * @param foodPairing
     * @param ebc
     * @param description
     * @param srm
     * @param attenuationLevel
     * @param volume
     * @param abv
     * @param imageUrl
     * @param name
     * @param ph
     * @param tagline
     * @param firstBrewed
     * @param ingredients
     * @param targetFg
     * @param brewersTips
     * @param id
     * @param ibu
     * @param boilVolume
     */
    public BeersModel(Integer id, String name, String tagline, String firstBrewed, String description, String imageUrl, Double abv, Double ibu, Double targetFg, Double targetOg, Double ebc, Double srm, Double ph, Double attenuationLevel, Volume volume, BoilVolume boilVolume, Method method, Ingredients ingredients, List<String> foodPairing, String brewersTips, String contributedBy) {
        super();
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.abv = abv;
        this.ibu = ibu;
        this.targetFg = targetFg;
        this.targetOg = targetOg;
        this.ebc = ebc;
        this.srm = srm;
        this.ph = ph;
        this.attenuationLevel = attenuationLevel;
        this.volume = volume;
        this.boilVolume = boilVolume;
        this.method = method;
        this.ingredients = ingredients;
        this.foodPairing = foodPairing;
        this.brewersTips = brewersTips;
        this.contributedBy = contributedBy;
        //
        this.index = "not_specified";
        //
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public Double getIbu() {
        return ibu;
    }

    public void setIbu(Double ibu) {
        this.ibu = ibu;
    }

    public Double getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(Double targetFg) {
        this.targetFg = targetFg;
    }

    public Double getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(Double targetOg) {
        this.targetOg = targetOg;
    }

    public Double getEbc() {
        return ebc;
    }

    public void setEbc(Double ebc) {
        this.ebc = ebc;
    }

    public Double getSrm() {
        return srm;
    }

    public void setSrm(Double srm) {
        this.srm = srm;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(Double attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public BoilVolume getBoilVolume() {
        return boilVolume;
    }

    public void setBoilVolume(BoilVolume boilVolume) {
        this.boilVolume = boilVolume;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(List<String> foodPairing) {
        this.foodPairing = foodPairing;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

}
