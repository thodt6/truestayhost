/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.model.Homestay;
import com.unibro.service.UserSessionBean;
import com.unibro.service.utils.GreatForService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing6_DescriptionService extends AbstractListingService {

    private GreatForService greatForService;
    
    public Listing6_DescriptionService(Homestay homestay, String username) {
        super(homestay, username);
    }
    
    @Override
    public boolean doService() {
        JsonObject ret = Listing.updateHomeStayTitleDesc(this.getUsername(), getHomestay().getHomestay_id(), getHomestay().getTitle(), getHomestay().getDescription(), getHomestay().getGreat_for_ids(), UserSessionBean.getUserSession().getUser().getLanguage_code());
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            if(this.greatForService==null){
                this.greatForService=new GreatForService();
                this.greatForService.setHomestay_id(this.getHomestay().getHomestay_id());
                this.greatForService.loadObject();
                this.getHomestay().setGreat_for_ids(this.greatForService.getSelectedGreatFor());
            }
            this.setPercentComplete(6 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/house-rule.html");
//            if (!UserSessionBean.getUserSession().getUser().isEnoughRequiredInfo()) {
//                UserSessionBean.getUserSession().getUser().setPhone_num("84");
//                this.redirectToPage("/portal/listing/become-a-host/contact-info.html");
//            } else {
//                this.redirectToPage("/portal/listing/become-a-host/house-rule.html");
//            }
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/photos.html");
    }

    /**
     * @return the greatForService
     */
    public GreatForService getGreatForService() {
        return greatForService;
    }

    /**
     * @param greatForService the greatForService to set
     */
    public void setGreatForService(GreatForService greatForService) {
        this.greatForService = greatForService;
    }

}
