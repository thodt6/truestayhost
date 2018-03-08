/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.tbl_ht_user;

import com.unibro.utils.Global;
import com.unibro.utils.RequestFilter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author THOND
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TBL_HT_USERService {

    private List<TBL_HT_USER> objects;
    private TBL_HT_USER selectedObject;
    private TBL_HT_USER newObject;

    private String selectedId;

    public void initSelectedObject() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            //Add code for init object here
            TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
            this.selectedObject = dao.getObjectByKey(selectedId);
        }
    }

    public final void loadObjects() {
        //Add code to load object here
//        TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
//        this.objects = dao.load(0, -1, "null", 0, new ArrayList());
    }

    public void createObject() {
        if (this.getNewObject() != null) {
            TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
            TBL_HT_USER result = dao.create(getNewObject());
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
            TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
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
            TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
            dao.delete(selectedObject);
            this.loadObjects();
        }
    }

    public void rowEdit(RowEditEvent event) {
        TBL_HT_USER sf = (TBL_HT_USER) event.getObject();
        if (sf != null) {
            this.setSelectedObject(sf);
            this.editSelected();
        }
    }

    public List<TBL_HT_USER> completeObject(String query) {
        TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
        if (query == null || query.equals("")) {
//            return dao.load(0, -1, "null", 0, new ArrayList());
            return new ArrayList();
        } else {
            RequestFilter filterEmail = new RequestFilter();
            //replace name by the query field
            filterEmail.setName("EMAIL");
            filterEmail.setType(RequestFilter.CONTAIN);
            filterEmail.setValue(query);
            filterEmail.setRequired(false);
            
            RequestFilter filterFullName = new RequestFilter();
            //replace name by the query field
            filterFullName.setName("FULLNAME");
            filterFullName.setType(RequestFilter.CONTAIN);
            filterFullName.setValue(query);
            filterFullName.setRequired(false);
            
            RequestFilter filterMobile = new RequestFilter();
            //replace name by the query field
            filterMobile.setName("PHONE_NUM");
            filterMobile.setType(RequestFilter.CONTAIN);
            filterMobile.setValue(query);
            filterMobile.setRequired(false);
            
            List filter_list = new ArrayList();
            filter_list.add(filterEmail);
            filter_list.add(filterMobile);
            return dao.load(0, -1, "null", 0, filter_list);
        }
    }

    /**
     * @return the objects
     */
    public List<TBL_HT_USER> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<TBL_HT_USER> objects) {
        this.objects = objects;
    }

    /**
     * @return the selectedObject
     */
    public TBL_HT_USER getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject the selectedObject to set
     */
    public void setSelectedObject(TBL_HT_USER selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * @return the newObject
     */
    public TBL_HT_USER getNewObject() {
        return newObject;
    }

    /**
     * @param newObject the newObject to set
     */
    public void setNewObject(TBL_HT_USER newObject) {
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
}
