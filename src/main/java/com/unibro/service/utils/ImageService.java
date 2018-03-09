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
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unibro.api.Utils;
import com.unibro.model.Image;
import com.unibro.utils.Global;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class ImageService implements Serializable {

    private String homestay_id = "";
    private List<Image> homestay_cover;
    private List<Image> homestay_images;

    private Image selectedCover;
    private Image selectedImage;

    Logger logger = Logger.getLogger(this.getClass().getName());

    public ImageService() {
//        this.loadObject();
    }

    private String root_url;

    public final void loadObject() {
        Global.loadConfig();
        this.setRoot_url(Global.getConfigValue("FILE_HTTP_PATH"));
        Gson gson = Global.getGsonObject();
        JsonObject obj = Utils.getHomeStayImages(homestay_id);
        if (obj.get("message").getAsString().equals("200")) {
            JsonElement data1 = obj.get("data").getAsJsonObject().get("homestay_cover");
            JsonElement data2 = obj.get("data").getAsJsonObject().get("homestay_images");

            Type listType = new TypeToken<List<com.unibro.model.Image>>() {
            }.getType();
            if (data1 != null) {
                homestay_cover = gson.fromJson(data1, listType);
            }
            if (data2 != null) {
                homestay_images = gson.fromJson(data2, listType);
            }

            if (homestay_cover == null) {
                homestay_cover = new ArrayList();
            }
            if (homestay_images == null) {
                homestay_images = new ArrayList();
            }
        } else {
            homestay_cover = new ArrayList();
            homestay_images = new ArrayList();
        }
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
     * @return the homestay_cover
     */
    public List<Image> getHomestay_cover() {
        return homestay_cover;
    }

    /**
     * @param homestay_cover the homestay_cover to set
     */
    public void setHomestay_cover(List<Image> homestay_cover) {
        this.homestay_cover = homestay_cover;
    }

    /**
     * @return the homestay_images
     */
    public List<Image> getHomestay_images() {
        return homestay_images;
    }

    /**
     * @param homestay_images the homestay_images to set
     */
    public void setHomestay_images(List<Image> homestay_images) {
        this.homestay_images = homestay_images;
    }

    public List<Image> getCoverImageList() {
        if (this.homestay_cover == null || this.homestay_cover.isEmpty()) {
            return new ArrayList();
        }
//        List<String> ret = new ArrayList();
        for (Image img : this.homestay_cover) {
            img.setImageurl(img.getImageurl().replace(getRoot_url() + "/", ""));
//            ret.add(img.getImageurl().replace(getRoot_url() + "/", ""));
        }
        return this.homestay_cover;
    }

    public List<Image> getImageList() {
        if (this.homestay_images == null || this.homestay_images.isEmpty()) {
            return new ArrayList();
        }
//        List<String> ret = new ArrayList();
        for (Image img : this.homestay_images) {
            img.setImageurl(img.getImageurl().replace(getRoot_url() + "/", ""));
//            ret.add(img.getImageurl().replace(getRoot_url() + "/", ""));
        }
        return homestay_images;
    }

    /**
     * @return the root_url
     */
    public String getRoot_url() {
        return root_url;
    }

    /**
     * @param root_url the root_url to set
     */
    public void setRoot_url(String root_url) {
        this.root_url = root_url;
    }

    /**
     * @return the selectedCover
     */
    public Image getSelectedCover() {
        return selectedCover;
    }

    /**
     * @param selectedCover the selectedCover to set
     */
    public void setSelectedCover(Image selectedCover) {
        this.selectedCover = selectedCover;
    }

    /**
     * @return the selectedImage
     */
    public Image getSelectedImage() {
        return selectedImage;
    }

    /**
     * @param selectedImage the selectedImage to set
     */
    public void setSelectedImage(Image selectedImage) {
        this.selectedImage = selectedImage;
    }

    public void removeSelectCover() {
        if (this.selectedCover != null) {
            this.homestay_cover.remove(this.selectedCover);
        }
    }

    public void removeSelectImage() {
        if (this.selectedImage != null) {
            this.homestay_images.remove(this.selectedImage);
        }
    }

}
