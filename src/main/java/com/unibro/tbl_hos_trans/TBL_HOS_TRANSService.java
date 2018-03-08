/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.tbl_hos_trans;

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
public class TBL_HOS_TRANSService {

    private List<TBL_HOS_TRANS> objects;
    private TBL_HOS_TRANS selectedObject;
    private TBL_HOS_TRANS newObject;

    private String selectedId;

    public void initSelectedObject() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            //Add code for init object here
            TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
            this.selectedObject = dao.getObjectByKey(selectedId);
        }
    }

    public final void loadObjects() {
        //Add code to load object here
        TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
        this.objects = dao.load(0, -1, "null", 0, new ArrayList());
    }

    public void createObject() {
        if (this.getNewObject() != null) {
            TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
            TBL_HOS_TRANS result = dao.create(getNewObject());
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
            TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
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
            TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
            dao.delete(selectedObject);
            this.loadObjects();
        }
    }

    public void rowEdit(RowEditEvent event) {
        TBL_HOS_TRANS sf = (TBL_HOS_TRANS) event.getObject();
        if (sf != null) {
            this.setSelectedObject(sf);
            this.editSelected();
        }
    }

    public List<TBL_HOS_TRANS> completeObject(String query) {
        TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
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
    public List<TBL_HOS_TRANS> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<TBL_HOS_TRANS> objects) {
        this.objects = objects;
    }

    /**
     * @return the selectedObject
     */
    public TBL_HOS_TRANS getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject the selectedObject to set
     */
    public void setSelectedObject(TBL_HOS_TRANS selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * @return the newObject
     */
    public TBL_HOS_TRANS getNewObject() {
        return newObject;
    }

    /**
     * @param newObject the newObject to set
     */
    public void setNewObject(TBL_HOS_TRANS newObject) {
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
