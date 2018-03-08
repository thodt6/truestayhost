/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.dashboard;

import com.unibro.model.HomestayList;
import com.unibro.service.UserSessionBean;
import com.unibro.service.utils.HomestayListService;
import com.unibro.tbl_hos_req_to_book.*;
import com.unibro.utils.Global;
import com.unibro.utils.RequestFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DashboardService {

    private Date filterDate = new java.util.Date();
    private int filterMode = 0;

//    private List<HomestayList> homestay_list;
    HomestayListService homestay_list;

    private List<TBL_HOS_REQ_TO_BOOK> objects;
    private List<TBL_HOS_REQ_TO_BOOK> pendingObjects;
    private List<TBL_HOS_REQ_TO_BOOK> recentObjects;
    private TBL_HOS_REQ_TO_BOOK selectedObject;

    private String selectedId;
    private int current_month = 0;
    private String current_earning = "0";
    private int current_booking = 0;

    Logger logger = Logger.getLogger(this.getClass().getName());

    public void initSelectedObject() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            //Add code for init object here
            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            this.selectedObject = dao.getObjectByKey(selectedId);
        }
    }

    public DashboardService() {
//        this.loadHomeStay();
        this.homestay_list = UserSessionBean.getUserSession().getHomestay_list();
        this.homestay_list.printMyHomeStay();
        this.loadObjects();
        this.loadRequestBooking();
        this.loadHistoryBooking();
        this.loadEarning();
    }

