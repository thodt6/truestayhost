package com.unibro.tbl_hos_trans;

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
public class TBL_HOS_TRANSLazyService implements Serializable {

    private LazyDataModel<TBL_HOS_TRANS> lazyModel;
    private TBL_HOS_TRANS selectedObject = new TBL_HOS_TRANS();
    private TBL_HOS_TRANS[] selectedObjects;
    private TBL_HOS_TRANS newObject = new TBL_HOS_TRANS();
    private String selectedId;
    private String BOOK_REQ_ID;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public TBL_HOS_TRANSLazyService() {
//        this.loadObjects();
    }

    public void initSelectedObject() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
            this.selectedObject = dao.getObjectByKey(selectedId);
        }
    }

    public final void loadTransactionByBooking() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
             this.lazyModel = new TBL_HOS_TRANSLazyModel(BOOK_REQ_ID);
        }
    }

    public final void loadObjects() {
        this.lazyModel = new TBL_HOS_TRANSLazyModel(BOOK_REQ_ID);
    }

    public void setLazyModel(LazyDataModel<TBL_HOS_TRANS> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public LazyDataModel<TBL_HOS_TRANS> getLazyModel() {
        return lazyModel;
    }

    public void setNewObject(TBL_HOS_TRANS newObject) {
        this.newObject = newObject;
    }

    public TBL_HOS_TRANS getNewObject() {
        return newObject;
    }

    public void setSelectedObject(TBL_HOS_TRANS selectedObject) {
        this.selectedObject = selectedObject;
    }

    public TBL_HOS_TRANS getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObjects(TBL_HOS_TRANS[] selectedObjects) {
        this.selectedObjects = selectedObjects;
    }

    public TBL_HOS_TRANS[] getSelectedObjects() {
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

    public void deleteObjects() {
        if (this.selectedObjects != null) {
            TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
            for (TBL_HOS_TRANS selectedObject1 : this.selectedObjects) {
                dao.delete(selectedObject1);
            }
            this.loadObjects();
        }
    }

    public void rowEdit(RowEditEvent event) {
        TBL_HOS_TRANS sf = (TBL_HOS_TRANS) event.getObject();
        if (sf != null) {
            this.selectedObject = sf;
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
     * @return the BOOK_REQ_ID
     */
    public String getBOOK_REQ_ID() {
        return BOOK_REQ_ID;
    }

    /**
     * @param BOOK_REQ_ID the BOOK_REQ_ID to set
     */
    public void setBOOK_REQ_ID(String BOOK_REQ_ID) {
        this.BOOK_REQ_ID = BOOK_REQ_ID;
    }

}
