/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

/**
 *
 * @author THOND
 */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unibro.api.Utils;
import com.unibro.model.Amenity;
import com.unibro.utils.Global;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class AmenityService implements Serializable {

    private String homestay_id = "";
    private List<Amenity> offer_amenities;
    private List<Amenity> safety_amenities;

    public AmenityService() {
//        this.loadObject();
    }

    public final void loadObject() {
        Gson gson = Global.getGsonObject();
        JsonObject obj = Utils.getAmenities(homestay_id);
        if (obj.get("message").getAsString().equals("200")) {
            JsonElement data1 = obj.get("data").getAsJsonObject().get("offer_amenities").getAsJsonArray();
            Type listType = new TypeToken<List<Amenity>>() {
            }.getType();
            offer_amenities = gson.fromJson(data1, listType);
            
            JsonElement data2 = obj.get("data").getAsJsonObject().get("safety_amenities").getAsJsonArray();
            safety_amenities = gson.fromJson(data2, listType);
        } else {
            offer_amenities = new ArrayList();
            safety_amenities = new ArrayList();
        }        
    }

    /**
     * @return the homestay_id
     */
    public String getHomestay_id() {
        return homestay_id;
    }

    /**
     * @param homestay_id the homestay_id to set
     */
    public void setHomestay_id(String homestay_id) {
        this.homestay_id = homestay_id;
    }

    /**
     * @return the offer_amenities
     */
    public List<Amenity> getOffer_amenities() {
        return offer_amenities;
    }

    /**
     * @param offer_amenities the offer_amenities to set
     */
    public void setOffer_amenities(List<Amenity> offer_amenities) {
        this.offer_amenities = offer_amenities;
    }

    /**
     * @return the safety_amenities
     */
    public List<Amenity> getSafety_amenities() {
        return safety_amenities;
    }

    /**
     * @param safety_amenities the safety_amenities to set
     */
    public void setSafety_amenities(List<Amenity> safety_amenities) {
        this.safety_amenities = safety_amenities;
    }

    public List<String> getSelectedOfferAmenitiesId() {
        List<String> ret = new ArrayList();
        for (Amenity a : this.offer_amenities) {
            if (a.getSelected()) {
                ret.add(a.getId());
            }
        }
        return ret;
    }

    public List<String> getSelectedSafetyAmenitiesId() {
        List<String> ret = new ArrayList();
        for (Amenity a : this.safety_amenities) {
            if (a.getSelected()) {
                ret.add(a.getId());
            }
        }
        return ret;
    }

}
