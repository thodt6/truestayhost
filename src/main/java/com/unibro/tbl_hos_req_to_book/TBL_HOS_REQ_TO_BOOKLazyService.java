package com.unibro.tbl_hos_req_to_book;

import com.unibro.model.HomestayList;
import com.unibro.service.UserSessionBean;
import com.unibro.tbl_ht_user.TBL_HT_USER;
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
public class TBL_HOS_REQ_TO_BOOKLazyService implements Serializable {

    private TBL_HT_USER searchUser;
    private java.util.Date beginDate, endDate;
    private LazyDataModel<TBL_HOS_REQ_TO_BOOK> lazyModel;
    private TBL_HOS_REQ_TO_BOOK selectedObject = new TBL_HOS_REQ_TO_BOOK();
    private TBL_HOS_REQ_TO_BOOK[] selectedObjects;
    private String booking_state = "C";
    private TBL_HOS_REQ_TO_BOOK newObject = new TBL_HOS_REQ_TO_BOOK();
    private String selectedId;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public TBL_HOS_REQ_TO_BOOKLazyService() {
        this.loadObjects();
    }

    public void initSelectedObject() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            this.selectedObject = dao.getObjectByKey(selectedId);
        }
    }

    public final void loadObjects() {
        List<HomestayList> list_ht = UserSessionBean.getUserSession().getHomestay_list().getHomeStayByState("A");
        if (list_ht.isEmpty()) {
            return;
        }
        List<String> homestayid_list = UserSessionBean.getUserSession().getHomestay_list().getHomeStayIdList(list_ht);
        this.lazyModel = new TBL_HOS_REQ_TO_BOOKLazyModel(homestayid_list, booking_state, searchUser, this.beginDate, this.endDate);
    }

    public void setLazyModel(LazyDataModel<TBL_HOS_REQ_TO_BOOK> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public LazyDataModel<TBL_HOS_REQ_TO_BOOK> getLazyModel() {
        return lazyModel;
    }

    public void setNewObject(TBL_HOS_REQ_TO_BOOK newObject) {
        this.newObject = newObject;
    }

    public TBL_HOS_REQ_TO_BOOK getNewObject() {
        return newObject;
    }

    public void setSelectedObject(TBL_HOS_REQ_TO_BOOK selectedObject) {
        this.selectedObject = selectedObject;
    }

    public TBL_HOS_REQ_TO_BOOK getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObjects(TBL_HOS_REQ_TO_BOOK[] selectedObjects) {
        this.selectedObjects = selectedObjects;
    }

    public TBL_HOS_REQ_TO_BOOK[] getSelectedObjects() {
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
            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            TBL_HOS_REQ_TO_BOOK result = dao.create(getNewObject());
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
            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
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
            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            dao.delete(selectedObject);
            this.loadObjects();
        }
    }

    public void deleteObjects() {
        if (this.selectedObjects != null) {
            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            for (TBL_HOS_REQ_TO_BOOK selectedObject1 : this.selectedObjects) {
                dao.delete(selectedObject1);
            }
            this.loadObjects();
        }
    }

    public void rowEdit(RowEditEvent event) {
        TBL_HOS_REQ_TO_BOOK sf = (TBL_HOS_REQ_TO_BOOK) event.getObject();
        if (sf != null) {
            this.selectedObject = sf;
            this.editSelected();
        }
    }

    public List<TBL_HOS_REQ_TO_BOOK> completeObject(String query) {
        TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
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
     * @return the searchUser
     */
    public TBL_HT_USER getSearchUser() {
        return searchUser;
    }

    /**
     * @param searchUser the searchUser to set
     */
    public void setSearchUser(TBL_HT_USER searchUser) {
        this.searchUser = searchUser;
    }

    /**
     * @return the beginDate
     */
    public java.util.Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public java.util.Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the booking_state
     */
    public String getBooking_state() {
        return booking_state;
    }

    /**
     * @param booking_state the booking_state to set
     */
    public void setBooking_state(String booking_state) {
        this.booking_state = booking_state;
    }

}
