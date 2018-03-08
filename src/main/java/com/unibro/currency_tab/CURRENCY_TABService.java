/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.currency_tab;

import com.unibro.utils.Global;
import com.unibro.utils.RequestFilter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author THOND
 */
@SuppressWarnings("serial")
@ManagedBean(eager = true)
@ApplicationScoped
public class CURRENCY_TABService implements Converter {

    private List<CURRENCY_TAB> objects;
    private CURRENCY_TAB selectedObject;
    private CURRENCY_TAB newObject;

    private String selectedId;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public void initSelectedObject() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            //Add code for init object here
            CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
            this.selectedObject = dao.getObjectByKey(selectedId);
        }
    }

    @PostConstruct
    public void init() {
        logger.info("Load Object");
        this.loadObjects();
    }

    public final void loadObjects() {
        //Add code to load object here
        CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
        this.objects = dao.load(0, -1, "null", 0, new ArrayList());
        
    }

    public void createObject() {
        if (this.getNewObject() != null) {
            CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
            CURRENCY_TAB result = dao.create(getNewObject());
            if (result != null) {
                this.newObject = result;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Global.getResourceLanguage("general.operationSuccess"), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Global.getResourceLanguage("general.operationFail"), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void editSelected() {
        if (this.selectedObject != null) {
            CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
            if (dao.edit(this.selectedObject) != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Global.getResourceLanguage("general.operationSuccess"), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Global.getResourceLanguage("general.operationFail"), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void deleteObject() {
        if (this.selectedObject != null) {
            CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
            dao.delete(selectedObject);
            this.loadObjects();
        }
    }

    public void rowEdit(RowEditEvent event) {
        CURRENCY_TAB sf = (CURRENCY_TAB) event.getObject();
        if (sf != null) {
            this.setSelectedObject(sf);
            this.editSelected();
        }
    }

    public List<CURRENCY_TAB> completeObject(String query) {
        CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
        if (query == null || query.equals("")) {
            return dao.load(0, -1, "null", 0, new ArrayList());
        } else {
            RequestFilter filter = new RequestFilter();
            //replace name by the query field
            filter.setName("name");
            filter.setType(RequestFilter.CONTAIN);
            filter.setValue(query);
            List filter_list = new ArrayList();
            filter_list.add(filter);
            return dao.load(0, -1, "null", 0, filter_list);
        }
    }

    /**
     * @return the objects
     */
    public List<CURRENCY_TAB> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<CURRENCY_TAB> objects) {
        this.objects = objects;
    }

    /**
     * @return the selectedObject
     */
    public CURRENCY_TAB getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject the selectedObject to set
     */
    public void setSelectedObject(CURRENCY_TAB selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * @return the newObject
     */
    public CURRENCY_TAB getNewObject() {
        return newObject;
    }

    /**
     * @param newObject the newObject to set
     */
    public void setNewObject(CURRENCY_TAB newObject) {
        this.newObject = newObject;
    }

    /**
     * @return the selectedId
     */
    public String getSelectedId() {
        return selectedId;
    }

    /**
     * @param selectedId the selectedId to set
     */
    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
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
        for (CURRENCY_TAB c : this.objects) {
            if (c.getCODE_A3().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public CURRENCY_TAB getCurrencyById(String id) {
        for (CURRENCY_TAB c : this.objects) {
            if (c.getCODE_A3().equals(id)) {
                return c;
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
        return String.valueOf(((CURRENCY_TAB) value).getCODE_A3());
    }

    public static CURRENCY_TABService getCurrencyApplicationScoped() {
        CURRENCY_TABService bean;
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null) {
            ELContext elContext = fc.getELContext();
            bean = (CURRENCY_TABService) elContext.getELResolver().getValue(elContext, null, "cURRENCY_TABService");
            return bean;
        }
        return null;
    }
}
