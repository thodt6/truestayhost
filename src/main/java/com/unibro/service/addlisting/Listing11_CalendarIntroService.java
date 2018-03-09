/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.unibro.model.Homestay;
import com.unibro.model.User;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing11_CalendarIntroService extends AbstractListingService {

    private Boolean understandCalendar = false;

    public Listing11_CalendarIntroService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
        return this.understandCalendar;
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.understandCalendar = false;
            this.setPercentComplete(11 * 100 / this.num_step);
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/calendar.html");
        } else {
            this.showMessage("Please read to understand calendar tool", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/stay-setting.html");
    }
    
    @Override
    public String getBackpage() {
        return "stay-setting.html";
    }

    /**
     * @return the understandCalendar
     */
    public Boolean getUnderstandCalendar() {
        return understandCalendar;
    }

    /**
     * @param understandCalendar the understandCalendar to set
     */
    public void setUnderstandCalendar(Boolean understandCalendar) {
        this.understandCalendar = understandCalendar;
    }

}
