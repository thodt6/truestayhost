/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.model.Homestay;
import com.unibro.service.utils.SpaceUsesService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing4_SpacesService extends AbstractListingService {

    private SpaceUsesService spaceUsesService = new SpaceUsesService();

    public Listing4_SpacesService(Homestay homestay, String username) {
        super(homestay, username);
    }
    
    @Override
    public boolean doService() {
        JsonObject ret = Listing.updateHomeStaySpaceUse(getUsername(), getHomestay().getHomestay_id(), getHomestay().getSpaceuse_ids());
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.getSpaceUsesService().setHomestay_id(this.getHomestay().getHomestay_id());
            this.getSpaceUsesService().loadObject();
            this.getHomestay().setSpaceuse_ids(this.spaceUsesService.getSelectedSpaceUses());
            this.setPercentComplete(4 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/photos.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/amenities.html");
    }

    /**
     * @return the spaceUsesService
     */
    public SpaceUsesService getSpaceUsesService() {
        return spaceUsesService;
    }

    /**
     * @param spaceUsesService the spaceUsesService to set
     */
    public void setSpaceUsesService(SpaceUsesService spaceUsesService) {
        this.spaceUsesService = spaceUsesService;
    }

}
