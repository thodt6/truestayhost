/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.model.Homestay;
import java.util.Calendar;

/**
 *
 * @author THOND
 */
public class DatetimeSettingService {

    private Homestay homestay;

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass().getName());

    public DatetimeSettingService() {

    }

    public void loadObject() {
        JsonObject obj = Listing.getHomeStayDateTimeSetting(homestay.getHomestay_id());
        if (obj.get("message").getAsString().equals("200")) {
            JsonElement date_time_setting = obj.get("data").getAsJsonObject().get("homestay_datetime_setting");
            if (date_time_setting != null && !date_time_setting.toString().equals("null")) {
                homestay.setBefore_time(date_time_setting.getAsJsonObject().get("before_time").getAsInt());
                homestay.setFar_time(date_time_setting.getAsJsonObject().get("far_time").getAsInt());
                homestay.setStay_min(date_time_setting.getAsJsonObject().get("stay_min").getAsInt());
                homestay.setStay_max(date_time_setting.getAsJsonObject().get("stay_max").getAsInt());
                homestay.setStay_max_week(homestay.getStay_max()/7);
                homestay.setIs_longturn(date_time_setting.getAsJsonObject().get("is_longturn").getAsBoolean());
                homestay.setIs_lastminute(date_time_setting.getAsJsonObject().get("is_lastminute").getAsBoolean());
                int checkin = date_time_setting.getAsJsonObject().get("checkin").getAsInt();
                int checkin_hour = checkin / 60;
                int checkin_min = checkin % 60;
                int checkout = date_time_setting.getAsJsonObject().get("checkout").getAsInt();
                int checkout_hour = checkout / 60;
                int checkout_min = checkout % 60;
                Calendar cal_checkin = Calendar.getInstance();
                cal_checkin.set(Calendar.HOUR_OF_DAY, checkin_hour);
                cal_checkin.set(Calendar.MINUTE, checkin_min);
                this.getHomestay().setCheckin(cal_checkin.getTime());
                Calendar cal_checkout = Calendar.getInstance();
                cal_checkout.set(Calendar.HOUR_OF_DAY, checkout_hour);
                cal_checkout.set(Calendar.MINUTE, checkout_min);
                this.getHomestay().setCheckout(cal_checkout.getTime());
            } else {
                Calendar cal_checkin = Calendar.getInstance();
                cal_checkin.set(Calendar.HOUR_OF_DAY, 14);
                cal_checkin.set(Calendar.MINUTE, 0);
                this.getHomestay().setCheckin(cal_checkin.getTime());
                Calendar cal_checkout = Calendar.getInstance();
                cal_checkout.set(Calendar.HOUR_OF_DAY, 12);
                cal_checkout.set(Calendar.MINUTE, 0);
                this.getHomestay().setCheckout(cal_checkout.getTime());
            }
        } else {
            Calendar cal_checkin = Calendar.getInstance();
            cal_checkin.set(Calendar.HOUR_OF_DAY, 14);
            cal_checkin.set(Calendar.MINUTE, 0);
            this.getHomestay().setCheckin(cal_checkin.getTime());
            Calendar cal_checkout = Calendar.getInstance();
            cal_checkout.set(Calendar.HOUR_OF_DAY, 12);
            cal_checkout.set(Calendar.MINUTE, 0);
            this.getHomestay().setCheckout(cal_checkout.getTime());
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
