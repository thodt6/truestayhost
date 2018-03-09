/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.unibro.model.Homestay;
import com.unibro.model.User;
import com.unibro.service.utils.CalendarService;
import javax.faces.application.FacesMessage;

/**
 *
 * @author THOND
 */
public class Listing12_CalendarService extends AbstractListingService {

    private CalendarService calendarService = new CalendarService(this.getUser());

    public Listing12_CalendarService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
        return true;
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.getCalendarService().setHomestay_id(this.getHomestay().getHomestay_id());
            this.getCalendarService().loadObject();
            this.setPercentComplete(12 * 100 / this.num_step);
        }
    }
    

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/price-setting.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/calendar-intro.html");
    }
    
    @Override
    public String getBackpage() {
        return "calendar-intro.html";
    }


    /**
     * @return the calendarService
     */
    public CalendarService getCalendarService() {
        return calendarService;
    }

    /**
     * @param calendarService the calendarService to set
     */
    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    

}
