/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

import com.unibro.api.Listing;
import com.unibro.model.HomestayList;
import com.unibro.service.UserSessionBean;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
@SuppressWarnings("serial")
public class HomestayListService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private List<HomestayList> objects;

    public void loadObjects() {
        this.objects = Listing.getHomestayList(UserSessionBean.getUserSession().getUser().getUsername(), "");
        logger.info(this.objects.size());
    }

    public void printMyHomeStay() {
        for (HomestayList home : this.objects) {
            logger.info(home.getHomestay_id() + ":" + home.getState());
        }
    }

    public HomestayList getHomestayList(String homestayid) {
        if (this.objects != null) {
            for (HomestayList h : this.objects) {
                if (h.getHomestay_id().equals(homestayid)) {
                    return h;
                }
            }
        }
        return null;
    }

    public List<HomestayList> getHomeStayByState(String state) {
        List ret = new ArrayList();
        if (objects == null || objects.isEmpty()) {
            return ret;
        }
        for (HomestayList home : objects) {
            if (home.getState().equals(state)) {
                ret.add(home);
            }
        }
        return ret;
    }

    public List<String> getHomeStayIdList(List<HomestayList> list) {
        List<String> homestayid_list = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (HomestayList h : list) {
                homestayid_list.add(h.getHomestay_id());
            }
        }
        return homestayid_list;
    }

    /**
     * @return the objects
     */
    public List<HomestayList> getObjects() {
        return objects;
    }

    public List<HomestayList> getActiveObjects() {
        List<HomestayList> ret = new ArrayList();
        if (objects != null && !objects.isEmpty()) {
            for (HomestayList home : objects) {
                if (home.getState().equals("A")) {
                    ret.add(home);
                }
            }
        }
        return ret;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<HomestayList> objects) {
        this.objects = objects;
    }
}
