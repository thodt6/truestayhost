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
import com.unibro.service.utils.BookTypeService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author THOND
 */
public class Listing9_HowToBookService extends AbstractListingService {

    private BookTypeService bookTypeService = new BookTypeService();

    public Listing9_HowToBookService(Homestay homestay, User user) {
        super(homestay, user);
    }

    @Override
    public boolean doService() {
        this.getHomestay().setBook_type_id(this.getBookTypeService().getSelectedBookType().getId());
        JsonObject ret = Listing.updateHomestayBookType(this.getUser().getUsername(), this.getHomestay().getHomestay_id(), getHomestay().getBook_type_id(), getHomestay().getCancel_policy_id());
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        if (this.getHomestay().getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
                logger.info("Init service");
                this.getBookTypeService().setHomestay_id(this.getHomestay().getHomestay_id());
                this.getBookTypeService().loadObject();
                this.getHomestay().setCancel_policy_id(this.getBookTypeService().getCancelPolicyId());                
                this.setPercentComplete(9 * 100 / this.num_step);
            }
        }
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/stay-setting.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/guest-requirements.html");
    }
    
    @Override
    public String getBackpage() {
        return "guest-requirements.html";
    }

    /**
     * @return the bookTypeService
     */
    public BookTypeService getBookTypeService() {
        return bookTypeService;
    }

    /**
     * @param bookTypeService the bookTypeService to set
     */
    public void setBookTypeService(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }    
  

}
