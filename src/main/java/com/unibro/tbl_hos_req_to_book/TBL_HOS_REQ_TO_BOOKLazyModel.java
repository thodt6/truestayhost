package com.unibro.tbl_hos_req_to_book;

import com.unibro.service.UserSessionBean;
import com.unibro.tbl_ht_user.TBL_HT_USER;
import com.unibro.utils.Global;
import com.unibro.utils.RequestFilter;
import java.util.*;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Nguyen Duc Tho
 */
public class TBL_HOS_REQ_TO_BOOKLazyModel extends LazyDataModel<TBL_HOS_REQ_TO_BOOK> {

    final Logger logger = Logger.getLogger(this.getClass().getName());
    TBL_HT_USER searchUser;
    private String booking_state = "C";
    private java.util.Date beginDate;
    private java.util.Date endDate;
    private List<String> homestayid_list;

    private List<TBL_HOS_REQ_TO_BOOK> datasources = new ArrayList<TBL_HOS_REQ_TO_BOOK>();

    public TBL_HOS_REQ_TO_BOOKLazyModel() {
        this.datasources = new ArrayList();
    }

    public TBL_HOS_REQ_TO_BOOKLazyModel(List<String> homestayid_list, String booking_state, TBL_HT_USER searchUser, java.util.Date beginDate, java.util.Date endDate) {
        this.searchUser = searchUser;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.booking_state = booking_state;
        this.homestayid_list = homestayid_list;
        this.datasources = new ArrayList();
    }

    public TBL_HOS_REQ_TO_BOOKLazyModel(ArrayList<TBL_HOS_REQ_TO_BOOK> datasources) {
        this.datasources = datasources;
    }

    public List<TBL_HOS_REQ_TO_BOOK> getDatasources() {
        return this.datasources;
    }

    public void setDatasources(List<TBL_HOS_REQ_TO_BOOK> datasources) {
        this.datasources = datasources;
    }

    @Override
    public TBL_HOS_REQ_TO_BOOK getRowData(String rowKey) {
        for (TBL_HOS_REQ_TO_BOOK obj : datasources) {
            if (obj.getID().equals(rowKey)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public String getRowKey(TBL_HOS_REQ_TO_BOOK obj) {
        return obj.getID();
    }

    @Override
    public List<TBL_HOS_REQ_TO_BOOK> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
        if (filters == null || filters.isEmpty()) {
            int sort = 0;
            if (sortOrder.equals(SortOrder.ASCENDING)) {
                sort = 1;
            }
            if (sortOrder.equals(SortOrder.DESCENDING)) {
                sort = -1;
            }
            List<RequestFilter> list = new ArrayList();
            if (this.homestayid_list != null) {
                RequestFilter filter = new RequestFilter();
                filter.setName("HOMESTAYID");
                filter.setRequired(true);
                filter.setType(RequestFilter.IN);
                filter.setValue(homestayid_list);
                list.add(filter);
            }
            if (searchUser != null) {
                RequestFilter userFilter = new RequestFilter();
                userFilter.setRequired(true);
                userFilter.setName("USERNAME");
                userFilter.setType(RequestFilter.EQUAL);
                userFilter.setValue(this.searchUser.getUSERNAME());
                list.add(userFilter);
            }
            if (this.beginDate != null) {
                RequestFilter beginDateFilter = new RequestFilter();
                beginDateFilter.setRequired(true);
                beginDateFilter.setName("DATETIME");
                beginDateFilter.setType(RequestFilter.GREATER);
                beginDateFilter.setValue(Global.getDateInStringFormat("yyyyMMdd", beginDate));
                beginDateFilter.setFunction("to_char(to_date(fieldname, 'yyyy/MM/dd HH24:MI:SS'),'yyyyMMdd')");
                list.add(beginDateFilter);
            }
            if (this.endDate != null) {
                RequestFilter endDateFilter = new RequestFilter();
                endDateFilter.setRequired(true);
                endDateFilter.setName("DATETIME");
                endDateFilter.setType(RequestFilter.LESS);
                endDateFilter.setValue(Global.getDateInStringFormat("yyyyMMdd", endDate));
                endDateFilter.setFunction("to_char(to_date(fieldname, 'yyyy/MM/dd HH24:MI:SS'),'yyyyMMdd')");
                list.add(endDateFilter);
            }

            this.datasources = dao.load(first, pageSize, sortField, sort, list);
            for(TBL_HOS_REQ_TO_BOOK req:this.datasources){
                req.setHomestay(UserSessionBean.getUserSession().getHomestay_list().getHomestayList(req.getHOMESTAYID()));
            }
            this.setRowCount(Long.valueOf(dao.getTotalObject(list)).intValue());
            return datasources;
        } else {
            int sort = 0;
            if (sortOrder.equals(SortOrder.ASCENDING)) {
                sort = 1;
            }
            if (sortOrder.equals(SortOrder.DESCENDING)) {
                sort = -1;
            }
            List<RequestFilter> list = RequestFilter.fromHashMap(filters);
            if (searchUser != null) {
                RequestFilter userFilter = new RequestFilter();
                userFilter.setRequired(true);
                userFilter.setName("USERNAME");
                userFilter.setType(RequestFilter.EQUAL);
                userFilter.setValue(this.searchUser.getUSERNAME());
                list.add(userFilter);
            }
            this.datasources = dao.load(first, pageSize, sortField, sort, list);
            for(TBL_HOS_REQ_TO_BOOK req:this.datasources){
                req.setHomestay(UserSessionBean.getUserSession().getHomestay_list().getHomestayList(req.getHOMESTAYID()));
            }
            this.setRowCount(Long.valueOf(dao.getTotalObject(list)).intValue());
            return datasources;
        }
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
