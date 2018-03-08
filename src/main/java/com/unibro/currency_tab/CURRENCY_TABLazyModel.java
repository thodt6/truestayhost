package com.unibro.currency_tab;

import com.unibro.utils.RequestFilter;
import java.util.*;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Nguyen Duc Tho
 */
public class CURRENCY_TABLazyModel extends LazyDataModel<CURRENCY_TAB> {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    private List<CURRENCY_TAB> datasources = new ArrayList<CURRENCY_TAB>();

    public CURRENCY_TABLazyModel() {
        this.datasources = new ArrayList();
    }

    public CURRENCY_TABLazyModel(ArrayList<CURRENCY_TAB> datasources) {
        this.datasources = datasources;
    }

    public List<CURRENCY_TAB> getDatasources() {
        return this.datasources;
    }

    public void setDatasources(List<CURRENCY_TAB> datasources) {
        this.datasources = datasources;
    }

    @Override
    public CURRENCY_TAB getRowData(String rowKey) {
        for (CURRENCY_TAB obj : datasources) {
            if (obj.getCODE_A3().equals(rowKey)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public String getRowKey(CURRENCY_TAB obj) {
        return obj.getCODE_A3();
    }

    @Override
    public List<CURRENCY_TAB> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CURRENCY_TABDAO dao = new CURRENCY_TABDAO();
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
