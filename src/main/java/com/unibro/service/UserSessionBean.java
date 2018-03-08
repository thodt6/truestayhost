/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.unibro.api.Profile;
import com.unibro.api.Utils;
import com.unibro.model.User;
import com.unibro.model.VerifyIdCard;
import com.unibro.service.utils.HomestayListService;
import com.unibro.utils.ApiClient;
import com.unibro.utils.Global;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpSession;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Nguyen Duc Tho
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped

public class UserSessionBean implements Serializable {

    public static String LOGIN_SUCCESS = "0";
    public static String LOGIN_FAIL = "-1";

    private String otp_tran_id = "null";
    private String otp_code;
    /**
     * Creates a new instance of UserBean
     */
    private static Map<String, Object> countries;
    private String email;
    private String password;
    private Boolean logged = false;
    private User user;

    private String code;

    private String localCode = "vn";
    private java.util.Date lastlogindate;

    static final Logger log = Logger.getLogger(UserSessionBean.class.getName());

    private String verifyPassword = "";
    private String newPassword = "";

    private String uploadMethod = "1";
    private Boolean firstIdPicture = true;
    private String token_key = "";

    private String redirect_token = "";

    private HomestayListService homestay_list = new HomestayListService();

    public void loginByToken() {
        String result = Profile.login_by_token(this.redirect_token);
        this.doLogin(result);
    }

    public UserSessionBean() {
        this.setLocalCode("vn");
    }

    /**
     * @return the username
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the username to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the logged
     */
    public Boolean getLogged() {
        return logged;
    }

