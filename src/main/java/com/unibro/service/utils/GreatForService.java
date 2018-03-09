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
import com.unibro.model.GreatFor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")

public class GreatForService implements Serializable {
    
    private String homestay_id="";

    private List<GreatFor> objects;

    
    public GreatForService(){
//        this.loadObject();
    }
    
    /**
     * @return the objects
     */
    public List<GreatFor> getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List<GreatFor> objects) {
        this.objects = objects;
    }

    public final void loadObject() {
        this.objects = Utils.getGreatFor(this.getHomestay_id());
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
    
    public List<GreatFor> getSelectedGreatFor() {
        if(this.objects==null||this.objects.isEmpty()){
            return new ArrayList();
        }
        List<GreatFor> ret = new ArrayList();
        for (GreatFor gf : this.objects) {
            if (gf.getSelected()) {
                ret.add(gf);
            }
        }
        return ret;
    }
    
}
