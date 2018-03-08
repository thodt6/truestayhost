/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.model;

import com.google.gson.JsonObject;
import com.unibro.api.Utils;
import com.unibro.utils.Global;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
public class User {

    private String id;
    private String email;
    private String username;
    private String fullname;
    private String password;
    private String phone_num;
    private String address = "";
    private String gender = "male";
    private String fb_avatar = "";
    private String pay_acct;
    private String acct_name;
    private String bank_name;
    private String agent_name;
    private Date create_date;
    private String is_become_host="false";
    private String is_become_agent="false";
    private String is_verify_host="false";
    private String language_code;
    private String currency;
    private Date birthday;
    private String last_login;
    private String describe;
    private String id_card;
    private String card_type_id;
    private Integer count_login;
    private String photo_1;
    private String photo_2;
    private Boolean verified = false;
    private Boolean required_phone=true;
    private Boolean required_email=true;

    private String birthday_date;
    private String birthday_month;
    private String birthday_year;

    final Logger logger = Logger.getLogger(this.getClass().getName());

    public User(JsonObject result) {
        this.id = result.get("id").getAsString();
        this.email = result.get("email").getAsString();
        this.username = result.get("username").getAsString();
        this.fullname = result.get("fullname").getAsString();
        this.fb_avatar = result.get("fb_avatar").getAsString();
        this.create_date = Global.getDateFromString(result.get("create_date").getAsString(), "dd/MM/yyyy");
        this.last_login = result.get("last_login").getAsString();
        this.count_login = Integer.parseInt(result.get("count_login").getAsString());
        this.phone_num = result.get("phone_num").getAsString();
        this.pay_acct = result.get("pay_acct").getAsString();
        this.acct_name = result.get("acct_name").getAsString();
        this.bank_name = result.get("bank_name").getAsString();
        this.agent_name = result.get("agent_name").getAsString();
        this.language_code = result.get("language_code").getAsString();
        this.address = result.get("address").getAsString();
        this.currency = result.get("currency").getAsString();
        this.birthday = Global.getRealDateFromString(result.get("birthday").getAsString(), "yyyyMMdd");
        this.describe = result.get("describe").getAsString();
        this.id_card = result.get("id_card").getAsString();
        this.card_type_id = result.get("card_type_id").getAsString();
        this.photo_1 = result.get("photo_1").getAsString();
        this.photo_2 = result.get("photo_2").getAsString();
        this.verified = result.get("verified").getAsBoolean();
        this.required_phone = result.get("required_phone").getAsBoolean();
        this.required_email = result.get("required_email").getAsBoolean();
        this.is_become_agent = result.get("is_become_agent").getAsString();
        this.is_become_host = result.get("is_become_host").getAsString();
        this.is_verify_host = result.get("is_verify_host").getAsString();
        if (this.birthday != null) {
            logger.info(birthday.toString());
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.birthday);
            if (cal.get(Calendar.DATE) >= 10) {
                this.birthday_date = String.valueOf(cal.get(Calendar.DATE));
            } else {
                this.birthday_date = "0" + String.valueOf(cal.get(Calendar.DATE));
            }
            this.birthday_year = String.valueOf(cal.get(Calendar.YEAR));
            if (cal.get(Calendar.MONTH) >= 10) {
                this.birthday_month = String.valueOf(cal.get(Calendar.MONTH) + 1);
            } else {
                this.birthday_month = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
            }
        }
    }

    public boolean isEnoughVerifyInfo() {
        if (this.phone_num == null || this.phone_num.equals("")) {
            return false;
        }
        if (this.email == null || this.email.equals("")) {
            return false;
        }
        if (this.pay_acct == null || this.pay_acct.equals("")) {
            return false;
        }
        if (this.acct_name == null || this.acct_name.equals("")) {
            return false;
        }
        if (this.bank_name == null || this.bank_name.equals("")) {
            return false;
        }
        if (this.id_card == null || this.id_card.equals("")) {
            return false;
        }
        if (this.card_type_id == null || this.card_type_id.equals("")) {
            return false;
        }
        if (this.photo_1 == null || this.photo_1.equals("")) {
            return false;
        }
        if (this.photo_2 == null || this.photo_2.equals("")) {
            return false;
        }
        return !(this.agent_name == null || this.agent_name.equals(""));
    }

    public boolean isEnoughRequiredInfo() {
        if (this.phone_num == null || this.phone_num.equals("")) {
            return false;
        }
        if (this.email == null || this.email.equals("")) {
            return false;
        }
        if (this.pay_acct == null || this.pay_acct.equals("")) {
            return false;
        }
        if (this.acct_name == null || this.acct_name.equals("")) {
            return false;
        }
        if (this.bank_name == null || this.bank_name.equals("")) {
            return false;
        }
        return !(this.agent_name == null || this.agent_name.equals(""));
    }

    /**
     * @return the card_type_id
     */
    public String getCard_type_id() {
        return card_type_id;
    }

    /**
     * @param card_type_id the card_type_id to set
     */
    public void setCard_type_id(String card_type_id) {
        this.card_type_id = card_type_id;
    }

    /**
     * @return the count_login
     */
    public Integer getCount_login() {
        return count_login;
    }

    /**
     * @param count_login the count_login to set
     */
    public void setCount_login(Integer count_login) {
        this.count_login = count_login;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone_num
     */
    public String getPhone_num() {
        return Global.getPhoneNumber(this.phone_num);
    }

    /**
     * @param phone_num the phone_num to set
     */
    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the fb_avatar
     */
    public String getFb_avatar() {
        if(this.fb_avatar==null||this.fb_avatar.equals("")){
            return "http://media.truestay.biz/defaults/account.svg";
        }
        return this.fb_avatar;
    }

    /**
     * @return the fb_avatar
     */
    public String getFullURL_Photo1() {
        if (this.photo_1 != null && this.photo_1.startsWith("http")) {
            return photo_1;
        }
        Global.loadConfig();
        return Global.FILE_HTTP_PATH + "/" + this.getPhoto_1();
    }

    public String getFullURL_Photo2() {
        if (this.photo_2 != null && this.photo_2.startsWith("http")) {
            return photo_2;
        }
        Global.loadConfig();
        return Global.FILE_HTTP_PATH + "/" + this.getPhoto_2();
    }

    public String getFullURL_Fb_avatar() {
        if (this.fb_avatar != null && this.fb_avatar.startsWith("http")) {
            return fb_avatar;
        }
        Global.loadConfig();
        return Global.FILE_HTTP_PATH + "/" + this.getFb_avatar();
    }

    /**
     * @param fb_avatar the fb_avatar to set
     */
    public void setFb_avatar(String fb_avatar) {
        this.fb_avatar = fb_avatar;
    }

    /**
     * @return the bank_name
     */
    public String getBank_name() {
        return bank_name;
    }

    /**
     * @param bank_name the bank_name to set
     */
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    /**
     * @return the agent_name
     */
    public String getAgent_name() {
        return agent_name;
    }

    /**
     * @param agent_name the agent_name to set
     */
    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    /**
     * @return the create_date
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * @param create_date the create_date to set
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * @return the language_code
     */
    public String getLanguage_code() {
        return language_code;
    }

    /**
     * @param language_code the language_code to set
     */
    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe the describe to set
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @param id_card the id_card to set
     */
    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    /**
     * @return the photo_1
     */
    public String getPhoto_1() {
        return photo_1;
    }

    /**
     * @param photo_1 the photo_1 to set
     */
    public void setPhoto_1(String photo_1) {
        this.photo_1 = photo_1;
    }

    /**
     * @return the photo_2
     */
    public String getPhoto_2() {
        return photo_2;
    }

    /**
     * @param photo_2 the photo_2 to set
     */
    public void setPhoto_2(String photo_2) {
        this.photo_2 = photo_2;
    }

    /**
     * @return the verified
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     * @param verified the verified to set
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    /**
     * @return the payAcct
     */
    public String getPay_acct() {
        return pay_acct;
    }

    /**
     * @param pay_acct the payAcct to set
     */
    public void setPay_acct(String pay_acct) {
        this.pay_acct = pay_acct;
    }

    /**
     * @return the id_card
     */
    public String getId_card() {
        return id_card;
    }

    /**
     * @return the last_login
     */
    public String getLast_login() {
        return last_login;
    }

    /**
     * @param last_login the last_login to set
     */
    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    /**
     * @return the acct_name
     */
    public String getAcct_name() {
        return acct_name;
    }

    /**
     * @param acct_name the acct_name to set
     */
    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
    }

    /**
     * @return the list_currency
     */
    public List<Currency> getList_currency() {
        return Utils.getHomestayCurrency(this.getUsername());
    }

    public List<Language> getList_language() {
        return Utils.getHomestayLanguage(this.getUsername());
    }

    public List<String> getListDateBirday() {
        List ret = new ArrayList();
        for (int i = 0; i < 31; i++) {
            if (i < 9) {
                ret.add("0" + String.valueOf(i + 1));
            } else {
                ret.add(String.valueOf(i + 1));
            }
        }
        return ret;
    }

    public List<String> getListYearBirday() {
        List ret = new ArrayList();
        Calendar cal = Calendar.getInstance();
        int last = cal.get(Calendar.YEAR) - 17;
        for (int i = 1930; i <= last; i++) {
            ret.add(String.valueOf(i + 1));
        }
        return ret;
    }

    /**
     * @return the birthday_date
     */
    public String getBirthday_date() {
        return birthday_date;
    }

    /**
     * @param birthday_date the birthday_date to set
     */
    public void setBirthday_date(String birthday_date) {
        this.birthday_date = birthday_date;
    }

    /**
     * @return the birthday_month
     */
    public String getBirthday_month() {
        return birthday_month;
    }

    /**
     * @param birthday_month the birthday_month to set
     */
    public void setBirthday_month(String birthday_month) {
        this.birthday_month = birthday_month;
    }

    /**
     * @return the birthday_year
     */
    public String getBirthday_year() {
        return birthday_year;
    }

    /**
     * @param birthday_year the birthday_year to set
     */
    public void setBirthday_year(String birthday_year) {
        this.birthday_year = birthday_year;
    }

    public String getBirthdayStr() {
        String ret = "";
        if (this.getBirthday_year() != null && this.getBirthday_month() != null && this.getBirthday_date() != null) {
            ret = this.getBirthday_year() + this.getBirthday_month() + this.getBirthday_date();
        }
        return ret;
    }

    /**
     * @return the required_phone
     */
    public Boolean getRequired_phone() {
        return required_phone;
    }

    /**
     * @param required_phone the required_phone to set
     */
    public void setRequired_phone(Boolean required_phone) {
        this.required_phone = required_phone;
    }

    /**
     * @return the required_email
     */
    public Boolean getRequired_email() {
        return required_email;
    }

    /**
     * @param required_email the required_email to set
     */
    public void setRequired_email(Boolean required_email) {
        this.required_email = required_email;
    }

    /**
     * @return the is_become_host
     */
    public String getIs_become_host() {
        return is_become_host;
    }

    /**
     * @param is_become_host the is_become_host to set
     */
    public void setIs_become_host(String is_become_host) {
        this.is_become_host = is_become_host;
    }

    /**
     * @return the is_become_agent
     */
    public String getIs_become_agent() {
        return is_become_agent;
    }

    /**
     * @param is_become_agent the is_become_agent to set
     */
    public void setIs_become_agent(String is_become_agent) {
        this.is_become_agent = is_become_agent;
    }

    /**
     * @return the is_verify_host
     */
    public String getIs_verify_host() {
        return is_verify_host;
    }

    /**
     * @param is_verify_host the is_verify_host to set
     */
    public void setIs_verify_host(String is_verify_host) {
        this.is_verify_host = is_verify_host;
    }

}
