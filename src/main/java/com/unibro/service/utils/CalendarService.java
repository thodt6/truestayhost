
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.model.CalendarItem;
import com.unibro.model.User;
import com.unibro.utils.Global;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author THOND
 */
public class CalendarService {

    public static final String BLOC_STATE = "B";
    public static final String UNBLOC_STATE = "U";

    private String homestay_id;


    private String startDateRange;
    private String endDateRante;

    private String state = "B";
    private Date beginDate = new java.util.Date();
    private Date endDate = new java.util.Date();

    private String eventString;

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass().getName());
    
    User user;

    public CalendarService(User user) {
//        this.blockPast();
        this.user = user;
    }

    public void loadObject() {
        JsonObject obj = Listing.getHomestayBlockCalendar(user.getUsername(), homestay_id);
        if (obj.get("message").getAsString().equals("200")) {
            JsonElement element = obj.get("data").getAsJsonObject().get("homestay_calendar_block");
            if (element != null && !element.toString().equals("null")) {
                logger.info(element.toString());
                JsonArray arr = element.getAsJsonArray();
                this.blockPast();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                List<CalendarItem> listItem = new ArrayList();
                for (int i = 0; i < arr.size(); i++) {
                    try {
                        JsonObject item = arr.get(i).getAsJsonObject();
                        String date1 = item.get("begindate").getAsString();
                        String date2 = item.get("enddate").getAsString();
                        logger.info(date1 + "-" + date2);
                        Date begin = format.parse(date1);
                        Calendar cal_begin = Calendar.getInstance();
                        cal_begin.setTime(begin);
                        cal_begin.set(Calendar.HOUR_OF_DAY, 0);
                        cal_begin.set(Calendar.MINUTE, 0);
                        cal_begin.set(Calendar.SECOND, 0);
                        Date end = format.parse(date2);
                        Calendar cal_end = Calendar.getInstance();
                        cal_end.setTime(end);
                        cal_end.add(Calendar.DATE, 1);
                        cal_end.set(Calendar.HOUR_OF_DAY, 0);
                        cal_end.set(Calendar.MINUTE, 0);
                        cal_end.set(Calendar.SECOND, 0);
                        CalendarItem cal_item = new CalendarItem();
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                        cal_item.setTitle(BLOC_STATE);
                        cal_item.setStart(format1.format(cal_begin.getTime()));
                        cal_item.setEnd(format1.format(cal_end.getTime()));
                        listItem.add(cal_item);
                    } catch (ParseException ex) {
                        logger.info(ex.toString());
                    }
                }
                Gson gson = Global.getGsonObject();
                this.eventString = gson.toJson(listItem);
            } else {
                this.eventString = "[]";
            }
//            this.beginDate=new java.util.Date();
        } else {
            this.blockPast();
        }
    }

//    private void updateCalendar(Date begindate, Date enddate, String state) {
//        JsonObject obj = Listing.updateHomestayCalendar(UserSessionBean.getUserSession().getUser().getUsername(), homestay_id, begindate, enddate, state);
//        if (obj.get("message").getAsString().equals("200")) {
//            logger.info("Has data");
//            if (obj.get("data").getAsJsonObject().get("is_success").getAsString().equals("true")) {
//                logger.info("Has data1");
//                getEventModel().clear();
//                this.blockPast();
//                JsonArray arr = obj.get("data").getAsJsonObject().getAsJsonArray("homestay_calendar_block");
//                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//                for (int i = 0; i < arr.size(); i++) {
//                    try {
//                        JsonObject item = arr.get(i).getAsJsonObject();
//                        String date1 = String.valueOf(item.get("begindate").getAsInt());
//                        String date2 = item.get("enddate").getAsString();
//                        logger.info(date1 + "-" + date2);
//                        Date begin = format.parse(date1);
//                        Calendar cal_begin = Calendar.getInstance();
//                        cal_begin.setTime(begin);
//                        cal_begin.set(Calendar.HOUR_OF_DAY, 0);
//                        cal_begin.set(Calendar.MINUTE, 0);
//                        cal_begin.set(Calendar.SECOND, 0);
//                        Date end = format.parse(date2);
//                        Calendar cal_end = Calendar.getInstance();
//                        cal_end.setTime(end);
//                        cal_end.add(Calendar.DATE, 1);
//                        cal_end.set(Calendar.HOUR_OF_DAY, 0);
//                        cal_end.set(Calendar.MINUTE, 0);
//                        cal_end.set(Calendar.SECOND, 0);
//                        DefaultScheduleEvent event = new DefaultScheduleEvent(BLOC_STATE, cal_begin.getTime(), cal_end.getTime(), "emp1");
//                        event.setAllDay(true);
//                        getEventModel().addEvent(event);
//                    } catch (ParseException ex) {
//                        logger.info(ex.toString());
//                    }
//                }
//            }
//        }
//    }
    public void updateCalendar() {
        this.updateCalendar(beginDate, endDate, state);
//        RequestContext.getCurrentInstance().execute("updateCalendar();");
    }

    public void clearAllEvent() {
        this.blockPast();
    }

    private void blockPast() {
        Global.loadConfig();
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.YEAR, -Integer.valueOf(Global.getConfigValue("app.calendar.beforeyear")));
        lastDate.set(Calendar.MONTH, 0);
        lastDate.set(Calendar.DAY_OF_MONTH, 1);
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, 1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.setStartDateRange(format.format(lastDate.getTime()));
        this.setEndDateRante(format.format(currentDate.getTime()));
    }

    public void blockAll() {
        Global.loadConfig();
        Calendar lastCal = Calendar.getInstance();
        lastCal.add(Calendar.YEAR, -Integer.valueOf(Global.getConfigValue("app.calendar.beforeyear")));
        lastCal.set(Calendar.MONTH, 0);
        lastCal.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.YEAR, Integer.valueOf(Global.getConfigValue("app.calendar.endyear")) + 1);
        endCal.set(Calendar.MONTH, 0);
        endCal.set(Calendar.DAY_OF_MONTH, 1);

        this.updateCalendar(lastCal.getTime(), endCal.getTime(), CalendarService.BLOC_STATE);
