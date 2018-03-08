package com.unibro.currency_tab;

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
public class CURRENCY_TABLazyService implements Serializable {

    private LazyDataModel<CURRENCY_TAB> lazyModel;
    private CURRENCY_TAB selectedObject = new CURRENCY_TAB();
    private CURRENCY_TAB[] selectedObjects;
    private CURRENCY_TAB newObject = new CURRENCY_TAB();
    private String selectedId;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CURRENCY_TABLazyService() {
        this.loadObjects();
    }

    public void initSelectedObject() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
            this.selectedObject = dao.getObjectByKey(selectedId);
        }
    }

    public final void loadObjects() {
        this.lazyModel = new CURRENCY_TABLazyModel();
    }

    public void setLazyModel(LazyDataModel<CURRENCY_TAB> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public LazyDataModel<CURRENCY_TAB> getLazyModel() {
        return lazyModel;
    }

    public void setNewObject(CURRENCY_TAB newObject) {
        this.newObject = newObject;
    }

    public CURRENCY_TAB getNewObject() {
        return newObject;
    }

    public void setSelectedObject(CURRENCY_TAB selectedObject) {
        this.selectedObject = selectedObject;
    }

    public CURRENCY_TAB getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObjects(CURRENCY_TAB[] selectedObjects) {
        this.selectedObjects = selectedObjects;
    }

    public CURRENCY_TAB[] getSelectedObjects() {
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

    public void deleteObjects() {
        if (this.selectedObjects != null) {
            CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
            for (CURRENCY_TAB selectedObject1 : this.selectedObjects) {
                dao.delete(selectedObject1);
            }
            this.loadObjects();
        }
    }

    public void rowEdit(RowEditEvent event) {
        CURRENCY_TAB sf = (CURRENCY_TAB) event.getObject();
        if (sf != null) {
            this.selectedObject = sf;
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

}
