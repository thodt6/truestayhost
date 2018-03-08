/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.unibro.model.Homestay;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
public abstract class AbstractListingService {

    private Homestay homestay;
    private int percentComplete = 0;
    private String username;
    final int num_step=14;

    Logger logger = Logger.getLogger(this.getClass().getName());

    public abstract boolean doService();

    public abstract void initService();

    public abstract void nextPage();

    public abstract void backPage();

    public void doUpdate(){
        if(!this.doService()){
            this.showMessage("Update fail",FacesMessage.SEVERITY_ERROR);
        }else{
            this.showMessage("Update success",FacesMessage.SEVERITY_INFO);
        }
    }
   
    
    public void saveAndExist() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/your-listing/index.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    public AbstractListingService() {

    }

    public AbstractListingService(Homestay homestay, String username) {
        this.homestay = homestay;
        this.username = username;
    }

    public void showMessage(String message, Severity type) {
        FacesMessage msg = new FacesMessage(type, message, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void redirectToPage(String uri) {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + uri);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    /**
     * @return the homestay
     */
    public Homestay getHomestay() {
        return homestay;
    }

    /**
     * @param homestay the homestay to set
     */
    public void setHomestay(Homestay homestay) {
        this.homestay = homestay;
    }

    /**
     * @return the percentComplete
     */
    public int getPercentComplete() {
        return percentComplete;
    }

    /**
     * @param percentComplete the percentComplete to set
     */
    public void setPercentComplete(int percentComplete) {
        this.percentComplete = percentComplete;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void publishHomeStay(){
        if(this.getHomestay().publishHomeStay()){
            this.showMessage("Your home stay is successfully to sell", FacesMessage.SEVERITY_INFO);
        }else{
            this.showMessage("Error occured. Try again", FacesMessage.SEVERITY_ERROR);
        }
    }
}
