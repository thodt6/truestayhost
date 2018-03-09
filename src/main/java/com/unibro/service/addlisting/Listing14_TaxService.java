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
import com.unibro.service.UserSessionBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author THOND
 */
public class Listing14_TaxService extends AbstractListingService {

    private Boolean understandTax = false;

    public Listing14_TaxService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
        return this.understandTax;
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
                if (this.getHomestay().getStep_num() >= 14) {
                    this.understandTax = true;
                } else {
                    this.understandTax = false;
                }
                this.setPercentComplete(14 * 100 / this.num_step);
            }

        }
    }

    @Override
    public void nextPage() {
        if (this.getUnderstandTax()) {
            this.showMessage("Successfully listing", FacesMessage.SEVERITY_INFO);
            JsonObject obj = Listing.updateHomeStayListingStep(this.getUser().getUsername(), this.getHomestay().getHomestay_id(), this.num_step, "Listing_finished");
            if (obj.get("message").getAsString().equals("200")) {
                if (obj.get("data").getAsJsonObject().get("is_success").getAsString().equals("true")) {
                    this.setPercentComplete(100);
                } else {
                    this.showMessage("System error", FacesMessage.SEVERITY_ERROR);
                }
            } else {
                this.showMessage("System error", FacesMessage.SEVERITY_ERROR);
            }
            if(!UserSessionBean.getUserSession().getUser().getVerified()){
                this.getHomestay().publishHomeStay();
            }
        } else {
            this.showMessage("Please accept your tax requirement policy", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/price-setting.html");
    }
    
    @Override
    public String getBackpage() {
        return "price-setting.html";
    }

    /**
     * @return the understandCalendar
     */
    public Boolean getUnderstandTax() {
        return understandTax;
    }

    /**
     * @param understandCalendar the understandCalendar to set
     */
    public void setUnderstandTax(Boolean understandCalendar) {
        this.understandTax = understandCalendar;
    }

}
