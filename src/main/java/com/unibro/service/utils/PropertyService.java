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
import com.unibro.model.Property;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@SuppressWarnings("serial")

public class PropertyService implements Serializable, Converter {

    private List<Property> objects;

    public PropertyService() {
//        this.loadObject();
    }

    /**
     * @return the objects
     */
    public List<Property> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<Property> objects) {
        this.objects = objects;
    }

    public final void loadObject() {
        this.objects = Utils.getPropertyType("0000");
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
        for (Property p : objects) {
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
        return String.valueOf(((Property) value).getId());
    }
}
