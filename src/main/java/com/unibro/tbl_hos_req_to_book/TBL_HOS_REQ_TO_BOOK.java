/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.tbl_hos_req_to_book;

import com.google.gson.JsonObject;
import com.unibro.api.Profile;
import com.unibro.currency_tab.CURRENCY_TAB;
import com.unibro.currency_tab.CURRENCY_TABService;
import com.unibro.model.HomestayList;
import com.unibro.service.UserSessionBean;
import com.unibro.tbl_ht_user.TBL_HT_USER;
import com.unibro.tbl_ht_user.TBL_HT_USERDAO;
import com.unibro.utils.Global;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author THOND
 */
public class TBL_HOS_REQ_TO_BOOK extends BaseTBL_HOS_REQ_TO_BOOK implements Converter {

    public Object getAsObject(FacesContext fc, UIComponent uic, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        }
        String id;
        try {
            id = String.valueOf(submittedValue);
        } catch (Exception ex) {
            id = null;
        }
        TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
        TBL_HOS_REQ_TO_BOOK ret = dao.getObjectByKey(id);
        return ret;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value.equals("")) {
            return "";
        }
        return String.valueOf(((TBL_HOS_REQ_TO_BOOK) value).getID());
    }

    public CURRENCY_TAB getCurrency() {
        CURRENCY_TABService service = CURRENCY_TABService.getCurrencyApplicationScoped();
        if (service != null) {
            return service.getCurrencyById(this.getCURRENCY());
        }
        return null;
    }

    public TBL_HT_USER getRequestUser() {
        TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
//        RequestFilter filter = new RequestFilter();
//        filter.setName("USERNAME");
//        filter.setRequired(true);
//        filter.setType(RequestFilter.EQUAL);
//        filter.setValue(this.getUSERNAME());
//        List filter_list = new ArrayList();
//        filter_list.add(filter);
//        List<TBL_HT_USER> list = dao.load(1, 0, "null", 0, filter_list);
//        if (list.size() > 0) {
//            return list.get(0);
//        }
//        return null;
        return dao.getObjectByUSERNAME(this.getUSERNAME());

    }

    private HomestayList homestay;

    /**
     * @return the homestay
     */
    public HomestayList getHomestay() {
        return homestay;
    }

    /**
     * @param homestay the homestay to set
     */
    public void setHomestay(HomestayList homestay) {
        this.homestay = homestay;
    }

    public Date getCheckInDate() {
        return Global.getDateFromString(this.getCHECKIN(), "yyyyMMdd");
    }

    public Date getCheckOutDate() {
        return Global.getDateFromString(this.getCHECKOUT(), "yyyyMMdd");
    }

    public boolean getEnableAccept() {
        return (this.getCheckInDate().after(new java.util.Date()) & this.getSTATE().equals("H"));
    }
    
    public void acceptRequest(){
        if(this.getSTATE().equals("H")){
            JsonObject obj=Profile.on_event(UserSessionBean.getUserSession().getUser().getUsername(), "105_ACCEPT_REQ_BOOK", "Accept booking", this.getID());            
        }
    }
    
    public void rejectRequest(){
        if(this.getSTATE().equals("H")){
            JsonObject obj=Profile.on_event(UserSessionBean.getUserSession().getUser().getUsername(), "105_REJECT_REQ_BOOK", "Reject booking", this.getID());            
        }
    }

}
