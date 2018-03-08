/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unibro.api.Utils;
import com.unibro.model.HouseRule;
import com.unibro.model.KnowAbout;
import com.unibro.utils.Global;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


/**
 *
 * @author THOND
 */
@SuppressWarnings("serial")
public class HouseRuleService {

    private String homestay_id = "";
    private List<HouseRule> house_rules;
    private List<KnowAbout> know_abouts;
    Logger logger = Logger.getLogger(this.getClass().getName());

    public final void loadObject() {
        Gson gson = Global.getGsonObject();
        JsonObject obj = Utils.getHouseRule(homestay_id);
        if (obj.get("message").getAsString().equals("200")) {
            JsonElement data1 = obj.get("data").getAsJsonObject().get("homestay_house_rules").getAsJsonArray();
            Type listType1 = new TypeToken<List<HouseRule>>() {
            }.getType();
            house_rules = gson.fromJson(data1, listType1);

            Type listType2 = new TypeToken<List<KnowAbout>>() {
            }.getType();

            JsonElement data2 = obj.get("data").getAsJsonObject().get("homestay_house_know_about").getAsJsonArray();
            know_abouts = gson.fromJson(data2, listType2);
        } else {
            house_rules = new ArrayList();
            know_abouts = new ArrayList();
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
     * @return the house_rules
     */
    public List<HouseRule> getHouse_rules() {
        return house_rules;
    }

    /**
     * @param house_rules the house_rules to set
     */
    public void setHouse_rules(List<HouseRule> house_rules) {
        this.house_rules = house_rules;
    }

    /**
     * @return the know_abouts
     */
    public List<KnowAbout> getKnow_abouts() {
        return know_abouts;
    }

    /**
     * @param know_abouts the know_abouts to set
     */
    public void setKnow_abouts(List<KnowAbout> know_abouts) {
        this.know_abouts = know_abouts;
    }

    public List<String> getSelectedHouseRuleIds() {
        if (this.house_rules == null || this.house_rules.isEmpty()) {
            return new ArrayList();
        }
        List<String> ret = new ArrayList();
        for (HouseRule rule : this.house_rules) {
            if (rule.getSelected()) {
                ret.add(rule.getId());
            }
        }
        return ret;
    }
    
    public List<String> getSelectedKnowAboutIds() {
        if (this.know_abouts == null || this.know_abouts.isEmpty()) {
            return new ArrayList();
        }
        List<String> ret = new ArrayList();
        for (KnowAbout about : this.know_abouts) {
            if (about.getSelected()) {
                ret.add(about.getId());
            }
        }
        return ret;
    }
}