    /**
     * @param logged the logged to set
     */
    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public void doFacebookLogin() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(this.getFbLoginUrl());
        } catch (IOException ex) {
            log.error(ex);
        }
    }

    public void doGoogleLogin() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(this.getGGLoginUrl());
        } catch (IOException ex) {
            log.error(ex);
        }
    }

    private String getFbLoginUrl() {
        try {
            String url = Global.getConfigValue("FB.LOGIN_URL") + "?client_id=" + Global.getConfigValue("FB.APP_ID")
                    + "&redirect_uri=" + URLEncoder.encode(Global.getConfigValue("FB.REDIRECT_URL"))
                    + "&scope=" + URLEncoder.encode(Global.getConfigValue("FB.SCOPE"))
                    + "&response_type=code"
                    + "&cancel_url=" + URLEncoder.encode(Global.getConfigValue("FB.REDIRECT_URL")) + "?error_reason=user_denied&error=access_denied&error_description=the+user+denied+your+request";

            return url;
        } catch (Exception ex) {
            return null;
        }
    }

    private String getGGLoginUrl() {
        try {
            String url = Global.getConfigValue("GG.LOGIN_URL") + "?client_id=" + URLEncoder.encode(Global.getConfigValue("GG.CLIENT_ID"))
                    + "&redirect_uri=" + URLEncoder.encode(Global.getConfigValue("GG.REDIRECT_URL"))
                    + "&scope=" + URLEncoder.encode(Global.getConfigValue("GG.SCOPE"))
                    + "&response_type=code"
                    + "&approval_prompt=force";
            log.info("gg url:" + url);
            return url;
        } catch (Exception ex) {
            return null;
        }
    }

    public void initFacebookLoginSession() {
        log.info("FB code:" + getCode());
        ApiClient client = new ApiClient("https://graph.facebook.com/v2.11/oauth/access_token");

        List<NameValuePair> param = new ArrayList();
        NameValuePair p1 = new BasicNameValuePair("client_id", Global.getConfigValue("FB.APP_ID"));
        NameValuePair p2 = new BasicNameValuePair("redirect_uri", Global.getConfigValue("FB.REDIRECT_URL"));
        NameValuePair p3 = new BasicNameValuePair("client_secret", Global.getConfigValue("FB.APP_SECRET"));
        NameValuePair p4 = new BasicNameValuePair("code", getCode());

        param.add(p1);
        param.add(p2);
        param.add(p3);
        param.add(p4);

        JsonObject obj = client.executeGetQuery(param);
        if (obj != null) {
            String access_token = obj.get("access_token").getAsString();
//            long expires_in = obj.get("expires_in").getAsLong();
//            JsonObject user_profile = this.queryUserProfile(access_token);
//            log.info("user_profile:" + user_profile.toString());
            String login_result = Profile.signin_by_fb(access_token, "");
            this.doLogin(login_result);

        }
    }

    public void initGoogleLoginSession() {
        log.info("GG code:" + getCode());
        ApiClient client = new ApiClient("https://accounts.google.com/o/oauth2/token", "");

        List<NameValuePair> param = new ArrayList();
        NameValuePair p1 = new BasicNameValuePair("client_id", Global.getConfigValue("GG.CLIENT_ID"));
        NameValuePair p2 = new BasicNameValuePair("redirect_uri", Global.getConfigValue("GG.REDIRECT_URL"));
        NameValuePair p3 = new BasicNameValuePair("client_secret", Global.getConfigValue("GG.CLIENT_SECRET"));
        NameValuePair p4 = new BasicNameValuePair("code", getCode());
        NameValuePair p5 = new BasicNameValuePair("grant_type", "authorization_code");

        param.add(p1);
        param.add(p2);
        param.add(p3);
        param.add(p4);
        param.add(p5);

        JsonObject obj = client.executePostQuery(param);
        if (obj != null) {
            log.info("google access:" + obj.toString());
            String access_token = obj.get("access_token").getAsString();

            JsonObject user_profile = this.queryGoogleUserProfile(access_token);
            String gmail = user_profile.get("email").getAsString();
            String fullname = user_profile.get("name").getAsString();
            String gender = "";
            if (user_profile.has("gender")) {
                gender = user_profile.get("gender").getAsString();
            }
            String avatar = "";
            if (user_profile.has("picture")) {
                avatar = user_profile.get("picture").getAsString();
            }
            String login_result = Profile.signin_by_google(gmail, fullname, "", gender, avatar, "");
            this.doLogin(login_result);

        } else {
            log.info("null");
        }
    }

    private JsonObject queryGoogleUserProfile(String access_token) {
        String g = "https://www.googleapis.com/oauth2/v1/userinfo";
        ApiClient client = new ApiClient(g, "");
        List<NameValuePair> param = new ArrayList();
        NameValuePair p1 = new BasicNameValuePair("access_token", access_token);
        param.add(p1);
        JsonObject obj = client.executeDefaultGetQuery(param);
        if (obj != null) {
            log.info(obj.toString());
        } else {
            log.info("null");
        }
        return obj;
    }

    private void doLogin(String result_str) {
        if (result_str != null) {
            Gson gson = Global.getGsonObject("dd/MM/yyyy");
            JsonObject result = gson.fromJson(result_str, JsonObject.class);
            if (result.get("message").getAsString().equals("200")) {
                if (result.get("data").getAsJsonObject().get("result").getAsString().equals("0")) {
                    //Login success
                    this.user = new User(result.get("data").getAsJsonObject().get("user_profile").getAsJsonObject());
                    this.user.setPassword(password);
                    this.setToken_key(result.get("data").getAsJsonObject().get("token_key").getAsString());

//                    if (!this.user.getIs_become_host().equals("true")) {
//                        this.logged = false;
//                        this.user = null;
//                        //Play message here
//                        log.info("you are not an agent");
//                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Global.getResourceLanguage("page.login.invalid_host"), "");
//                        FacesContext.getCurrentInstance().addMessage(null, msg);
//                    } else {
//                        this.logged = true;
//                        this.homestay_list.loadObjects();
//                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.get("data").getAsJsonObject().get("content").getAsString(), "");
//                        FacesContext.getCurrentInstance().addMessage(null, msg);
//                        this.localCode = this.user.getLanguage_code();
//                        this.setLanguageByUserProfile();
//                        this.gotoHomePage();
//                    }
                    this.logged = true;
                    this.homestay_list.loadObjects();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.get("data").getAsJsonObject().get("content").getAsString(), "");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    this.localCode = this.user.getLanguage_code();
                    this.setLanguageByUserProfile();
                    this.gotoHomePage();
                } else {
                    this.logged = false;
                    this.user = null;
                    //Play message here
                    log.info("password is wrong");
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, result.get("data").getAsJsonObject().get("content").getAsString(), "");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } else {
                //Login fail
                this.logged = false;
                this.user = null;
                //Play message here
                log.info("system error");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, result.get("data").getAsJsonObject().get("content").getAsString(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                this.gotoPage("/login.html");
            }
        } else {
            this.logged = false;
            this.user = null;
            //Play message here
            log.info("system error");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.gotoPage("/login.html");
        }
    }

    public void doLogin() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionid = session.getId();
        String result_str = Profile.login(email, password, sessionid);
        this.doLogin(result_str);
    }

    public boolean login() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionid = session.getId();
        String result_str = Profile.login(email, password, sessionid);
        if (result_str != null) {
            Gson gson = Global.getGsonObject("dd/MM/yyyy");
            JsonObject result = gson.fromJson(result_str, JsonObject.class);
            if (result.get("message").getAsString().equals("200")) {
                if (result.get("data").getAsJsonObject().get("result").getAsString().equals("0")) {
                    //Login success
                    this.user = new User(result.get("data").getAsJsonObject().get("user_profile").getAsJsonObject());
                    this.user.setPassword(password);
                    this.logged = true;
                } else {
                    this.logged = false;
                    this.user = null;

                }
            } else {
                //Login fail
                this.logged = false;
                this.user = null;

            }
        } else {
            this.logged = false;
            this.user = null;
            //Play message here

        }
        return this.logged;
    }

    public void gotoHomePage() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/portal/dashboard.html");
        } catch (IOException ex) {

        }
    }

    private void gotoPage(String page) {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + page);
        } catch (IOException ex) {

        }
    }

    public void updatePassword() {
//        if (Global.MD5(this.getVerifyPassword()).equals(this.getAccount().getOauthPwd())) {
//            this.getAccount().setOauthPwd(Global.MD5(this.getNewPassword()));
//            AccountDAO dao = new AccountDAO();
//            if (dao.edit(this.getAccount())) {
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Global.getResourceLanguage("general.operationSuccess"), "");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            }
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Global.getResourceLanguage("obj.user.general.invalidpassword"), "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
    }

    public void verifyAccessPage() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (!this.logged) {
            try {
                this.setEmail("");
                this.password = "";
                this.setUser(null);
                context.redirect(context.getRequestContextPath() + "/access-denied.html");
            } catch (IOException ex) {

            }
        }
    }

    @SuppressWarnings("unused")
    public void doLogout() {
        if (this.logged) {
            try {
                log.info("do Log out");
                this.logged = false;
                this.setEmail("");
                this.password = "";
//                int index = this.getCookieUserIndex(user.getUserid());
//                if (index != -1) {
//                    this.getCookieUser().remove(index);
//                    CookieUser.saveCookieUser(this.getCookieUser());
//                }
                this.user = null;
                Global.loadConfig();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/login.html");

            } catch (IOException ex) {
                log.error(ex);
                //Logger.getLogger(homebean.class.getName()).log(Level.SEVERE, null, ex);
                //log.info(ex);
            }
        } else {
            log.info("not log");
        }
    }
