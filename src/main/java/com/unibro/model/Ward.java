/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.model;

/**
 *
 * @author THOND
 */
public class Ward {
    private String id;
    private String name;
    private String ward_type;
    private String location;
    private String districid;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the ward_type
     */
    public String getWard_type() {
        return ward_type;
    }

    /**
     * @param ward_type the ward_type to set
     */
    public void setWard_type(String ward_type) {
        this.ward_type = ward_type;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the districid
     */
    public String getDistricid() {
        return districid;
    }

    /**
     * @param districid the districid to set
     */
    public void setDistricid(String districid) {
        this.districid = districid;
    }
}
