/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.tbl_hos_req_to_book;

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
public class BaseTBL_HOS_REQ_TO_BOOK implements Serializable {

    private String ID = "";
    private String HOMESTAYID = "";
    private String USERNAME = "";
    private String CHECKIN = "";
    private String CHECKOUT = "";
    private String NUM_ADULTS = "";
    private String NUM_CHILDREN = "";
    private String NUM_INFANTS = "";
    private String DATETIME = "";
    private String AMOUNT = "";
    private String CURRENCY = "";
    private String DESCRIPTION = "";
    private String STATE = "";
    private String LASTUPDATE = "";
    private String ALEPAY_TRANS_CODE = "";
    private String AMOUNT_AS_DAYS = "";
    private String CHECKIN_TIME = "";
    private String PRICE_PERNIGHT = "";
    private String SERVICE_FEES = "";
    private String RULE_FEES = "";
    private String WEEKEN_PRICING = "";
    private String TOTAL_AMOUNT = "";

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }

    public void setHOMESTAYID(String HOMESTAYID) {
        this.HOMESTAYID = HOMESTAYID;
    }

    public String getHOMESTAYID() {
        return this.HOMESTAYID;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getUSERNAME() {
        return this.USERNAME;
    }

    public void setCHECKIN(String CHECKIN) {
        this.CHECKIN = CHECKIN;
    }

    public String getCHECKIN() {
        return this.CHECKIN;
    }

    public void setCHECKOUT(String CHECKOUT) {
        this.CHECKOUT = CHECKOUT;
    }

    public String getCHECKOUT() {
        return this.CHECKOUT;
    }

    public void setNUM_ADULTS(String NUM_ADULTS) {
        this.NUM_ADULTS = NUM_ADULTS;
    }

    public String getNUM_ADULTS() {
        return this.NUM_ADULTS;
    }

    public void setNUM_CHILDREN(String NUM_CHILDREN) {
        this.NUM_CHILDREN = NUM_CHILDREN;
    }

    public String getNUM_CHILDREN() {
        return this.NUM_CHILDREN;
    }

    public void setNUM_INFANTS(String NUM_INFANTS) {
        this.NUM_INFANTS = NUM_INFANTS;
    }

    public String getNUM_INFANTS() {
        return this.NUM_INFANTS;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public String getDATETIME() {
        return this.DATETIME;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getAMOUNT() {
        return this.AMOUNT;
    }

    public void setCURRENCY(String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    public String getCURRENCY() {
        return this.CURRENCY;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getSTATE() {
        return this.STATE;
    }

    public void setLASTUPDATE(String LASTUPDATE) {
        this.LASTUPDATE = LASTUPDATE;
    }

    public String getLASTUPDATE() {
        return this.LASTUPDATE;
    }

    public void setALEPAY_TRANS_CODE(String ALEPAY_TRANS_CODE) {
        this.ALEPAY_TRANS_CODE = ALEPAY_TRANS_CODE;
    }

    public String getALEPAY_TRANS_CODE() {
        return this.ALEPAY_TRANS_CODE;
    }

    public void setAMOUNT_AS_DAYS(String AMOUNT_AS_DAYS) {
        this.AMOUNT_AS_DAYS = AMOUNT_AS_DAYS;
    }

    public String getAMOUNT_AS_DAYS() {
        return this.AMOUNT_AS_DAYS;
    }

    public void setCHECKIN_TIME(String CHECKIN_TIME) {
        this.CHECKIN_TIME = CHECKIN_TIME;
    }

    public String getCHECKIN_TIME() {
        return this.CHECKIN_TIME;
    }

    public void setPRICE_PERNIGHT(String PRICE_PERNIGHT) {
        this.PRICE_PERNIGHT = PRICE_PERNIGHT;
    }

    public String getPRICE_PERNIGHT() {
        return this.PRICE_PERNIGHT;
    }

    public void setSERVICE_FEES(String SERVICE_FEES) {
        this.SERVICE_FEES = SERVICE_FEES;
    }

    public String getSERVICE_FEES() {
        return this.SERVICE_FEES;
    }

    public void setRULE_FEES(String RULE_FEES) {
        this.RULE_FEES = RULE_FEES;
    }

    public String getRULE_FEES() {
        return this.RULE_FEES;
    }

    public void setWEEKEN_PRICING(String WEEKEN_PRICING) {
        this.WEEKEN_PRICING = WEEKEN_PRICING;
    }

    public String getWEEKEN_PRICING() {
        return this.WEEKEN_PRICING;
    }

    public void setTOTAL_AMOUNT(String TOTAL_AMOUNT) {
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }

    public String getTOTAL_AMOUNT() {
        return this.TOTAL_AMOUNT;
    }

    public String toJsonStr() {
        Gson gson = Global.getGsonObject();
        return gson.toJson(this);
    }

    public JsonObject toJson() {
        Gson gson = Global.getGsonObject();
        return gson.toJsonTree(this).getAsJsonObject();
    }

    public static String toJsonArrayString(List<TBL_HOS_REQ_TO_BOOK> list) {
        Gson gson = Global.getGsonObject();
        return gson.toJson(list);
    }

    public static JsonArray toJsonArray(List<TBL_HOS_REQ_TO_BOOK> list) {
        Gson gson = Global.getGsonObject();
        JsonElement element = gson.toJsonTree(list, new TypeToken<List<TBL_HOS_REQ_TO_BOOK>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        return jsonArray;
    }

    public static TBL_HOS_REQ_TO_BOOK getObjectFromJsonString(String jsonObj) {
        try {
            Gson gson = Global.getGsonObject();
            TBL_HOS_REQ_TO_BOOK obj;
            obj = gson.fromJson(jsonObj, TBL_HOS_REQ_TO_BOOK.class);
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
        if (!(obj instanceof BaseTBL_HOS_REQ_TO_BOOK)) {
            return false;
        }
        BaseTBL_HOS_REQ_TO_BOOK compareObj = (BaseTBL_HOS_REQ_TO_BOOK) obj;
        return (compareObj.getID().equals(this.getID()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.getID() != null ? this.getID().hashCode() : 0);
        return hash;
    }

}
