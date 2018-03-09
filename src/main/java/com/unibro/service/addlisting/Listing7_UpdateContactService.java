/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.unibro.model.Homestay;
import com.unibro.model.User;
import com.unibro.service.UserSessionBean;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing7_UpdateContactService extends AbstractListingService {

    public Listing7_UpdateContactService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
//        JsonObject ret = Profile.updateContact(this.getUser().getUsername(), getHomestay().getHomestay_id(), UserSessionBean.getUserSession().getUser().getPhone_num(),
//                UserSessionBean.getUserSession().getEmail(), UserSessionBean.getUserSession().getUser().getAddress(), UserSessionBean.getUserSession().getUser().getPay_acct(),
//                UserSessionBean.getUserSession().getUser().getAcct_name(), UserSessionBean.getUserSession().getUser().getBank_name(), UserSessionBean.getUserSession().getUser().getAgent_name());
        User user=UserSessionBean.getUserSession().getUser();
        return UserSessionBean.getUserSession().updateProfileWithOtp();
//        if (ret.get("message").getAsString().equals("200")) {
//            UserSessionBean.getUserSession().setOtp_tran_id(ret.get("data").getAsJsonObject().get("trans_id").getAsString());
//            return true;
//        }
//        return false;
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.setPercentComplete(7 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/contact-confirm.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/description.html");
    }
    
    @Override
    public String getBackpage() {
        return "description.html";
    }

}
