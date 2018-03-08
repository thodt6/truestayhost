/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.unibro.api.Utils;
import com.unibro.model.BookType;
import com.unibro.model.CancelPolicy;
import com.unibro.utils.Global;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
public class BookTypeService {

    private String homestay_id;
    private List<BookType> booktype_list;
    private List<CancelPolicy> cancel_policy_list;
    private BookType selectedBookType;

    Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * @return the booktype_list
     */
    public List<BookType> getBooktype_list() {
        return booktype_list;
    }

    /**
     * @param booktype_list the booktype_list to set
     */
    public void setBooktype_list(List<BookType> booktype_list) {
        this.booktype_list = booktype_list;
    }

    /**
     * @return the cancel_policy_list
     */
    public List<CancelPolicy> getCancel_policy_list() {
        return cancel_policy_list;
    }

    /**
     * @param cancel_policy_list the cancel_policy_list to set
     */
    public void setCancel_policy_list(List<CancelPolicy> cancel_policy_list) {
        this.cancel_policy_list = cancel_policy_list;
    }

    public final void loadObject() {
        Gson gson = Global.getGsonObject();
        JsonObject obj = Utils.getBookType(getHomestay_id());
        if (obj.get("message").getAsString().equals("200")) {
            JsonElement data1 = obj.get("data").getAsJsonObject().get("list_homestay_booktype").getAsJsonArray();
            Type listType1 = new TypeToken<List<BookType>>() {
            }.getType();

            Type listType2 = new TypeToken<List<CancelPolicy>>() {
            }.getType();

            booktype_list = gson.fromJson(data1, listType1);
            JsonElement data2 = obj.get("data").getAsJsonObject().get("list_homestay_cancel_policy").getAsJsonArray();
            cancel_policy_list = gson.fromJson(data2, listType2);
            this.setDefaultBookType();
        } else {
            booktype_list = new ArrayList();
            cancel_policy_list = new ArrayList();
        }
    }

    public void setBookTypeIndex(int index) {
        for (BookType b : this.booktype_list) {
            b.setSelected(false);
        }
        BookType b = this.booktype_list.get(index);
        b.setSelected(true);
    }

    private void setDefaultBookType() {
        Boolean setDefaultBook = true;
        for (BookType b : this.booktype_list) {
            if (b.getSelected()) {
                setDefaultBook = false;
                this.selectedBookType = b;
                break;
            }
        }
        if (setDefaultBook) {
            this.setBookTypeIndex(0);
            this.selectedBookType = this.booktype_list.get(0);
            logger.info("ID selected:" + this.selectedBookType.getId());
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
     * @return the selectedBookType
     */
    public BookType getSelectedBookType() {
        return selectedBookType;
    }

    public void setBookType() {
        logger.info("Set book type:");
        for (BookType b : this.booktype_list) {
            b.setSelected(false);
        }
        this.selectedBookType.setSelected(true);
        logger.info("Set selet book type:" + this.selectedBookType.getId() + " " + this.selectedBookType.getSelected());
    }

    /**
     * @param selectedBookType the selectedBookType to set
     */
    public void setSelectedBookType(BookType selectedBookType) {
        for (BookType b : this.booktype_list) {
            b.setSelected(false);
        }
        this.selectedBookType = selectedBookType;
        logger.info("Selected booktype:" + selectedBookType.getName());
        this.selectedBookType.setSelected(true);
    }

    public String getSelectedBookTypeId() {
        for (BookType b : this.booktype_list) {
            if (b.getSelected()) {
                return b.getId();
            }
        }
        return null;
    }

    public String getCancelPolicyId() {
        for (CancelPolicy c : this.cancel_policy_list) {
            if (c.getSelected()) {
                return c.getId();
            }
        }
        return null;
    }
}
