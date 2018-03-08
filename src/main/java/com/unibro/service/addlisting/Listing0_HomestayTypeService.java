/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.model.Homestay;
import com.unibro.service.utils.PropertyService;
import com.unibro.service.utils.RoomTypeService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing0_HomestayTypeService extends AbstractListingService {

    private RoomTypeService roomTypeService;
    private PropertyService propertyService;

    public Listing0_HomestayTypeService(){
        
    }
    
    public Listing0_HomestayTypeService(Homestay homestay, String username) {
        super(homestay, username);
    }

    @Override
    public boolean doService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            JsonObject ret = Listing.createHomeStay(this.getUsername(), this.getHomestay().getPropertytypeid(), this.getHomestay().getRoomtypeid());
            if (ret.get("message").getAsString().equals("200")) {
                this.getHomestay().setHomestay_id(ret.get("data").getAsJsonObject().get("homestay_id").getAsString());
                this.getHomestay().setIsNewHomeStay(false);
//                this.houseRuleService.loadObject();
//                this.redirectToPage("/portal/listing/become-a-host/bedroom.html");
                return true;
            } else {
                return false;
            }
        } else {
            JsonObject ret = Listing.updateHomeStay(this.getUsername(), this.getHomestay().getHomestay_id(), this.getHomestay().getPropertytypeid(), this.getHomestay().getRoomtypeid());
            if (ret.get("message").getAsString().equals("200")) {
                this.getHomestay().setHomestay_id(ret.get("data").getAsJsonObject().get("homestay_id").getAsString());
//                this.houseRuleService.loadObject();
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void initService() {
        this.setPercentComplete(0 * 100 / this.num_step);
        this.roomTypeService = new RoomTypeService();
        this.propertyService = new PropertyService();
        this.roomTypeService.loadObject();
        this.propertyService.loadObject();
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/bedroom.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {

    }

    /**
     * @return the roomTypeService
     */
    public RoomTypeService getRoomTypeService() {
        return roomTypeService;
    }

    /**
     * @param roomTypeService the roomTypeService to set
     */
    public void setRoomTypeService(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    /**
     * @return the propertyService
     */
    public PropertyService getPropertyService() {
        return propertyService;
    }

    /**
     * @param propertyService the propertyService to set
     */
    public void setPropertyService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

}
