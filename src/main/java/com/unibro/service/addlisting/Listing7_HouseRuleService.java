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
import com.unibro.service.utils.HouseRuleService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing7_HouseRuleService extends AbstractListingService {

    private HouseRuleService houseRuleService = new HouseRuleService();

    public Listing7_HouseRuleService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
        this.getHomestay().setHomestay_house_rule_ids(this.houseRuleService.getSelectedHouseRuleIds());
        JsonObject ret = Listing.updateHomeStayHouseRule(this.getUser().getUsername(), this.getHomestay().getHomestay_id(), this.getHomestay().getHomestay_house_rule_ids(), this.getHomestay().getHomestay_knowabout_ids());
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.getHouseRuleService().setHomestay_id(this.getHomestay().getHomestay_id());
            this.getHouseRuleService().loadObject();
            this.getHomestay().setHomestay_house_rule_ids(this.getHouseRuleService().getSelectedHouseRuleIds());
            this.getHomestay().setHomestay_knowabout_ids(this.getHouseRuleService().getSelectedKnowAboutIds());
            this.setPercentComplete(7 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/guest-requirements.html");
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

    /**
     * @return the houseRuleService
     */
    public HouseRuleService getHouseRuleService() {
        return houseRuleService;
    }

    /**
     * @param houseRuleService the houseRuleService to set
     */
    public void setHouseRuleService(HouseRuleService houseRuleService) {
        this.houseRuleService = houseRuleService;
    }

}
