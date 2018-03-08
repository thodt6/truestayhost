/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.model.Homestay;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing1_BedroomService extends AbstractListingService {

    public Listing1_BedroomService(Homestay homestay, String username) {
        super(homestay, username);
    }

    @Override
    public boolean doService() {
        this.getHomestay().setStandard_guest_num(this.getHomestay().getAdults_num() + this.getHomestay().getChildren_num() + this.getHomestay().getInfants_num());
        JsonObject ret = Listing.updateHomeStayGuest(this.getUsername(), this.getHomestay().getHomestay_id(),
                this.getHomestay().getAdults_num(), this.getHomestay().getChildren_num(), this.getHomestay().getInfants_num(), this.getHomestay().getBed_room_num(),
                this.getHomestay().getBath_room_num(), this.getHomestay().getSingle_bed_num(),this.getHomestay().getStandard_guest_num(),this.getHomestay().getMax_guest_num(),
                this.getHomestay().getDouble_bed_num());
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html?id=new-homestay");
        } else {
            this.setPercentComplete(1 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/location.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/room.html");
    }

}
