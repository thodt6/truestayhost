/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

/**
 *
 * @author THOND
 */
import com.unibro.api.Utils;
import com.unibro.model.SpaceUse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class SpaceUsesService implements Serializable,Converter {

    private String homestay_id = "";
    private List<SpaceUse> spaceuses;

    public SpaceUsesService() {
//        this.loadObject();
    }

    public final void loadObject() {
        this.spaceuses = Utils.getSpaceUse(homestay_id);
    }

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

    public List<SpaceUse> getSelectedSpaceUses() {
        List<SpaceUse> ret = new ArrayList();
        for (SpaceUse a : this.spaceuses) {
            if (a.getSelected()) {
                ret.add(a);
            }
        }
        return ret;
    }

    /**
     * @return the spaceuses
     */
    public List<SpaceUse> getSpaceuses() {
        return spaceuses;
    }

    /**
     * @param spaceuses the spaceuses to set
     */
    public void setSpaceuses(List<SpaceUse> spaceuses) {
        this.spaceuses = spaceuses;
    }
    
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
        for(SpaceUse s:this.spaceuses){
            if(s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }
    
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value.equals("")) {
            return "";
        }
        return String.valueOf(((SpaceUse) value).getId());
    }

}
