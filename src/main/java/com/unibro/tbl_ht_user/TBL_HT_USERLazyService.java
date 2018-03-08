package com.unibro.tbl_ht_user;

import com.unibro.utils.Global;
import com.unibro.utils.RequestFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Nguyen Duc Tho
 */
@ManagedBean
@ViewScoped
public class TBL_HT_USERLazyService implements Serializable {

    private LazyDataModel<TBL_HT_USER> lazyModel;
    private TBL_HT_USER selectedObject = new TBL_HT_USER();
    private TBL_HT_USER[] selectedObjects;
    private TBL_HT_USER newObject = new TBL_HT_USER();
    private String selectedId;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public TBL_HT_USERLazyService() {
        this.loadObjects();
    }

    public void initSelectedObject() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
            this.selectedObject = dao.getObjectByKey(selectedId);
        }
    }

    public final void loadObjects() {
        this.lazyModel = new TBL_HT_USERLazyModel();
    }

    public void setLazyModel(LazyDataModel<TBL_HT_USER> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public LazyDataModel<TBL_HT_USER> getLazyModel() {
        return lazyModel;
    }

    public void setNewObject(TBL_HT_USER newObject) {
        this.newObject = newObject;
    }

    public TBL_HT_USER getNewObject() {
        return newObject;
    }

    public void setSelectedObject(TBL_HT_USER selectedObject) {
        this.selectedObject = selectedObject;
    }

    public TBL_HT_USER getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObjects(TBL_HT_USER[] selectedObjects) {
        this.selectedObjects = selectedObjects;
    }

    public TBL_HT_USER[] getSelectedObjects() {
        return selectedObjects;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    public String getSelectedId() {
        return selectedId;
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

    public void deleteObjects() {
        if (this.selectedObjects != null) {
            TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
            for (TBL_HT_USER selectedObject1 : this.selectedObjects) {
                dao.delete(selectedObject1);
            }
            this.loadObjects();
        }
    }

    public void rowEdit(RowEditEvent event) {
        TBL_HT_USER sf = (TBL_HT_USER) event.getObject();
        if (sf != null) {
            this.selectedObject = sf;
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

}