//    private void loadHomeStay() {
//        this.homestay_list = Listing.getHomestayList(UserSessionBean.getUserSession().getUser().getUsername(), "");
//    }
//
//    private HomestayList getHomestayList(String homestayid) {
//        if (this.homestay_list != null) {
//            for (HomestayList h : this.homestay_list) {
//                if (h.getHomestay_id().equals(homestayid)) {
//                    return h;
//                }
//            }
//        }
//        return null;
//    }
    public final void loadObjects() {
        //Add code to load object here

        List<HomestayList> list = this.homestay_list.getHomeStayByState("A");
        if (list.isEmpty()) {
            return;
        }
        logger.info("homestay list:" + list);
        List<String> homestayid_list = this.homestay_list.getHomeStayIdList(list);
        if (homestayid_list != null) {

            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            List filterList = new ArrayList();
            RequestFilter filter = new RequestFilter();
            filter.setName("HOMESTAYID");
            filter.setRequired(true);
            filter.setType(RequestFilter.IN);
            filter.setValue(homestayid_list);
            filterList.add(filter);

            filter = new RequestFilter();
            filter.setName("STATE");
            filter.setRequired(true);
            filter.setType(RequestFilter.EQUAL);
            filter.setValue("C");
            filterList.add(filter);

            if (this.filterMode == 0) {
                RequestFilter filter1 = new RequestFilter();
                filter1.setName("CHECKIN");
                filter1.setRequired(true);
                filter1.setType(RequestFilter.EQUAL);
                filter1.setValue(Global.getDateInStringFormat("yyyyMMdd", filterDate));
                filterList.add(filter1);
            }
            if (this.filterMode == 1) {
                RequestFilter filter1 = new RequestFilter();
                filter1.setName("CHECKOUT");
                filter1.setRequired(true);
                filter1.setType(RequestFilter.EQUAL);
                filter1.setValue(Global.getDateInStringFormat("yyyyMMdd", filterDate));
                filterList.add(filter1);
            }
            if (this.filterMode == 2) {
                RequestFilter filter1 = new RequestFilter();
                filter1.setRequired(true);
                filter1.setName("CHECKIN");
                filter1.setType(RequestFilter.LESS);
                filter1.setValue(Global.getDateInStringFormat("yyyyMMdd", filterDate));
                filter1.setFunction("to_char(to_date(fieldname, 'yyyy/MM/dd HH24:MI:SS'),'yyyyMMdd')");
                filterList.add(filter1);
                RequestFilter filter2 = new RequestFilter();
                filter2.setRequired(true);
                filter2.setName("CHECKOUT");
                filter2.setType(RequestFilter.GREATER);
                filter2.setValue(Global.getDateInStringFormat("yyyyMMdd", filterDate));
                filter2.setFunction("to_char(to_date(fieldname, 'yyyy/MM/dd HH24:MI:SS'),'yyyyMMdd')");
                filterList.add(filter2);
            }

            this.objects = dao.load(0, -1, "DATETIME", -1, filterList);
            if (this.objects != null && !this.objects.isEmpty()) {
                for (TBL_HOS_REQ_TO_BOOK req : this.objects) {
                    req.setHomestay(this.homestay_list.getHomestayList(req.getHOMESTAYID()));
                }
            }
        }

    }

    public final void loadRequestBooking() {
        //Add code to load object here
        List<HomestayList> list = this.homestay_list.getHomeStayByState("A");
        if (list.isEmpty()) {
            return;
        }
        List<String> homestayid_list = this.homestay_list.getHomeStayIdList(list);

        if (homestayid_list != null) {

            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            List filterList = new ArrayList();
            RequestFilter filter = new RequestFilter();
            filter.setName("HOMESTAYID");
            filter.setRequired(true);
            filter.setType(RequestFilter.IN);
            filter.setValue(homestayid_list);
            filterList.add(filter);

            List listState = new ArrayList();
            listState.add("H");
            listState.add("R");

            filter = new RequestFilter();
            filter.setName("STATE");
            filter.setRequired(true);
            filter.setType(RequestFilter.IN);
            filter.setValue(listState);
            filterList.add(filter);

            this.pendingObjects = dao.load(0, -1, "DATETIME", -1, filterList);
            if (this.pendingObjects != null && !this.pendingObjects.isEmpty()) {
                for (TBL_HOS_REQ_TO_BOOK req : this.pendingObjects) {
                    req.setHomestay(this.homestay_list.getHomestayList(req.getHOMESTAYID()));
                }
            }
        }

    }

    public final void loadHistoryBooking() {
        //Add code to load object here
        List<HomestayList> list = this.homestay_list.getHomeStayByState("A");
        if (list.isEmpty()) {
            return;
        }
        List<String> homestayid_list = this.homestay_list.getHomeStayIdList(list);

        if (homestayid_list != null) {
            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            List filterList = new ArrayList();
            RequestFilter filter = new RequestFilter();
            filter.setName("HOMESTAYID");
            filter.setRequired(true);
            filter.setType(RequestFilter.IN);
            filter.setValue(homestayid_list);
            filterList.add(filter);

            filter = new RequestFilter();
            filter.setName("STATE");
            filter.setRequired(true);
            filter.setType(RequestFilter.EQUAL);
            filter.setValue("C");
            filterList.add(filter);

            this.recentObjects = dao.load(0, -1, "DATETIME", -1, filterList);
            if (this.recentObjects != null && !this.recentObjects.isEmpty()) {
                for (TBL_HOS_REQ_TO_BOOK req : this.recentObjects) {
                    req.setHomestay(this.homestay_list.getHomestayList(req.getHOMESTAYID()));
                }
            }
        }

    }

    /**
     * @return the objects
     */
    public List<TBL_HOS_REQ_TO_BOOK> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<TBL_HOS_REQ_TO_BOOK> objects) {
        this.objects = objects;
    }

    /**
     * @return the selectedObject
     */
    public TBL_HOS_REQ_TO_BOOK getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject the selectedObject to set
     */
    public void setSelectedObject(TBL_HOS_REQ_TO_BOOK selectedObject) {
        this.selectedObject = selectedObject;
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

    /**
     * @return the filterDate
     */
    public Date getFilterDate() {
        return filterDate;
    }

    /**
     * @param filterDate the filterDate to set
     */
    public void setFilterDate(Date filterDate) {
        this.filterDate = filterDate;
    }

    /**
     * @return the recentObjects
     */
    public List<TBL_HOS_REQ_TO_BOOK> getRecentObjects() {
        return recentObjects;
    }

    /**
     * @param recentObjects the recentObjects to set
     */
    public void setRecentObjects(List<TBL_HOS_REQ_TO_BOOK> recentObjects) {
        this.recentObjects = recentObjects;
    }

    /**
     * @return the pendingObjects
     */
    public List<TBL_HOS_REQ_TO_BOOK> getPendingObjects() {
        return pendingObjects;
    }

    /**
     * @param pendingObjects the pendingObjects to set
     */
    public void setPendingObjects(List<TBL_HOS_REQ_TO_BOOK> pendingObjects) {
        this.pendingObjects = pendingObjects;
    }

    /**
     * @return the filterMode
     */
    public int getFilterMode() {
        return filterMode;
    }

    /**
     * @param filterMode the filterMode to set
     */
    public void setFilterMode(int filterMode) {
        this.filterMode = filterMode;
    }

    /**
     * @return the current_month
     */
    public int getCurrent_month() {
        return current_month;
    }

    /**
     * @param current_month the current_month to set
     */
    public void setCurrent_month(int current_month) {
        this.current_month = current_month;
    }

    /**
     * @return the current_earning
     */
    public String getCurrent_earning() {
        return current_earning;
    }

    /**
     * @param current_earning the current_earning to set
     */
    public void setCurrent_earning(String current_earning) {
        this.current_earning = current_earning;
    }

    /**
     * @return the current_booking
     */
    public int getCurrent_booking() {
        return current_booking;
    }

    /**
     * @param current_booking the current_booking to set
     */
    public void setCurrent_booking(int current_booking) {
        this.current_booking = current_booking;
    }

    private void loadEarning() {
        Calendar start_cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
        this.current_month = start_cal.get(Calendar.MONTH);
        start_cal.set(Calendar.DAY_OF_MONTH, 1);
        Calendar stop_cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
        stop_cal.set(Calendar.DAY_OF_MONTH, 1);
        stop_cal.add(Calendar.MONTH, 1);
        stop_cal.add(Calendar.DATE, -1);
        List<HomestayList> list_ht = this.homestay_list.getHomeStayByState("A");
        if (list_ht.isEmpty()) {
            return;
        }
        List<String> homestayid_list = this.homestay_list.getHomeStayIdList(list_ht);
        if (homestayid_list != null) {

            TBL_HOS_REQ_TO_BOOKDAO dao = new TBL_HOS_REQ_TO_BOOKDAO();
            List filterList = new ArrayList();
            RequestFilter filter = new RequestFilter();
            filter.setName("HOMESTAYID");
            filter.setRequired(true);
            filter.setType(RequestFilter.IN);
            filter.setValue(homestayid_list);
            filterList.add(filter);

            filter = new RequestFilter();
            filter.setName("STATE");
            filter.setRequired(true);
            filter.setType(RequestFilter.EQUAL);
            filter.setValue("C");
            filterList.add(filter);

            RequestFilter filter1 = new RequestFilter();
            filter1.setName("DATETIME");
            filter1.setRequired(true);
            filter1.setType(RequestFilter.GREATER);
            filter1.setValue(Global.getDateInStringFormat("yyyyMMdd", start_cal.getTime()));
            filter1.setFunction("to_char(to_date(fieldname, 'yyyy/MM/dd HH24:MI:SS'),'yyyyMMdd')");
            filterList.add(filter1);

            RequestFilter filter2 = new RequestFilter();
            filter2.setName("DATETIME");
            filter2.setRequired(true);
            filter2.setType(RequestFilter.LESS);
            filter2.setValue(Global.getDateInStringFormat("yyyyMMdd", stop_cal.getTime()));
            filter2.setFunction("to_char(to_date(fieldname, 'yyyy/MM/dd HH24:MI:SS'),'yyyyMMdd')");
            filterList.add(filter2);

            List<TBL_HOS_REQ_TO_BOOK> list = dao.load(0, -1, "DATETIME", -1, filterList);
            
            if (list != null && !list.isEmpty()) {
                this.current_booking = list.size();
                HashMap map = this.loadListCurrency(list);
                this.current_earning = "";
                for (Object key : map.keySet()) {
                    List<TBL_HOS_REQ_TO_BOOK> list_rq = (List<TBL_HOS_REQ_TO_BOOK>) map.get(key);
                    if (list_rq != null && !list_rq.isEmpty()) {
                        this.current_earning += "," + this.getEarning((String) key, list_rq);
                    }
                }
                if (this.current_earning.length() > 0) {
                    this.current_earning = this.current_earning.substring(1);
                }
            }

        }
    }

    private HashMap loadListCurrency(List<TBL_HOS_REQ_TO_BOOK> list) {
        HashMap ret = new HashMap();
        for (TBL_HOS_REQ_TO_BOOK req : list) {
            String currency = req.getCURRENCY().toLowerCase();
            if (ret.containsKey(currency)) {
                List<TBL_HOS_REQ_TO_BOOK> list_rq = (List<TBL_HOS_REQ_TO_BOOK>) ret.get(currency);
                list_rq.add(req);
            } else {
                List<TBL_HOS_REQ_TO_BOOK> list_rq = new ArrayList();
                list_rq.add(req);
                ret.put(currency.toLowerCase(), list_rq);
            }
        }
        return ret;
    }

    private String getEarning(String currency, List<TBL_HOS_REQ_TO_BOOK> list_rq) {
        Double total = 0.0;
        if (list_rq != null && !list_rq.isEmpty()) {
            for (TBL_HOS_REQ_TO_BOOK req : list_rq) {
                total += Double.valueOf(req.getTOTAL_AMOUNT()) - Double.valueOf(req.getSERVICE_FEES());
            }
        }
        return String.format("%1$,.0f", total) + " " + currency;
    }

    public String getCurrenEarningLabel() {
        return (Global.getResourceLanguage("page.dashboard.earning1") + " "
                + Global.getResourceLanguage("general.month." + this.current_month) + " "
                + Global.getResourceLanguage("page.dashboard.earning2")).trim();
    }

    public String getCurrenBookingLabel() {
        return (Global.getResourceLanguage("page.dashboard.booking1") + " "
                + Global.getResourceLanguage("general.month." + this.current_month) + " "
                + Global.getResourceLanguage("page.dashboard.booking2")).trim();
    }

    public void acceptRequest() {
        if (this.selectedObject != null) {
            this.selectedObject.acceptRequest();
            this.loadRequestBooking();
        }
    }

    public void rejectRequest() {
        if (this.selectedObject != null) {
            this.selectedObject.rejectRequest();
            this.loadRequestBooking();
        }
    }

}