//    public void doLogout(){
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        if (this.logged) {
//            try {
//                this.logged=false;
//                this.username="";
//                this.password="";
//                this.user=null;
//                Map<String,Object> props = new HashMap<String, Object>();  
//                props.put("maxAge", 0);  
//                FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("remember", "", props); // the cookie I want to overwrite (expire)  
//                FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("userName", "", props); // random dummy cookie to see whether any is inserted
//                FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("userPassword", "", props); // random dummy cookie to see whether any is inserted
//                context.redirect(context.getRequestContextPath());
//            } catch (IOException ex) {
//                //Logger.getLogger(homebean.class.getName()).log(Level.SEVERE, null, ex);
//                //log.info(ex);
//            }
//        }        
//    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public static UserSessionBean getUserSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            UserSessionBean user = (UserSessionBean) session.getAttribute("userSessionBean");
            //log.info("UserBean:" + user.toString());
            //log.info(user.getUser());
            return user;
        }
        return null;
    }

    public void countryLocaleCodeChanged(ValueChangeEvent e) {

        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {
                Locale local = new Locale((String) entry.getValue(), entry.getKey());
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale(local);

            }
        }
    }

    public String setVietnameaseLanguage() {
        Locale locale = new Locale("vn");
        this.localCode = "vn";
        this.user.setLanguage_code(localCode.toUpperCase());
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        return null;
    }

    public void setLanguageByUserProfile() {
        this.localCode = this.user.getLanguage_code().toLowerCase();
        Locale locale = new Locale(this.localCode);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String setEnglishLanguage() {
        Locale locale = new Locale("en");
        this.localCode = "en";
        this.user.setLanguage_code(localCode.toUpperCase());
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        return null;
    }

    public void setLanguage() {
        Locale locale = new Locale(this.getLocalCode());
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String setLanguage(String lang) {
        log.info("Set Language");
        Locale locale = new Locale(lang);
        this.localCode = lang;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        return null;
    }

    /**
     * @return the localCode
     */
    public String getLocalCode() {
        return localCode;
    }

    /**
     * @param localCode the localCode to set
     */
    public final void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    /**
     * @return the lastlogindate
     */
    public java.util.Date getLastlogindate() {
        return lastlogindate;
    }

    /**
     * @param lastlogindate the lastlogindate to set
     */
    public void setLastlogindate(java.util.Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getFileHttpPath() {
        return Global.FILE_HTTP_PATH;
    }

    /**
     * @return the verifyPassword
     */
    public String getVerifyPassword() {
        return verifyPassword;
    }

    /**
     * @param verifyPassword the verifyPassword to set
     */
    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean updateProfileWithOtp() {
        JsonObject ret = Profile.updateUserProfile(user.getUsername(), user.getFullname(), user.getEmail(), user.getPhone_num(), user.getGender(), user.getBirthdayStr(), user.getLanguage_code(),
                user.getCurrency(), user.getAddress(), user.getDescribe(), user.getId_card(), user.getCard_type_id(), user.getPhoto_1(), user.getPhoto_2(),
                user.getPay_acct(), user.getAcct_name(), user.getAgent_name(), user.getBank_name(), "true");
        if (ret.get("message").getAsString().equals("200")) {
            this.setOtp_tran_id(ret.get("data").getAsJsonObject().get("trans_id").getAsString());
            return true;
        }
        return false;
//        if (acc_dao.edit(this.getAccount())) {
//            if (dao.edit(this.getUser())) {
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Global.getResourceLanguage("general.operationSuccess"), "");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            } else {
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Global.getResourceLanguage("general.operationFail"), "");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            }
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Global.getResourceLanguage("general.operationFail"), "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
    }

    public boolean updateProfile() {
        JsonObject ret = Profile.updateUserProfile(user.getUsername(), user.getFullname(), user.getEmail(), user.getPhone_num(), user.getGender(), user.getBirthdayStr(), user.getLanguage_code(),
                user.getCurrency(), user.getAddress(), user.getDescribe(), user.getId_card(), user.getCard_type_id(), user.getPhoto_1(), user.getPhoto_2(),
                user.getPay_acct(), user.getAcct_name(), user.getAgent_name(), user.getBank_name(), "false");
//        if (acc_dao.edit(this.getAccount())) {
//            if (dao.edit(this.getUser())) {
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Global.getResourceLanguage("general.operationSuccess"), "");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            } else {
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Global.getResourceLanguage("general.operationFail"), "");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            }
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Global.getResourceLanguage("general.operationFail"), "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
        return ret.get("message").getAsString().equals("200");
    }

    public boolean checkConfirmOtp() {
        JsonObject ret = Profile.confirmContact(this.getOtp_tran_id(), this.getOtp_code());
        if (ret.get("message").getAsString().equals("200")) {
            if (ret.get("data").getAsJsonObject().get("is_success").getAsString().equals("true")) {
                this.otp_code = "";
                this.otp_tran_id = "null";
                this.request_verified_email = true;
                this.user.setRequired_email(false);
                return true;
            }
        }
        return false;
    }

    public boolean checkConfirmSmsOtp() {
        JsonObject ret = Profile.confirmPhone(this.getOtp_tran_id(), this.getOtp_code());
        if (ret.get("message").getAsString().equals("200")) {
            if (ret.get("data").getAsJsonObject().get("is_success").getAsString().equals("true")) {
                this.otp_code = "";
                this.otp_tran_id = "null";
                this.request_verified_phone = true;
                this.user.setRequired_phone(false);
                return true;
            }
        }
        return false;
    }

    /**
     * @return the otp_tran_id
     */
    public String getOtp_tran_id() {
        return otp_tran_id;
    }

    /**
     * @param otp_tran_id the otp_tran_id to set
     */
    public void setOtp_tran_id(String otp_tran_id) {
        this.otp_tran_id = otp_tran_id;
    }

    /**
     * @return the otp_code
     */
    public String getOtp_code() {
        return otp_code;
    }

    /**
     * @param otp_code the otp_code to set
     */
    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }

    public void updateBasicProfile() {
        if (this.updateProfile()) {
            this.gotoPage("/portal/profile/update-your-profile/media.html");
        }
    }

    public void updateEmailAndPhone() {
//        if (this.updateProfileWithOtp()) {
        this.gotoPage("/portal/profile/update-your-profile/payout-info.html");
//        }
    }

    public void updatePayoutInfo() {
        if (this.updateProfile()) {
            this.gotoPage("/portal/profile/update-your-profile/basic.html");
        }
    }

    public void confirmSms() {
        if (this.checkConfirmSmsOtp()) {
            this.request_verified_phone = false;
            this.user.setRequired_phone(false);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Phone validation success", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            this.request_verified_phone = true;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid OTP", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void confirmEmail() {
        if (this.checkConfirmOtp()) {
            this.request_verified_email = false;
            this.user.setRequired_email(false);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email validation success", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            this.request_verified_email = true;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid OTP", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void confirmEmailAndPhone() {
        if (this.checkConfirmOtp()) {
            this.gotoPage("/portal/profile/update-your-profile/payout-info.html");
        } else {
            this.gotoPage("/portal/profile/update-your-profile/email-phone.html");
        }
    }

    public void updateMedia() {
        if (this.updateProfile()) {
            this.gotoPage("/portal/profile/update-your-profile/id.html");
        }
    }

    public void updateIDCard() {
        if (this.updateProfile()) {
            String message = "Update profile success. Please wait truestay verify your information";
            if (this.user.getVerified()) {
                message = "Update profile success. You can publish your homestay now";
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
//            this.gotoPage("/portal/dashboard.html");
        }
    }

    public void handleProfileUpload(FileUploadEvent event) {
        Global.loadConfig();
        UploadedFile f = event.getFile();
        String filename = f.getFileName();
//        String prefix = Global.getPrefixFileName(filename);
        String tail = Global.getTailFile(filename);
        filename = getUser().getId() + "." + tail;
        File folder = new File(Global.FILE_ROOT_PATH + "/" + getUser().getId() + "/profile");
        folder.mkdirs();
        File saveFile = new File(Global.FILE_ROOT_PATH + "/" + getUser().getId() + "/profile/" + filename);
        saveFile.delete();
        try {
            final int BUFFER_SIZE = 1024;
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = f.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
//            this.getHomestay().getHomestay_image_url().add(UserSessionBean.getUserSession().getUser().getId() + "/image/" + filename);
            this.user.setFb_avatar(getUser().getId() + "/profile/" + filename);
        } catch (IOException ex) {
        }
    }

    public void handleUploadIDCard(FileUploadEvent event) {
        Global.loadConfig();
        UploadedFile f = event.getFile();
        String filename = f.getFileName();
        String tail = Global.getTailFile(filename);
        if (this.firstIdPicture) {
            filename = "id1." + tail;
        } else {
            filename = "id2." + tail;
        }
        File folder = new File(Global.FILE_ROOT_PATH + "/" + getUser().getId() + "/profile");
        folder.mkdirs();
        File saveFile = new File(Global.FILE_ROOT_PATH + "/" + getUser().getId() + "/profile/" + filename);
        saveFile.delete();
        try {
            final int BUFFER_SIZE = 1024;
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = f.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
//            this.getHomestay().getHomestay_image_url().add(UserSessionBean.getUserSession().getUser().getId() + "/image/" + filename);
            if (this.firstIdPicture) {
                this.user.setPhoto_1(getUser().getId() + "/profile/" + filename);
            } else {
                this.user.setPhoto_2(getUser().getId() + "/profile/" + filename);
            }
            this.firstIdPicture = !this.firstIdPicture;
        } catch (IOException ex) {
        }
    }

    public void onCapture(CaptureEvent captureEvent) {
        File folder = new File(Global.FILE_ROOT_PATH + "/" + getUser().getId() + "/profile");
        folder.mkdirs();
        String filename = getUser().getId() + ".jpeg";
        String filepath = Global.FILE_ROOT_PATH + "/" + getUser().getId() + "/profile/" + filename;
        byte[] data = captureEvent.getData();

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(filepath));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
        this.user.setFb_avatar(getUser().getId() + "/profile/" + filename);
    }

    public void onCaptureIDPicture(CaptureEvent captureEvent) {
        File folder = new File(Global.FILE_ROOT_PATH + "/" + getUser().getId() + "/profile");
        folder.mkdirs();
        String filename;
        if (this.firstIdPicture) {
            filename = "id1.jpeg";
        } else {
            filename = "id2.jpeg";
        }
        String filepath = Global.FILE_ROOT_PATH + "/" + getUser().getId() + "/profile/" + filename;
        byte[] data = captureEvent.getData();

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(filepath));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            log.error(e);
        }
        if (this.firstIdPicture) {
            this.user.setPhoto_1(getUser().getId() + "/profile/" + filename);
        } else {
            this.user.setPhoto_2(getUser().getId() + "/profile/" + filename);
        }
        log.info("Capture :" + filename);
        this.firstIdPicture = !this.firstIdPicture;
    }

    /**
     * @return the uploadMethod
     */
    public String getUploadMethod() {
        return uploadMethod;
    }

    /**
     * @param uploadMethod the uploadMethod to set
     */
    public void setUploadMethod(String uploadMethod) {
        this.uploadMethod = uploadMethod;
    }

    public List<VerifyIdCard> getListVerifyIdCard() {
        return Utils.getVerifyIdCard(this.getUser().getUsername());
    }

    /**
     * @return the firstIdPicture
     */
    public Boolean getFirstIdPicture() {
        return firstIdPicture;
    }

    /**
     * @param firstIdPicture the firstIdPicture to set
     */
    public void setFirstIdPicture(Boolean firstIdPicture) {
        this.firstIdPicture = firstIdPicture;
    }

    /**
     * @return the token_key
     */
    public String getToken_key() {
        return token_key;
    }

    /**
     * @param token_key the token_key to set
     */
    public void setToken_key(String token_key) {
        this.token_key = token_key;
    }
    private boolean request_verified_email = false;
    private boolean request_verified_phone = false;

    public void verifyEmail() {
        this.request_verified_email = this.updateProfileWithOtp();
    }

    public void verifySms() {
        JsonObject ret = Profile.verifyPhone(this.getToken_key(), this.user.getPhone_num());
        if (ret.get("message").getAsString().equals("200")) {
            this.setOtp_tran_id(ret.get("data").getAsJsonObject().get("trans_id").getAsString());
            this.request_verified_phone = true;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Check phone number for OTP code", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            this.request_verified_phone = false;
        }
    }

    /**
     * @return the request_verified_email
     */
    public boolean isRequest_verified_email() {
        return request_verified_email;
    }

    /**
     * @param request_verified_email the request_verified_email to set
     */
    public void setRequest_verified_email(boolean request_verified_email) {
        this.request_verified_email = request_verified_email;
    }

    /**
     * @return the request_verified_phone
     */
    public boolean isRequest_verified_phone() {
        return request_verified_phone;
    }

    /**
     * @param request_verified_phone the request_verified_phone to set
     */
    public void setRequest_verified_phone(boolean request_verified_phone) {
        this.request_verified_phone = request_verified_phone;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the redirect_token
     */
    public String getRedirect_token() {
        return redirect_token;
    }

    /**
     * @param redirect_token the redirect_token to set
     */
    public void setRedirect_token(String redirect_token) {
        this.redirect_token = redirect_token;
    }

    /**
     * @return the homestay_list
     */
    public HomestayListService getHomestay_list() {
        return homestay_list;
    }

    /**
     * @param homestay_list the homestay_list to set
     */
    public void setHomestay_list(HomestayListService homestay_list) {
        this.homestay_list = homestay_list;
    }

    public String getSecurityCode() {
        if (this.user != null) {
            String currency = user.getCurrency().toLowerCase();
            String language = user.getLanguage_code().toLowerCase();
            if (currency == null || currency.equals("")) {
                currency = "default";
            }
            if (language == null || language.equals("")) {
                language = "default";
            }
            return Global.getConfigValue("APP_INTERNAL_WS_KEY").replace("language_default", "language_" + language).replace("currency_default", "currency_" + currency);

        }
        return Global.getConfigValue("APP_INTERNAL_WS_KEY");
    }

}
