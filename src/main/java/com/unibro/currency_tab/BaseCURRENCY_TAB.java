/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.currency_tab;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.unibro.utils.Global;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author THOND
 */
public class BaseCURRENCY_TAB implements Serializable {

    private String CODE_N3 = "";
    private String EXCHANGE_RATE = "";
    private String COUNTRY_NAME = "";
    private String CODE_A3 = "";
    private String EXP = "";
    private String STATE = "";

    public void setCODE_N3(String CODE_N3) {
        this.CODE_N3 = CODE_N3;
    }

    public String getCODE_N3() {
        return this.CODE_N3;
    }

    public void setEXCHANGE_RATE(String EXCHANGE_RATE) {
        this.EXCHANGE_RATE = EXCHANGE_RATE;
    }

    public String getEXCHANGE_RATE() {
        return this.EXCHANGE_RATE;
    }

    public void setCOUNTRY_NAME(String COUNTRY_NAME) {
        this.COUNTRY_NAME = COUNTRY_NAME;
    }

    public String getCOUNTRY_NAME() {
        return this.COUNTRY_NAME;
    }

    public void setCODE_A3(String CODE_A3) {
        this.CODE_A3 = CODE_A3;
    }

    public String getCODE_A3() {
        return this.CODE_A3;
    }

    public void setEXP(String EXP) {
        this.EXP = EXP;
    }

    public String getEXP() {
        return this.EXP;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getSTATE() {
        return this.STATE;
    }

    public String toJsonStr() {
        Gson gson = Global.getGsonObject();
        return gson.toJson(this);
    }

    public JsonObject toJson() {
        Gson gson = Global.getGsonObject();
        return gson.toJsonTree(this).getAsJsonObject();
    }

    public static String toJsonArrayString(List<CURRENCY_TAB> list) {
        Gson gson = Global.getGsonObject();
        return gson.toJson(list);
    }

    public static JsonArray toJsonArray(List<CURRENCY_TAB> list) {
        Gson gson = Global.getGsonObject();
        JsonElement element = gson.toJsonTree(list, new TypeToken<List<CURRENCY_TAB>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        return jsonArray;
    }

    public static CURRENCY_TAB getObjectFromJsonString(String jsonObj) {
        try {
            Gson gson = Global.getGsonObject();
            CURRENCY_TAB obj;
            obj = gson.fromJson(jsonObj, CURRENCY_TAB.class);
            return obj;
        } catch (JsonSyntaxException ex) {
            //this.getLogger.error("Error:": + ex);
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseCURRENCY_TAB)) {
            return false;
        }
        BaseCURRENCY_TAB compareObj = (BaseCURRENCY_TAB) obj;
        return (compareObj.getCODE_A3().equals(this.getCODE_A3()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.getCODE_A3() != null ? this.getCODE_A3().hashCode() : 0);
        return hash;
    }

}
