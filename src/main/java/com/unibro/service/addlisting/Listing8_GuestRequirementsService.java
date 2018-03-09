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
import com.unibro.service.utils.GuestRequirementService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing8_GuestRequirementsService extends AbstractListingService {

    private GuestRequirementService guestRequirementService = new GuestRequirementService();

    public Listing8_GuestRequirementsService(Homestay homestay,User user){
        super(homestay,user);
    }
    
    @Override
    public boolean doService() {
        JsonObject ret = Listing.updateHomeStayGuestRequirement(this.getUser().getUsername(), this.getHomestay().getHomestay_id(), this.getHomestay().getGuest_requirement_ids());
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.getGuestRequirementService().setHomestay_id(this.getHomestay().getHomestay_id());
            this.getGuestRequirementService().loadObject();
            this.getHomestay().setGuest_requirement_ids(this.getGuestRequirementService().getSelectedId());
            this.setPercentComplete(8 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/howto-book.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/house-rule.html");
    }
    
    @Override
    public String getBackpage() {
        return "house-rule.html";
    }

    /**
     * @return the guestRequirementService
     */
    public GuestRequirementService getGuestRequirementService() {
        return guestRequirementService;
    }

    /**
     * @param guestRequirementService the guestRequirementService to set
     */
    public void setGuestRequirementService(GuestRequirementService guestRequirementService) {
        this.guestRequirementService = guestRequirementService;
    }

}