//        getEventModel().deleteEvent(getPastEvent());
    }

    public void unBlockAll() {
        Global.loadConfig();
        Calendar lastCal = Calendar.getInstance();
        lastCal.add(Calendar.YEAR, -Integer.valueOf(Global.getConfigValue("app.calendar.beforeyear")));
        lastCal.set(Calendar.MONTH, 0);
        lastCal.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.YEAR, Integer.valueOf(Global.getConfigValue("app.calendar.endyear")) + 1);
        endCal.set(Calendar.MONTH, 0);
        endCal.set(Calendar.DAY_OF_MONTH, 1);

        this.updateCalendar(lastCal.getTime(), endCal.getTime(), CalendarService.UNBLOC_STATE);
//        eventModel.deleteEvent(pastEvent);
    }

    /**
     * @return the homestay_id
     */
    public String getHomestay_id() {
        return homestay_id;
    }

    /**
     * @param homestay_id the homestay_id to set
     */
    public void setHomestay_id(String homestay_id) {
        this.homestay_id = homestay_id;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

   

    /**
     * @return the eventString
     */
    public String getEventString() {
        return eventString;
    }

    /**
     * @param eventString the eventString to set
     */
    public void setEventString(String eventString) {
        this.eventString = eventString;
    }

    /**
     * @return the startDateRange
     */
    public String getStartDateRange() {
        return startDateRange;
    }

    /**
     * @param startDateRange the startDateRange to set
     */
    public void setStartDateRange(String startDateRange) {
        this.startDateRange = startDateRange;
    }

    /**
     * @return the endDateRante
     */
    public String getEndDateRante() {
        return endDateRante;
    }

    /**
     * @param endDateRante the endDateRante to set
     */
    public void setEndDateRante(String endDateRante) {
        this.endDateRante = endDateRante;
    }

    public void showMessage(String message, FacesMessage.Severity type) {
        FacesMessage msg = new FacesMessage(type, message, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void updateCalendar(Date begindate, Date enddate, String state) {
        JsonObject obj = Listing.updateHomestayCalendar(user.getUsername(), homestay_id, begindate, enddate, state);
        if (obj.get("message").getAsString().equals("200")) {
            logger.info("Has data");
            if (obj.get("data").getAsJsonObject().get("is_success").getAsString().equals("true")) {
                logger.info("Has data1");
                JsonArray arr = obj.get("data").getAsJsonObject().getAsJsonArray("homestay_calendar_block");
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                List<CalendarItem> listItem = new ArrayList();
                for (int i = 0; i < arr.size(); i++) {
                    try {
                        JsonObject item = arr.get(i).getAsJsonObject();
                        String date1 = item.get("begindate").getAsString();
                        String date2 = item.get("enddate").getAsString();
                        logger.info(date1 + "-" + date2);
                        Date begin = format.parse(date1);
                        Calendar cal_begin = Calendar.getInstance();
                        cal_begin.setTime(begin);
                        cal_begin.set(Calendar.HOUR_OF_DAY, 0);
                        cal_begin.set(Calendar.MINUTE, 0);
                        cal_begin.set(Calendar.SECOND, 0);
                        Date end = format.parse(date2);
                        Calendar cal_end = Calendar.getInstance();
                        cal_end.setTime(end);
                        cal_end.add(Calendar.DATE, 1);
                        cal_end.set(Calendar.HOUR_OF_DAY, 0);
                        cal_end.set(Calendar.MINUTE, 0);
                        cal_end.set(Calendar.SECOND, 0);
                        CalendarItem cal_item = new CalendarItem();
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                        cal_item.setTitle(BLOC_STATE);
                        cal_item.setStart(format1.format(cal_begin.getTime()));
                        cal_item.setEnd(format1.format(cal_end.getTime()));
                        listItem.add(cal_item);
                    } catch (ParseException ex) {
                        logger.info(ex.toString());
                    }
                }
                Gson gson = Global.getGsonObject();
                this.eventString = gson.toJson(listItem);
            }
        }
    }

    public void updateStartEndDate() {
        try {
            String str_beginDate = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("begin_date");
            String str_endDate = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("end_date");
            this.showMessage("Begin/End Date:" + str_beginDate + "/" + str_endDate, FacesMessage.SEVERITY_INFO);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.beginDate = format.parse(str_beginDate);
            this.endDate = format.parse(str_endDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            cal.add(Calendar.DATE, -1);
            this.endDate = cal.getTime();
        } catch (ParseException ex) {
            logger.error(ex);
        }
    }
    
     public String getBeginDateInStr(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        if(this.beginDate!=null){
            return format.format(this.beginDate);
        }else{
            return format.format(new java.util.Date());
        }
    }

}
