/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.tbl_hos_trans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author THOND
 */
public class TBL_HOS_TRANS extends BaseTBL_HOS_TRANS implements Converter {

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
        TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
        TBL_HOS_TRANS ret = dao.getObjectByKey(id);
        return ret;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value.equals("")) {
            return "";
        }
        return String.valueOf(((TBL_HOS_TRANS) value).getID());
    }
}
