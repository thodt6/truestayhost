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
import com.unibro.service.utils.AmenityService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing3_AmenitiesService extends AbstractListingService {

    private AmenityService amenityService = new AmenityService();

    public Listing3_AmenitiesService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
        JsonObject ret = Listing.updateHomeStayAmenities(this.getUser().getUsername(), getHomestay().getHomestay_id(), getHomestay().getAmenity_ids(), getHomestay().getSafety_amenity_ids());
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.getAmenityService().setHomestay_id(this.getHomestay().getHomestay_id());
            this.getAmenityService().loadObject();
            this.getHomestay().setAmenity_ids(this.getAmenityService().getSelectedOfferAmenitiesId());
            this.getHomestay().setSafety_amenity_ids(this.getAmenityService().getSelectedSafetyAmenitiesId());
            this.setPercentComplete(3 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/spaces.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/location.html");
    }
    
    @Override
    public String getBackpage() {
        return "location.html";
    }

    /**
     * @return the amenityService
     */
    public AmenityService getAmenityService() {
        return amenityService;
    }

    /**
     * @param amenityService the amenityService to set
     */
    public void setAmenityService(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

}
