/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.tbl_ht_user;

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
public class BaseTBL_HT_USER implements Serializable {

    private String ID = "";
    private String EMAIL = "";
    private String FULLNAME = "";
    private String ADDRESS = "";
    private String DISTRICT_ID = "";
    private String PROVINCE_ID = "";
    private String PHONE_NUM = "";
    private String PASSWORD = "";
    private String TOKEN_KEY = "";
    private String IS_FB_LOGIN = "";
    private String REGISTER_DATE = "";
    private String USERNAME = "";
    private String GENDER = "";
    private String FB_AVATAR = "";
    private String LAST_REQUEST = "";
    private String COUNT_OPEN_APP = "";
    private String LAST_LOGIN = "";
    private String NTC_REGISTERID = "";
    private String DEVICE_TYPE = "";
    private String OS_VERSION = "";
    private String PAY_ACCT = "";
    private String BANK_NAME = "";
    private String BANK_AGENT_NAME = "";
    private String LANGUAGE_CODE = "";
    private String CURRENCY = "";
    private String DESCRIBE = "";
    private String BIRTHDAY = "";
    private String ID_CARD = "";
    private String PHOTO_1 = "";
    private String PHOTO_2 = "";
    private String VERIFIED = "";
    private String CARD_TYPE = "";
    private String COUNT_LOGIN = "";
    private String ACCT_NAME = "";
    private String TOKEN = "";
    private String COUNTRY_CODE = "";
    private String LAST_UPDATE = "";
    private String RESPONSE_PENDING = "";
    private String RESPONSE_READY = "";
    private String RESPONSE_TIME = "";

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getEMAIL() {
        return this.EMAIL;
    }

    public void setFULLNAME(String FULLNAME) {
        this.FULLNAME = FULLNAME;
    }

