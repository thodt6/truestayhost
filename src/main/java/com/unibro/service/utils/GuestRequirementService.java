/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

/**
 *
 * @author THOND
 */
import com.unibro.api.Utils;
import com.unibro.model.GuestRequirement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class GuestRequirementService implements Serializable {

    private String homestay_id = "";

    private List<GuestRequirement> objects;

    public GuestRequirementService() {
//        this.loadObject();
    }

    /**
     * @return the objects
     */
    public List<GuestRequirement> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<GuestRequirement> objects) {
        this.objects = objects;
    }

    public final void loadObject() {
        this.objects = Utils.getGuestRequirementList(this.getHomestay_id());
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

    public List<GuestRequirement> getSelectedId() {
        List ret = new ArrayList();
        for (GuestRequirement g : this.objects) {
            if (g.getSelected()) {
                ret.add(g);
            }
        }
        return ret;
    }
}
