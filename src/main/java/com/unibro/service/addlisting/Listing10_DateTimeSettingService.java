/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.model.Homestay;
import com.unibro.model.User;
import com.unibro.service.utils.DatetimeSettingService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author THOND
 */
public class Listing10_DateTimeSettingService extends AbstractListingService {

    DatetimeSettingService dateTimeSettingService = new DatetimeSettingService();

    public Listing10_DateTimeSettingService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
        this.getHomestay().setStay_max(this.getHomestay().getStay_max_week() * 7);
        JsonObject ret = Listing.updateHomestayDateTime(this.getUser().getUsername(), this.getHomestay().getHomestay_id(), getHomestay().getBefore_time(), getHomestay().getFar_time(),
                getHomestay().getStay_max(), getHomestay().getStay_min(), this.getHomestay().getCheckin(), this.getHomestay().getCheckout(), this.getHomestay().getIs_longturn(), this.getHomestay().getIs_lastminute());
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
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
                this.dateTimeSettingService.setHomestay(this.getHomestay());
                this.dateTimeSettingService.loadObject();
                this.setPercentComplete(10 * 100 / this.num_step);
            }
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/calendar-intro.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/howto-book.html");
    }

    @Override
    public String getBackpage() {
        return "howto-book.html";
    }

}
