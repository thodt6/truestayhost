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
import com.unibro.api.Listing;
import com.unibro.model.ExtraCharge;
import com.unibro.model.Homestay;
import com.unibro.service.UserSessionBean;
import com.unibro.utils.Global;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THOND
 */
public class PriceSettingService {

    private Homestay homestay;

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass().getName());

    public PriceSettingService() {

    }

    public List<ExtraCharge> getExtraChargeList(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_extra_charges";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("homestay_extra_charges");
                return getExtraChargeList(data);
            }
        }
        return new ArrayList();
    }

    public List<ExtraCharge> getExtraChargeList(JsonElement obj) {
        if (obj != null) {
            Gson gson = Global.getGsonObject();
            Type listType = new TypeToken<List<ExtraCharge>>() {
            }.getType();
            List<ExtraCharge> retval = gson.fromJson(obj, listType);
            return retval;
        }
        return new ArrayList();
    }

    public void loadObject() {
        JsonObject obj = Listing.getHomeStayPriceSetting(homestay.getHomestay_id());
        if (obj.get("message").getAsString().equals("200")) {
            JsonElement price_setting = obj.get("data").getAsJsonObject().get("homestay_price_setting");
            if (price_setting != null && !price_setting.toString().equals("null")) {
                homestay.setWas_price(price_setting.getAsJsonObject().get("wasprice").getAsDouble());
                homestay.setLastminute_price(price_setting.getAsJsonObject().get("lastminute_price").getAsDouble());
                homestay.setCurrency(price_setting.getAsJsonObject().get("currency").getAsString());
                homestay.setWeekly_discount(price_setting.getAsJsonObject().get("weekly_discount").getAsInt());
//                homestay.setMonthly_discount(price_setting.getAsJsonObject().get("monthly_discount").getAsInt());
                homestay.setLongturn_discount(price_setting.getAsJsonObject().get("longturn_discount").getAsInt());
                JsonElement list_extra_charge = price_setting.getAsJsonObject().get("list_extra_charges");
                if (list_extra_charge != null && !list_extra_charge.toString().equals("null")) {
                    homestay.setList_extra_charges(this.getExtraChargeList(list_extra_charge));
                } else {
                    homestay.setList_extra_charges(this.getExtraChargeList(this.homestay.getHomestay_id()));
                }
            } else {
                homestay.setList_extra_charges(this.getExtraChargeList(this.homestay.getHomestay_id()));
            }
        }
    }

    /**
     * @return the homestay
     */
    public final Homestay getHomestay() {
        return homestay;
    }

    /**
     * @param homestay the homestay to set
     */
    public void setHomestay(Homestay homestay) {
        this.homestay = homestay;

    }

    

}
