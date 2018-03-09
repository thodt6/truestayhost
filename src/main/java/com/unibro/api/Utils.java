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
import com.unibro.model.Currency;
import com.unibro.model.District;
import com.unibro.model.ExtraCharge;
import com.unibro.model.GreatFor;
import com.unibro.model.GuestRequirement;
import com.unibro.model.Language;
import com.unibro.model.Property;
import com.unibro.model.Province;
import com.unibro.model.RoomType;
import com.unibro.model.SpaceUse;
import com.unibro.model.VerifyIdCard;
import com.unibro.model.Ward;
import com.unibro.service.UserSessionBean;
import com.unibro.utils.Global;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
public class Utils {

    static final Logger logger = Logger.getLogger(Utils.class.getName());

    static String secure_code = UserSessionBean.getUserSession().getSecurityCode();

    public static List<Property> getPropertyType(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_property_type";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("homestay_property_type").getAsJsonArray();
                Type listType = new TypeToken<List<Property>>() {
                }.getType();
                List<Property> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<RoomType> getRoomType(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_room_type";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("homestay_room_type").getAsJsonArray();
                Type listType = new TypeToken<List<RoomType>>() {
                }.getType();
                List<RoomType> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<GreatFor> getGreatFor(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_great_for";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("homestay_great_for").getAsJsonArray();
                Type listType = new TypeToken<List<GreatFor>>() {
                }.getType();
                List<GreatFor> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static JsonObject getAmenities(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_amenities";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static List<Province> getProvinces(String get_value) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_list_provinces";
        String[] params = {"get_value"};
        String[] values = {get_value};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("list_provinces").getAsJsonArray();
                Type listType = new TypeToken<List<Province>>() {
                }.getType();
                List<Province> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<District> getDistricts(String province_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_list_districts";
        String[] params = {"province_id"};
        String[] values = {province_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("list_districts").getAsJsonArray();
                Type listType = new TypeToken<List<District>>() {
                }.getType();
                List<District> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<Ward> getWards(String district_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_list_wards";
        String[] params = {"district_id"};
        String[] values = {district_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("list_wards").getAsJsonArray();
                Type listType = new TypeToken<List<Ward>>() {
                }.getType();
                List<Ward> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<SpaceUse> getSpaceUse(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_spacesuse";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("homestay_spacesuse").getAsJsonArray();
                Type listType = new TypeToken<List<SpaceUse>>() {
                }.getType();
                List<SpaceUse> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<GuestRequirement> getGuestRequirementList(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_list_guest_requirements";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("list_guest_requirements").getAsJsonArray();
                Type listType = new TypeToken<List<GuestRequirement>>() {
                }.getType();
                List<GuestRequirement> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static JsonObject getHomeStayImages(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_images";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject getHouseRule(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_rules";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject getBookType(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_book_type";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static List<ExtraCharge> getExtraChargeList(String homestay_id) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_extra_charges";
        String[] params = {"homestay_id"};
        String[] values = {homestay_id};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("homestay_extra_charges").getAsJsonArray();
                Type listType = new TypeToken<List<ExtraCharge>>() {
                }.getType();
                List<ExtraCharge> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<Language> getHomestayLanguage(String username) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_languages";
        String[] params = {"username"};
        String[] values = {username};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("list_homestay_languages").getAsJsonArray();
                Type listType = new TypeToken<List<Language>>() {
                }.getType();
                List<Language> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<Currency> getHomestayCurrency(String username) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_currency";
        String[] params = {"username"};
        String[] values = {username};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("list_homestay_currency").getAsJsonArray();
                Type listType = new TypeToken<List<Currency>>() {
                }.getType();
                List<Currency> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }

    public static List<VerifyIdCard> getVerifyIdCard(String username) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "util/get_homestay_verify_card";
        String[] params = {"username"};
        String[] values = {username};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            if (obj.get("message").getAsString().equals("200")) {
                JsonElement data = obj.get("data").getAsJsonObject().get("list_homestay_vefiry_card").getAsJsonArray();
                Type listType = new TypeToken<List<VerifyIdCard>>() {
                }.getType();
                List<VerifyIdCard> retval = gson.fromJson(data, listType);
                return retval;
            }
        }
        return new ArrayList();
    }
 

}
