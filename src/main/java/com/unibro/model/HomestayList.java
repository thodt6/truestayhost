/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.model;

import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.service.UserSessionBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author THOND
 */
public class HomestayList {

    public static final String PENDING_STATE = "P";
    public static final String PROCESSING_STATE = "C";
    public static final String ACTIVE_STATE = "A";
    public static final String REMOVED_STATE = "R";

    private String homestay_id;
    private String address;
    private String price;
    private String lastupdate;
    private String title;
    private String description;
    private String state;
    private String contact_phone;
    private List<Image> homestay_cover;
    private int step_num;
    private String step_name;

    /**
     * @return the homestay_id
     */
    public String getHomestay_id() {
        return homestay_id;
    }

    /**
     * @param homestay_id the homestay_id to set
     */
    public void setHomestay_id(String homestay_id) {
        this.homestay_id = homestay_id;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the lastupdate
     */
    public String getLastupdate() {
        return lastupdate;
    }

    /**
     * @param lastupdate the lastupdate to set
     */
    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the contact_phone
     */
    public String getContact_phone() {
        return contact_phone;
    }

    /**
     * @param contact_phone the contact_phone to set
     */
    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    /**
     * @return the homestay_cover
     */
    public List<Image> getHomestay_cover() {
        return homestay_cover;
    }

    /**
     * @param homestay_cover the homestay_cover to set
     */
    public void setHomestay_cover(List<Image> homestay_cover) {
        this.homestay_cover = homestay_cover;
    }

    public String getCoverImage() {
        if (this.homestay_cover != null && !this.homestay_cover.isEmpty()) {
            return this.homestay_cover.get(0).getImageurl();
        }
        return "";
    }

    public int getPercent() {
        return 100 * this.getStep_num()/ 14;
    }

    /**
     * @return the step_num
     */
    public int getStep_num() {
        return step_num;
    }

    /**
     * @param step_num the step_number to set
     */
    public void setStep_num(int step_num) {
        this.step_num = step_num;
    }

    /**
     * @return the step_name
     */
    public String getStep_name() {
        return step_name;
    }

    /**
     * @param step_name the step_name to set
     */
    public void setStep_name(String step_name) {
        this.step_name = step_name;
    }
    public boolean publishHomeStay() {
//        if (UserSessionBean.getUserSession().getUser().getVerified()) {
            JsonObject obj = Listing.update_homestay_state(UserSessionBean.getUserSession().getToken_key(), this.getHomestay_id(), "A");
            if (obj.get("message").getAsString().equals("200")) {
                return true;
            }
//        }
        return false;
    }
    public boolean unPublistHomeStay() {
//        if (UserSessionBean.getUserSession().getUser().getVerified()) {
            JsonObject obj = Listing.update_homestay_state(UserSessionBean.getUserSession().getToken_key(), this.getHomestay_id(), "P");
            if (obj.get("message").getAsString().equals("200")) {
                return true;
            }
//        }
        return false;
    }
    public void showMessage(String message, FacesMessage.Severity type) {
        FacesMessage msg = new FacesMessage(type, message, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void publishHome(){
        if(this.publishHomeStay()){
            this.showMessage("Your home stay is successfully to sell", FacesMessage.SEVERITY_INFO);
        }else{
            this.showMessage("Error occured. Try again", FacesMessage.SEVERITY_ERROR);
        }
    }
    
}
