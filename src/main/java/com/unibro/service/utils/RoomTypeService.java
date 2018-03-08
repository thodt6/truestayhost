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
import com.unibro.model.RoomType;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@SuppressWarnings("serial")


public class RoomTypeService implements Serializable, Converter {

    private List<RoomType> objects;

    public RoomTypeService(){
//        this.loadObject();
    }
    /**
     * @return the objects
     */
    public List<RoomType> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<RoomType> objects) {
        this.objects = objects;
    }

    public final void loadObject() {
        this.objects = Utils.getRoomType("00000");
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
        for (RoomType p : objects) {
            if (p.getId().equals(id)) {
                return p;
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
        return String.valueOf(((RoomType) value).getId());
    }
}
