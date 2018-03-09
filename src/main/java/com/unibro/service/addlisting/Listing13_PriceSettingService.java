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
import com.unibro.service.utils.PriceSettingService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing13_PriceSettingService extends AbstractListingService {

    private PriceSettingService priceSettingService = new PriceSettingService(this.getUser());

    public Listing13_PriceSettingService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
        JsonObject ret = Listing.updateHomestayPrice(this.getUser().getUsername(), this.getHomestay().getHomestay_id(), getHomestay().getWas_price(), getHomestay().getLastminute_price(),
                getHomestay().getCurrency(), getHomestay().getLongturn_discount(), getHomestay().getWeekly_discount(), getHomestay().getMonthly_discount(), getHomestay().getList_extra_charges());
        return ret.get("data").getAsJsonObject().get("is_success").getAsString().equals("true");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.getPriceSettingService().setHomestay(this.getHomestay());
            this.getPriceSettingService().loadObject();
            this.setPercentComplete(13 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/tax.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/calendar.html");
    }
    
    @Override
    public String getBackpage() {
        return "calendar.html";
    }

    /**
     * @return the priceSettingService
     */
    public PriceSettingService getPriceSettingService() {
        return priceSettingService;
    }

    /**
     * @param priceSettingService the priceSettingService to set
     */
    public void setPriceSettingService(PriceSettingService priceSettingService) {
        this.priceSettingService = priceSettingService;
    }

}
