/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.model;

import com.unibro.utils.Global;

/**
 *
 * @author THOND
 */
public class Image {

    private String id;
    private String imagetype;
    private String imageurl;
    private String homestayid;

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
     * @return the imagetype
     */
    public String getImagetype() {
        return imagetype;
    }

    /**
     * @param imagetype the imagetype to set
     */
    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }

    /**
     * @return the imageurl
     */
    public String getImageurl() {
        if (imageurl.startsWith("http")) {
            return imageurl;
        }
        Global.loadConfig();
        return Global.FILE_HTTP_PATH + "/" + imageurl;
    }

    /**
     * @param imageurl the imageurl to set
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    /**
     * @return the homestayid
     */
    public String getHomestayid() {
        return homestayid;
    }

    /**
     * @param homestayid the homestayid to set
     */
    public void setHomestayid(String homestayid) {
        this.homestayid = homestayid;
    }
}