    public String getFULLNAME() {
        return this.FULLNAME;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getADDRESS() {
        return this.ADDRESS;
    }

    public void setDISTRICT_ID(String DISTRICT_ID) {
        this.DISTRICT_ID = DISTRICT_ID;
    }

    public String getDISTRICT_ID() {
        return this.DISTRICT_ID;
    }

    public void setPROVINCE_ID(String PROVINCE_ID) {
        this.PROVINCE_ID = PROVINCE_ID;
    }

    public String getPROVINCE_ID() {
        return this.PROVINCE_ID;
    }

    public void setPHONE_NUM(String PHONE_NUM) {
        this.PHONE_NUM = PHONE_NUM;
    }

    public String getPHONE_NUM() {
        return this.PHONE_NUM;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPASSWORD() {
        return this.PASSWORD;
    }

    public void setTOKEN_KEY(String TOKEN_KEY) {
        this.TOKEN_KEY = TOKEN_KEY;
    }

    public String getTOKEN_KEY() {
        return this.TOKEN_KEY;
    }

    public void setIS_FB_LOGIN(String IS_FB_LOGIN) {
        this.IS_FB_LOGIN = IS_FB_LOGIN;
    }

    public String getIS_FB_LOGIN() {
        return this.IS_FB_LOGIN;
    }

    public void setREGISTER_DATE(String REGISTER_DATE) {
        this.REGISTER_DATE = REGISTER_DATE;
    }

    public String getREGISTER_DATE() {
        return this.REGISTER_DATE;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getUSERNAME() {
        return this.USERNAME;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getGENDER() {
        return this.GENDER;
    }

    public void setFB_AVATAR(String FB_AVATAR) {
        this.FB_AVATAR = FB_AVATAR;
    }

    public String getFB_AVATAR() {
        return this.FB_AVATAR;
    }

    public void setLAST_REQUEST(String LAST_REQUEST) {
        this.LAST_REQUEST = LAST_REQUEST;
    }

    public String getLAST_REQUEST() {
        return this.LAST_REQUEST;
    }

    public void setCOUNT_OPEN_APP(String COUNT_OPEN_APP) {
        this.COUNT_OPEN_APP = COUNT_OPEN_APP;
    }

    public String getCOUNT_OPEN_APP() {
        return this.COUNT_OPEN_APP;
    }

    public void setLAST_LOGIN(String LAST_LOGIN) {
        this.LAST_LOGIN = LAST_LOGIN;
    }

    public String getLAST_LOGIN() {
        return this.LAST_LOGIN;
    }

    public void setNTC_REGISTERID(String NTC_REGISTERID) {
        this.NTC_REGISTERID = NTC_REGISTERID;
    }

    public String getNTC_REGISTERID() {
        return this.NTC_REGISTERID;
    }

    public void setDEVICE_TYPE(String DEVICE_TYPE) {
        this.DEVICE_TYPE = DEVICE_TYPE;
    }

    public String getDEVICE_TYPE() {
        return this.DEVICE_TYPE;
    }

    public void setOS_VERSION(String OS_VERSION) {
        this.OS_VERSION = OS_VERSION;
    }

    public String getOS_VERSION() {
        return this.OS_VERSION;
    }

    public void setPAY_ACCT(String PAY_ACCT) {
        this.PAY_ACCT = PAY_ACCT;
    }

    public String getPAY_ACCT() {
        return this.PAY_ACCT;
    }

    public void setBANK_NAME(String BANK_NAME) {
        this.BANK_NAME = BANK_NAME;
    }

    public String getBANK_NAME() {
        return this.BANK_NAME;
    }

    public void setBANK_AGENT_NAME(String BANK_AGENT_NAME) {
        this.BANK_AGENT_NAME = BANK_AGENT_NAME;
    }

    public String getBANK_AGENT_NAME() {
        return this.BANK_AGENT_NAME;
    }

    public void setLANGUAGE_CODE(String LANGUAGE_CODE) {
        this.LANGUAGE_CODE = LANGUAGE_CODE;
    }

    public String getLANGUAGE_CODE() {
        return this.LANGUAGE_CODE;
    }

    public void setCURRENCY(String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    public String getCURRENCY() {
        return this.CURRENCY;
    }

    public void setDESCRIBE(String DESCRIBE) {
        this.DESCRIBE = DESCRIBE;
    }

    public String getDESCRIBE() {
        return this.DESCRIBE;
    }

    public void setBIRTHDAY(String BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

    public String getBIRTHDAY() {
        return this.BIRTHDAY;
    }

    public void setID_CARD(String ID_CARD) {
        this.ID_CARD = ID_CARD;
    }

    public String getID_CARD() {
        return this.ID_CARD;
    }

    public void setPHOTO_1(String PHOTO_1) {
        this.PHOTO_1 = PHOTO_1;
    }

    public String getPHOTO_1() {
        return this.PHOTO_1;
    }

    public void setPHOTO_2(String PHOTO_2) {
        this.PHOTO_2 = PHOTO_2;
    }

    public String getPHOTO_2() {
        return this.PHOTO_2;
    }

    public void setVERIFIED(String VERIFIED) {
        this.VERIFIED = VERIFIED;
    }

    public String getVERIFIED() {
        return this.VERIFIED;
    }

    public void setCARD_TYPE(String CARD_TYPE) {
        this.CARD_TYPE = CARD_TYPE;
    }

    public String getCARD_TYPE() {
        return this.CARD_TYPE;
    }

    public void setCOUNT_LOGIN(String COUNT_LOGIN) {
        this.COUNT_LOGIN = COUNT_LOGIN;
    }

    public String getCOUNT_LOGIN() {
        return this.COUNT_LOGIN;
    }

    public void setACCT_NAME(String ACCT_NAME) {
        this.ACCT_NAME = ACCT_NAME;
    }

    public String getACCT_NAME() {
        return this.ACCT_NAME;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public String getTOKEN() {
        return this.TOKEN;
    }

    public void setCOUNTRY_CODE(String COUNTRY_CODE) {
        this.COUNTRY_CODE = COUNTRY_CODE;
    }

    public String getCOUNTRY_CODE() {
        return this.COUNTRY_CODE;
    }

    public void setLAST_UPDATE(String LAST_UPDATE) {
        this.LAST_UPDATE = LAST_UPDATE;
    }

    public String getLAST_UPDATE() {
        return this.LAST_UPDATE;
    }

    public void setRESPONSE_PENDING(String RESPONSE_PENDING) {
        this.RESPONSE_PENDING = RESPONSE_PENDING;
    }

    public String getRESPONSE_PENDING() {
        return this.RESPONSE_PENDING;
    }

    public void setRESPONSE_READY(String RESPONSE_READY) {
        this.RESPONSE_READY = RESPONSE_READY;
    }

    public String getRESPONSE_READY() {
        return this.RESPONSE_READY;
    }

    public void setRESPONSE_TIME(String RESPONSE_TIME) {
        this.RESPONSE_TIME = RESPONSE_TIME;
    }

    public String getRESPONSE_TIME() {
        return this.RESPONSE_TIME;
    }

    public String toJsonStr() {
        Gson gson = Global.getGsonObject();
        return gson.toJson(this);
    }

    public JsonObject toJson() {
        Gson gson = Global.getGsonObject();
        return gson.toJsonTree(this).getAsJsonObject();
    }

    public static String toJsonArrayString(List<TBL_HT_USER> list) {
        Gson gson = Global.getGsonObject();
        return gson.toJson(list);
    }

    public static JsonArray toJsonArray(List<TBL_HT_USER> list) {
        Gson gson = Global.getGsonObject();
        JsonElement element = gson.toJsonTree(list, new TypeToken<List<TBL_HT_USER>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        return jsonArray;
    }

    public static TBL_HT_USER getObjectFromJsonString(String jsonObj) {
        try {
            Gson gson = Global.getGsonObject();
            TBL_HT_USER obj;
            obj = gson.fromJson(jsonObj, TBL_HT_USER.class);
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
        if (!(obj instanceof BaseTBL_HT_USER)) {
            return false;
        }
        BaseTBL_HT_USER compareObj = (BaseTBL_HT_USER) obj;
        return (compareObj.getID().equals(this.getID()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.getID() != null ? this.getID().hashCode() : 0);
        return hash;
    }

}
