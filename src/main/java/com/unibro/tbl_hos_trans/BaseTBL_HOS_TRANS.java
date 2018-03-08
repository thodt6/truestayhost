/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.tbl_hos_trans;

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
public class BaseTBL_HOS_TRANS implements Serializable {

    private String ID = "";
    private String BOOK_REQ_ID = "";
    private String TRANS_CODE = "";
    private String ORDER_CODE = "";
    private String AMOUNT = "";
    private String CURRENCY = "";
    private String BUYER_EMAIL = "";
    private String BUYER_PHONE = "";
    private String CARD_NUM = "";
    private String BUYER_NAME = "";
    private String STATE = "";
    private String MESSAGE = "";
    private String INSTALLMENT = "";
    private String BANK_CODE = "";
    private String BANK_NAME = "";
    private String CARD_METHOD = "";
    private String TRANS_TIME = "";
    private String SUCCESS_TIME = "";
    private String BANK_HOTLINE = "";
    private String USERNAME = "";
    private String TRANS_STATUS = "";
    private String USERID = "";
    private String MERCHANT_FEE = "";
    private String PAYER_FEE = "";

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }

    public void setBOOK_REQ_ID(String BOOK_REQ_ID) {
        this.BOOK_REQ_ID = BOOK_REQ_ID;
    }

    public String getBOOK_REQ_ID() {
        return this.BOOK_REQ_ID;
    }

    public void setTRANS_CODE(String TRANS_CODE) {
        this.TRANS_CODE = TRANS_CODE;
    }

    public String getTRANS_CODE() {
        return this.TRANS_CODE;
    }

    public void setORDER_CODE(String ORDER_CODE) {
        this.ORDER_CODE = ORDER_CODE;
    }

    public String getORDER_CODE() {
        return this.ORDER_CODE;
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

    public void setBUYER_EMAIL(String BUYER_EMAIL) {
        this.BUYER_EMAIL = BUYER_EMAIL;
    }

    public String getBUYER_EMAIL() {
        return this.BUYER_EMAIL;
    }

    public void setBUYER_PHONE(String BUYER_PHONE) {
        this.BUYER_PHONE = BUYER_PHONE;
    }

    public String getBUYER_PHONE() {
        return this.BUYER_PHONE;
    }

    public void setCARD_NUM(String CARD_NUM) {
        this.CARD_NUM = CARD_NUM;
    }

    public String getCARD_NUM() {
        return this.CARD_NUM;
    }

    public void setBUYER_NAME(String BUYER_NAME) {
        this.BUYER_NAME = BUYER_NAME;
    }

    public String getBUYER_NAME() {
        return this.BUYER_NAME;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getSTATE() {
        return this.STATE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getMESSAGE() {
        return this.MESSAGE;
    }

    public void setINSTALLMENT(String INSTALLMENT) {
        this.INSTALLMENT = INSTALLMENT;
    }

    public String getINSTALLMENT() {
        return this.INSTALLMENT;
    }

    public void setBANK_CODE(String BANK_CODE) {
        this.BANK_CODE = BANK_CODE;
    }

    public String getBANK_CODE() {
        return this.BANK_CODE;
    }

    public void setBANK_NAME(String BANK_NAME) {
        this.BANK_NAME = BANK_NAME;
    }

    public String getBANK_NAME() {
        return this.BANK_NAME;
    }

    public void setCARD_METHOD(String CARD_METHOD) {
        this.CARD_METHOD = CARD_METHOD;
    }

    public String getCARD_METHOD() {
        return this.CARD_METHOD;
    }

    public void setTRANS_TIME(String TRANS_TIME) {
        this.TRANS_TIME = TRANS_TIME;
    }

    public String getTRANS_TIME() {
        return this.TRANS_TIME;
    }

    public void setSUCCESS_TIME(String SUCCESS_TIME) {
        this.SUCCESS_TIME = SUCCESS_TIME;
    }

    public String getSUCCESS_TIME() {
        return this.SUCCESS_TIME;
    }

    public void setBANK_HOTLINE(String BANK_HOTLINE) {
        this.BANK_HOTLINE = BANK_HOTLINE;
    }

    public String getBANK_HOTLINE() {
        return this.BANK_HOTLINE;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getUSERNAME() {
        return this.USERNAME;
    }

    public void setTRANS_STATUS(String TRANS_STATUS) {
        this.TRANS_STATUS = TRANS_STATUS;
    }

    public String getTRANS_STATUS() {
        return this.TRANS_STATUS;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getUSERID() {
        return this.USERID;
    }

    public void setMERCHANT_FEE(String MERCHANT_FEE) {
        this.MERCHANT_FEE = MERCHANT_FEE;
    }

    public String getMERCHANT_FEE() {
        return this.MERCHANT_FEE;
    }

    public void setPAYER_FEE(String PAYER_FEE) {
        this.PAYER_FEE = PAYER_FEE;
    }

    public String getPAYER_FEE() {
        return this.PAYER_FEE;
    }

    public String toJsonStr() {
        Gson gson = Global.getGsonObject();
        return gson.toJson(this);
    }

    public JsonObject toJson() {
        Gson gson = Global.getGsonObject();
        return gson.toJsonTree(this).getAsJsonObject();
    }

    public static String toJsonArrayString(List<TBL_HOS_TRANS> list) {
        Gson gson = Global.getGsonObject();
        return gson.toJson(list);
    }

    public static JsonArray toJsonArray(List<TBL_HOS_TRANS> list) {
        Gson gson = Global.getGsonObject();
        JsonElement element = gson.toJsonTree(list, new TypeToken<List<TBL_HOS_TRANS>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        return jsonArray;
    }

    public static TBL_HOS_TRANS getObjectFromJsonString(String jsonObj) {
        try {
            Gson gson = Global.getGsonObject();
            TBL_HOS_TRANS obj;
            obj = gson.fromJson(jsonObj, TBL_HOS_TRANS.class);
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
        if (!(obj instanceof BaseTBL_HOS_TRANS)) {
            return false;
        }
        BaseTBL_HOS_TRANS compareObj = (BaseTBL_HOS_TRANS) obj;
        return (compareObj.getID().equals(this.getID()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.getID() != null ? this.getID().hashCode() : 0);
        return hash;
    }

}
