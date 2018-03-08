package com.unibro.tbl_ht_user;

import com.unibro.utils.RequestFilter;
import java.util.*;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Nguyen Duc Tho
 */
public class TBL_HT_USERLazyModel extends LazyDataModel<TBL_HT_USER> {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    private List<TBL_HT_USER> datasources = new ArrayList<TBL_HT_USER>();

    public TBL_HT_USERLazyModel() {
        this.datasources = new ArrayList();
    }

    public TBL_HT_USERLazyModel(ArrayList<TBL_HT_USER> datasources) {
        this.datasources = datasources;
    }

    public List<TBL_HT_USER> getDatasources() {
        return this.datasources;
    }

    public void setDatasources(List<TBL_HT_USER> datasources) {
        this.datasources = datasources;
    }

    @Override
    public TBL_HT_USER getRowData(String rowKey) {
        for (TBL_HT_USER obj : datasources) {
            if (obj.getID().toString().equals(rowKey)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public String getRowKey(TBL_HT_USER obj) {
        return obj.getID();
    }

    @Override
    public List<TBL_HT_USER> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        TBL_HT_USERDAO dao = new TBL_HT_USERDAO();
        if (filters == null || filters.isEmpty()) {
            int sort = 0;
            if (sortOrder.equals(SortOrder.ASCENDING)) {
                sort = 1;
            }
            if (sortOrder.equals(SortOrder.DESCENDING)) {
                sort = -1;
            }
            this.datasources = dao.load(first, pageSize, sortField, sort, new ArrayList());
            this.setRowCount(Long.valueOf(dao.getTotalObject(new ArrayList())).intValue());
            return datasources;
        } else {
            int sort = 0;
            if (sortOrder.equals(SortOrder.ASCENDING)) {
                sort = 1;
            }
            if (sortOrder.equals(SortOrder.DESCENDING)) {
                sort = -1;
            }
            this.datasources = dao.load(first, pageSize, sortField, sort, RequestFilter.fromHashMap(filters));
            this.setRowCount(Long.valueOf(dao.getTotalObject(RequestFilter.fromHashMap(filters))).intValue());
            return datasources;
        }
    }

}
