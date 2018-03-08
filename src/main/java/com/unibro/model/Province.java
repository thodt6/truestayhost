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
public class Province {
   private String id;
   private String name;
   private String province_type;

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
     * @return the province_type
     */
    public String getProvince_type() {
        return province_type;
    }

    /**
     * @param province_type the province_type to set
     */
    public void setProvince_type(String province_type) {
        this.province_type = province_type;
    }
}
