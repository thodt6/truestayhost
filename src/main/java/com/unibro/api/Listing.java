/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unibro.model.Amenity;
import com.unibro.model.ExtraCharge;
import com.unibro.model.GreatFor;
import com.unibro.model.GuestRequirement;
import com.unibro.model.Homestay;
import com.unibro.model.HomestayList;
import com.unibro.model.HouseRule;
import com.unibro.model.KnowAbout;
import com.unibro.model.SpaceUse;
import com.unibro.service.UserSessionBean;
import com.unibro.utils.Global;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
public class Listing {

    static final Logger logger = Logger.getLogger(Listing.class.getName());

    public static String getSecureCode() {
        return UserSessionBean.getUserSession().getSecurityCode();
    }

    public static JsonObject createHomeStay(String username, String propertytypeid, String roomtypeid) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/create_new_homestay";
        String[] params = {"username", "propertytypeid", "roomtypeid"};
        String[] values = {username, propertytypeid, roomtypeid};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStay(String username, String homestay_id, String propertytypeid, String roomtypeid) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_type";
        String[] params = {"username", "homestay_id", "propertytypeid", "roomtypeid"};
        String[] values = {username, homestay_id, propertytypeid, roomtypeid};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStayGuest(String username, String homestay_id, int adults_num, int children_num, int infants_num, int bath_room_num, int bed_room_num, int single_bed_num, int double_bed_num, int standard_guest_num, int max_guest_num) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_guests";
        String[] params = {"username", "homestay_id", "adults_num", "children_num", "infants_num", "bath_room_num", "bed_room_num", "single_bed_num", "double_bed_num", "standard_guest_num", "max_guest_num"};
        String[] values = {username, homestay_id, String.valueOf(adults_num), String.valueOf(children_num), String.valueOf(infants_num), String.valueOf(bath_room_num), String.valueOf(bed_room_num), String.valueOf(single_bed_num), String.valueOf(double_bed_num), String.valueOf(standard_guest_num), String.valueOf(max_guest_num)};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStayAddress(String username, String homestay_id, String latitude, String logitude, String address_full, String province_id, String district_id, String ward_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_address";
        String[] params = {"username", "homestay_id", "latitude", "logitude", "address_full", "province_id", "district_id", "ward_id"};
        String[] values = {username, homestay_id, latitude, logitude, address_full, province_id, district_id, ward_id};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStayTitleDesc(String username, String homestay_id, String title, String description, List<GreatFor> list_great_for, String language_code) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_title_desc";
        String[] params = {"username", "homestay_id", "title", "description", "list_great_for", "language_code"};

        String great_for_selected = "";
        for (GreatFor a : list_great_for) {
            great_for_selected += "|" + a.getId();
        }
        if (great_for_selected.length() > 0) {
            great_for_selected = great_for_selected.substring(1);
        }

        String[] values = {username, homestay_id, title, description, great_for_selected, language_code};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStayAmenities(String username, String homestay_id, List<Amenity> offer_amenities, List<Amenity> safety_amenities) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_amenities";
        String[] params = {"username", "homestay_id", "amenities_selected"};
        String amenities_selected = "";
        for (Amenity a : offer_amenities) {
            amenities_selected += "|" + a.getId();
        }

        for (Amenity a : safety_amenities) {
            amenities_selected += "|" + a.getId();
        }
        if (amenities_selected.length() > 0) {
            amenities_selected = amenities_selected.substring(1);
        }
        String[] values = {username, homestay_id, amenities_selected};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStaySpaceUse(String username, String homestay_id, List<SpaceUse> spaceuses) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_spacesuse";
        String[] params = {"username", "homestay_id", "spacesuse_selected"};
        String spaceuse_selected = "";
        for (SpaceUse a : spaceuses) {
            spaceuse_selected += "|" + a.getId();
        }

        if (spaceuse_selected.length() > 0) {
            spaceuse_selected = spaceuse_selected.substring(1);
        }
        String[] values = {username, homestay_id, spaceuse_selected};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStayImages(String username, String homestay_id, List<String> list_cover_url, List<String> list_images_url) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_images";
        String[] params = {"username", "homestay_id", "list_cover_url", "list_images_url"};
        String cover_url = "";
        String image_url = "";
        for (String a : list_cover_url) {
            cover_url += "|" + a;
        }

        for (String a : list_images_url) {
            image_url += "|" + a;
        }
        if (cover_url.length() > 0) {
            cover_url = cover_url.substring(1);
        }
        if (image_url.length() > 0) {
            image_url = image_url.substring(1);
        }
        String[] values = {username, homestay_id, cover_url, image_url};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStayHouseRule(String username, String homestay_id, List<HouseRule> hourse_rule_ids, List<KnowAbout> hourse_knowabout_ids) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_rules";
        String[] params = {"username", "homestay_id", "list_house_rules", "list_know_about"};
        String house_rule = "";
        String know_about = "";
        for (HouseRule a : hourse_rule_ids) {
            house_rule += "|" + a.getId();
        }

        for (KnowAbout a : hourse_knowabout_ids) {
            know_about += "|" + a.getId();
        }
        if (house_rule.length() > 0) {
            house_rule = house_rule.substring(1);
        }
        if (know_about.length() > 0) {
            know_about = know_about.substring(1);
        }
        String[] values = {username, homestay_id, house_rule, know_about};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStayGuestRequirement(String username, String homestay_id, List<GuestRequirement> guest_requirement_list) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_guest_requirement";
        String[] params = {"username", "homestay_id", "list_guest_req"};
        String guest_requirement = "";
        for (GuestRequirement a : guest_requirement_list) {
            guest_requirement += "|" + a;
        }

        if (guest_requirement.length() > 0) {
            guest_requirement = guest_requirement.substring(1);
        }
        String[] values = {username, homestay_id, guest_requirement};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static List<HomestayList> getHomestayList(String username, String state) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/get_homestay_your_listing";
        String[] params = {"username", "state"};
        String[] values = {username, state};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
//        ret=ret.replaceAll("null","\"\"");
//        logger.info(ret);
        if (ret != null) {
            Gson gson = Global.getGsonObject("yyyy/MM/dd HH:mm:ss");
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("homestay_your_listing").getAsJsonArray();
                Type listType = new TypeToken<List<HomestayList>>() {
                }.getType();
                List<HomestayList> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static JsonObject confirmHomestayContact(String trans_id, String otp_code) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/homestay_confirm_contact";
        String[] params = {"trans_id", "otp_code"};
        String[] values = {trans_id, otp_code};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomestayBookType(String username, String homestay_id, String book_type_id, String cancel_policy_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_booktype";
        String[] params = {"username", "homestay_id", "book_type_id", "cancel_policy_id"};
        String[] values = {username, homestay_id, book_type_id, cancel_policy_id};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomestayDateTime(String username, String homestay_id, int before_time, int far_time, int stay_max, int stay_min, Date checkin, Date checkout, Boolean is_longturn, Boolean is_lastminute) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_datetime_setting";
        String[] params = {"username", "homestay_id", "before_time", "far_time", "stay_max", "stay_min", "checkin", "checkout", "is_longturn", "is_lastminute"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkin);
        String checkin_min = String.valueOf(cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE));
        cal.setTime(checkout);
        String checkout_min = String.valueOf(cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE));
        String[] values = {username, homestay_id, String.valueOf(before_time), String.valueOf(far_time), String.valueOf(stay_max), String.valueOf(stay_min), checkin_min, checkout_min, String.valueOf(is_longturn), String.valueOf(is_lastminute)};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomestayCalendar(String username, String homestay_id, Date begindate, Date enddate, String state) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_calendar";
        String[] params = {"username", "homestay_id", "begindate", "enddate", "state"};
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String[] values = {username, homestay_id, f.format(begindate), f.format(enddate), state};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject getHomestayBlockCalendar(String username, String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/get_homestay_calendar_block";
        String[] params = {"username", "homestay_id"};
        String[] values = {username, homestay_id};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomestayPrice(String username, String homestay_id, Double was_price, Double lastminute_price, String currency, Integer longturn_discount, Integer weekly_discount, Integer monthly_discount, List<ExtraCharge> list_extra_charges) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_price_setting";
        String[] params = {"username", "homestay_id", "was_price", "lastminute_price", "currency", "longturn_discount", "weekly_discount", "monthly_discount", "list_extra_charges"};
        String list_extras = "";
        for (ExtraCharge extra : list_extra_charges) {
            list_extras += "|" + extra.getId() + "&" + extra.getAmount();
        }
        if (list_extras.length() > 0) {
            list_extras = list_extras.substring(1);
        }
        String[] values = {username, homestay_id, String.valueOf(was_price), String.valueOf(lastminute_price), String.valueOf(currency), String.valueOf(longturn_discount), String.valueOf(weekly_discount), String.valueOf(monthly_discount), list_extras};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static Homestay getHomestayDetail(String homestay_id, String language_code) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/get_homestay_listing_detail";
        String[] params = {"homestay_id", "language_code"};
        String[] values = {homestay_id, language_code};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject("yyyy/MM/dd hh:mm:ss");
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj != null && obj.get("message").getAsString().equals("200")) {
                JsonObject homestay = obj.get("data").getAsJsonObject().get("homestay_listing_detail").getAsJsonObject();
                Homestay ret_home = gson.fromJson(homestay, Homestay.class);
                if (ret_home.getAdults_num() == 0) {
                    ret_home.setAdults_num(1);
                }
                if (ret_home.getBed_room_num() == 0) {
                    ret_home.setBed_room_num(1);
                }
                if (!obj.get("data").getAsJsonObject().get("homestay_datetime_setting").isJsonNull()) {
                    JsonObject datetime_setting = obj.get("data").getAsJsonObject().get("homestay_datetime_setting").getAsJsonObject();
                    ret_home.setBefore_time(datetime_setting.get("before_time").getAsInt());
                    ret_home.setFar_time(datetime_setting.get("far_time").getAsInt());
                    ret_home.setStay_max(datetime_setting.get("stay_min").getAsInt());
                    ret_home.setBefore_time(datetime_setting.get("stay_max").getAsInt());
                    ret_home.setIs_longturn(datetime_setting.get("is_longturn").getAsBoolean());
                    ret_home.setIs_lastminute(datetime_setting.get("is_lastminute").getAsBoolean());
                }

                return ret_home;
            }
        }
        return null;
    }

    public static JsonObject getHomeStayDateTimeSetting(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/get_homestay_datetime_setting";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject getHomeStayPriceSetting(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/get_homestay_price_setting";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateHomeStayListingStep(String username, String homestay_id, Integer step_num, String step_name) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_listing_step";
        String[] params = {"username", "homestay_id", "step_num", "step_name"};
        String[] values = {username, homestay_id, String.valueOf(step_num), step_name};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject update_homestay_state(String token_key, String homestay_id, String new_state) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        String getSecureCode() = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_state";
        String[] params = {"token_key", "homestay_id", "new_state"};
        String[] values = {token_key, homestay_id, new_state};
        String ret = Global.getDataByPost(url, path, getSecureCode(), params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

}
