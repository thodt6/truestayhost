/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.unibro.service.UserSessionBean;
import com.unibro.utils.Global;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
public class Profile {

    static final Logger logger = Logger.getLogger(Profile.class.getName());
    static String secure_code = UserSessionBean.getUserSession().getSecurityCode();

    public static String register(String email, String fullname, String password, String phone_num, String address, String gender) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
//        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/register";
        String[] params = {"email", "fullname", "password", "phone_num", "address", "gender"};
        String[] values = {email, fullname, password, phone_num, address, gender};
        return Global.getDataByPost(url, path, secure_code, params, values);
    }

    public static String login(String email, String password, String session) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/login";
        String[] params = {"email", "password", "session"};
        String[] values = {email, password, session};
        return Global.getDataByPost(url, path, secure_code, params, values);
    }

    public static String signin_by_fb(String token, String invite_code) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/signin_by_fb";
        String[] params = {"token", "invite_code"};
        String[] values = {token, invite_code};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        return ret;
    }

    public static String signin_by_google(String email, String fullname, String birthday, String gender, String avatar, String invite_code) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/signin_by_google";
        String[] params = {"email", "fullname", "birthday", "gender", "avatar", "invite_code"};
        String[] values = {email, fullname, birthday, gender, avatar, invite_code};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        return ret;
    }

    public static String login_by_token(String token) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/login_by_token";
        String[] params = {"token_key"};
        String[] values = {token};
        return Global.getDataByPost(url, path, secure_code, params, values);
    }

    public static JsonObject updateContact(String username, String homestay_id, String phone, String email, String address, String pay_acct, String acct_name, String bank_name, String agent_name) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "hosting/update_homestay_contact";
        phone = phone.replace("(", "");
        phone = phone.replace(")", "");
        String[] params = {"username", "homestay_id", "phone", "email", "address", "pay_acct", "acct_name", "bank_name", "agent_name"};
        String[] values = {username, homestay_id, phone, email, address, pay_acct, bank_name, acct_name, agent_name};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject updateUserProfile(String username, String full_name, String email, String phone, String gender,
            String birthday, String language_code, String currency, String address, String describe,
            String id_card, String card_type_id, String photo_1, String photo_2, String pay_acct,
            String acct_name, String agent_name, String bank_name, String verify_req) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/update_user_profile";
        phone = phone.replace("(", "");
        phone = phone.replace(")", "");
        String[] params = {"username", "fullname", "email", "phone", "gender", "birthday", "language_code", "currency",
            "address", "describe", "id_card", "card_type_id", "photo_1", "photo_2", "pay_acct", "acct_name",
            "agent_name", "bank_name", "verify_req"};
        String[] values = {username, full_name, email, phone, gender, birthday, language_code, currency,
            address, describe, id_card, card_type_id, photo_1, photo_2, pay_acct, acct_name,
            agent_name, bank_name, verify_req};

        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject confirmContact(String trans_id, String otp_code) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/confirm_update_user_profile";
        String[] params = {"trans_id", "otp_code"};
        String[] values = {trans_id, otp_code};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject verifyPhone(String token_key, String phone_num) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/verify_phone";
        String[] params = {"token_key", "phone_num"};
        String[] values = {token_key, phone_num};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject confirmPhone(String trans_id, String otp_code) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/confirm_verify_phone";
        String[] params = {"trans_id", "otp_code"};
        String[] values = {trans_id, otp_code};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

    public static JsonObject on_event(String username, String event_code, String event_desc, String preference) {
        String url = Global.getConfigValue("APP_INTERNAL_WS_URL");
        //String secure_code = UserSessionBean.getUserSession().getSecurityCode();
        String path = "profile/on_event";
        String[] params = {"username", "event_code", "event_desc", "preference"};
        String[] values = {username, event_code, event_desc, preference};
        String ret = Global.getDataByPost(url, path, secure_code, params, values);
        if (ret != null) {
            Gson gson = Global.getGsonObject();
            JsonObject obj = gson.fromJson(ret, JsonObject.class);
            return obj;
        }
        return null;
    }

}
