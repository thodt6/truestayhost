package com.unibro.tbl_hos_trans;

import com.unibro.utils.RequestFilter;
import java.util.*;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Nguyen Duc Tho
 */
public class TBL_HOS_TRANSLazyModel extends LazyDataModel<TBL_HOS_TRANS> {

    private String BOOK_REQ_ID;

    final Logger logger = Logger.getLogger(this.getClass().getName());

    private List<TBL_HOS_TRANS> datasources = new ArrayList<TBL_HOS_TRANS>();

    public TBL_HOS_TRANSLazyModel(String BOOK_REQ_ID) {
        this.BOOK_REQ_ID = BOOK_REQ_ID;
        this.datasources = new ArrayList();
    }

    public TBL_HOS_TRANSLazyModel(ArrayList<TBL_HOS_TRANS> datasources) {
        this.datasources = datasources;
    }

    public List<TBL_HOS_TRANS> getDatasources() {
        return this.datasources;
    }

    public void setDatasources(List<TBL_HOS_TRANS> datasources) {
        this.datasources = datasources;
    }

    @Override
    public TBL_HOS_TRANS getRowData(String rowKey) {
        for (TBL_HOS_TRANS obj : datasources) {
            if (obj.getID().equals(rowKey)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public String getRowKey(TBL_HOS_TRANS obj) {
        return obj.getID();
    }

    private RequestFilter getBookingID_Filter() {
        if (this.BOOK_REQ_ID != null && !this.BOOK_REQ_ID.equals("")) {
            RequestFilter f = new RequestFilter();
            f.setFunction("");
            f.setName("BOOK_REQ_ID");
            f.setRequired(true);
            f.setType(RequestFilter.EQUAL);
            f.setValue(BOOK_REQ_ID);
            return f;
        }
        return null;
    }

    @Override
    public List<TBL_HOS_TRANS> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        TBL_HOS_TRANSDAO dao = new TBL_HOS_TRANSDAO();
        if (filters == null || filters.isEmpty()) {
            int sort = 0;
            if (sortOrder.equals(SortOrder.ASCENDING)) {
                sort = 1;
            }
            if (sortOrder.equals(SortOrder.DESCENDING)) {
                sort = -1;
            }
            RequestFilter f = this.getBookingID_Filter();
            List filter_list = new ArrayList();
            if (f != null) {
                filter_list.add(this.getBookingID_Filter());
            }
            this.datasources = dao.load(first, pageSize, sortField, sort, filter_list);
            this.setRowCount(Long.valueOf(dao.getTotalObject(filter_list)).intValue());
            return datasources;
        } else {
            int sort = 0;
            if (sortOrder.equals(SortOrder.ASCENDING)) {
                sort = 1;
            }
            if (sortOrder.equals(SortOrder.DESCENDING)) {
                sort = -1;
            }
            RequestFilter f = this.getBookingID_Filter();
            List filter_list = RequestFilter.fromHashMap(filters);
            if (f != null) {
                filter_list.add(this.getBookingID_Filter());
            }
            this.datasources = dao.load(first, pageSize, sortField, sort, filter_list);
            this.setRowCount(Long.valueOf(dao.getTotalObject(filter_list)).intValue());
            return datasources;
        }
    }

}
