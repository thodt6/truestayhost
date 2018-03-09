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
import com.unibro.service.utils.ProvinceService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing2_LocationService extends AbstractListingService {

    private ProvinceService provinceService = new ProvinceService();
    
    public Listing2_LocationService(Homestay homestay, User user) {
        super(homestay, user);
    }
    
    @Override
    public boolean doService() {
        JsonObject ret = Listing.updateHomeStayAddress(this.getUser().getUsername(), this.getHomestay().getHomestay_id(),
                String.valueOf(this.getHomestay().getLatitude()), String.valueOf(this.getHomestay().getLongitude()),
                String.valueOf(this.getHomestay().getAddress_full()), String.valueOf(this.getHomestay().getProvince_id()),
                String.valueOf(this.getHomestay().getDistrict_id()), String.valueOf(this.getHomestay().getWard_id()));
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.provinceService.setHomestay(this.getHomestay());
            this.provinceService.loadProvinces();
            this.provinceService.setProvinceid(this.getHomestay().getProvince_id());
            this.provinceService.loadDistricts();
            this.provinceService.setDistricid(this.getHomestay().getDistrict_id());
            this.provinceService.loadWards();
            this.provinceService.setWardid(this.getHomestay().getWard_id());            
            this.setPercentComplete(2 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/amenities.html");
        }else{
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/bedroom.html");
    }
    
    @Override
    public String getBackpage() {
        return "bedroom.html";
    }

    /**
     * @return the provinceService
     */
    public ProvinceService getProvinceService() {
        return provinceService;
    }

    /**
     * @param provinceService the provinceService to set
     */
    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

}
