/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.google.gson.JsonObject;
import com.unibro.api.Profile;
import com.unibro.model.Homestay;
import com.unibro.service.UserSessionBean;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing8_ConfirmContactService extends AbstractListingService {

    public Listing8_ConfirmContactService(Homestay homestay, String username) {
        super(homestay, username);
    }

    private String otp_code;

    @Override
    public boolean doService() {
        JsonObject ret = Profile.confirmContact(UserSessionBean.getUserSession().getOtp_tran_id(), this.getOtp_code());
        if (ret.get("message").getAsString().equals("200")) {
            if (ret.get("data").getAsJsonObject().get("is_success").getAsString().equals("true")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.setPercentComplete(8 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/house-rule.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/contact-info.html");
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

}
