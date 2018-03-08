/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.model.Homestay;
import com.unibro.model.Image;
import com.unibro.service.UserSessionBean;
import com.unibro.service.utils.ImageService;
import com.unibro.utils.Global;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author THOND
 */
public class Listing5_ImageService extends AbstractListingService {

    private ImageService imageService = new ImageService();

    public Listing5_ImageService(Homestay homestay, String username) {
        super(homestay, username);
    }

    @Override
    public boolean doService() {
        this.getHomestay().setHomestay_cover_url(this.getImageService().getCoverImageList());
        this.getHomestay().setHomestay_image_url(this.getImageService().getImageList());
        JsonObject ret = Listing.updateHomeStayImages(UserSessionBean.getUserSession().getUser().getUsername(), getHomestay().getHomestay_id(), getHomestay().getHomestay_cover_url(), getHomestay().getHomestay_image_url());
        return ret.get("message").getAsString().equals("200");
    }

    @Override
    public void initService() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            this.setPercentComplete(5 * 100 / this.num_step);
            this.getImageService().setHomestay_id(this.getHomestay().getHomestay_id());
            this.getImageService().loadObject();
        }
//        this.getHomestay().setHomestay_image_url(this.);
    }

    @Override
    public void nextPage() {
        if (this.doService()) {
            this.redirectToPage("/portal/listing/become-a-host/description.html");
        } else {
            this.showMessage("Server is down", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void backPage() {
        this.redirectToPage("/portal/listing/become-a-host/spaces.html");
    }

    public void handleCoverImageUpload(FileUploadEvent event) {
        Global.loadConfig();
        UploadedFile f = event.getFile();
        String filename = f.getFileName();
        logger.info("File:" + filename);
        String prefix = Global.getPrefixFileName(filename);
        String tail = Global.getTailFile(filename);
        filename = Global.convertStringFilename(prefix) + "." + tail;
        File folder = new File(Global.FILE_ROOT_PATH + "/" + UserSessionBean.getUserSession().getUser().getId() + "/cover");
        folder.mkdirs();
        File saveFile = Global.getNewFileName(new File(Global.FILE_ROOT_PATH + "/" + UserSessionBean.getUserSession().getUser().getId() + "/cover/" + filename));
        logger.info(saveFile.getAbsolutePath());
        try {
            final int BUFFER_SIZE = 1024;
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = f.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            Image img = new Image();
            img.setHomestayid(this.getHomestay().getHomestay_id());
            img.setId(Global.getRandomString());
            img.setImagetype("0");
            img.setImageurl(UserSessionBean.getUserSession().getUser().getId() + "/cover/" + filename);
            this.imageService.getHomestay_cover().add(img);
        } catch (IOException ex) {
            logger.error("Error:" + ex);
        }
    }

    public void handleImageUpload(FileUploadEvent event) {
        Global.loadConfig();
        UploadedFile f = event.getFile();
        String filename = f.getFileName();
        logger.info("File:" + filename);
        String prefix = Global.getPrefixFileName(filename);
        String tail = Global.getTailFile(filename);
        filename = Global.convertStringFilename(prefix) + "." + tail;
        File folder = new File(Global.FILE_ROOT_PATH + "/" + UserSessionBean.getUserSession().getUser().getId() + "/image");
        folder.mkdirs();
        File saveFile = Global.getNewFileName(new File(Global.FILE_ROOT_PATH + "/" + UserSessionBean.getUserSession().getUser().getId() + "/image/" + filename));
        logger.info(saveFile.getAbsolutePath());
        try {
            final int BUFFER_SIZE = 1024;
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = f.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
//            this.getHomestay().getHomestay_image_url().add(UserSessionBean.getUserSession().getUser().getId() + "/image/" + filename);
            Image img = new Image();
            img.setHomestayid(this.getHomestay().getHomestay_id());
            img.setId(Global.getRandomString());
            img.setImagetype("1");
            img.setImageurl(UserSessionBean.getUserSession().getUser().getId() + "/image/" + filename);
            this.imageService.getHomestay_images().add(img);
        } catch (IOException ex) {
            logger.error("Error:" + ex);
        }
    }

    /**
     * @return the imageService
     */
    public ImageService getImageService() {
        return imageService;
    }

    /**
     * @param imageService the imageService to set
     */
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

}
