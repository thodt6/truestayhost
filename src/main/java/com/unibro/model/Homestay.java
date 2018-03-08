/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.model;

import com.google.gson.JsonObject;
import com.unibro.api.Listing;
import com.unibro.service.UserSessionBean;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author THOND
 */
public class Homestay implements Serializable {

    private Boolean isNewHomeStay = false;
    private String homestay_id;
    private String propertytypeid;
    private String roomtypeid;
    private int adults_num = 1;
    private int children_num = 0;
    private int infants_num = 0;
    private int bath_room_num = 0;
    private int bed_room_num = 1;
    private int single_bed_num = 0;
    private int double_bed_num = 0;
    private String longitude;
    private String latitude;
    private String address_full;
    private String province_id;
    private String district_id;
    private String ward_id;
    private int standard_guest_num = 0;
    private int max_guest_num = 0;
    private List<String> amenity_ids;
    private List<String> safety_amenity_ids;
    private List<String> spaceuse_ids;
    private List<String> homestay_cover_url;
    private List<String> homestay_image_url;

    private List<String> homestay_house_rule_ids;
    private List<String> homestay_knowabout_ids;
    private List<String> guest_requirement_ids;

    private String book_type_id;
    private String cancel_policy_id;

    private Integer before_time = 1;
    private Integer far_time = 3;
    private Integer stay_max = 7;
    private Integer stay_min = 1;
    private Boolean is_longturn = false;
    private Boolean is_lastminute = false;

    private String title;
    private String description;
    private List<String> great_for_ids;

    private Double was_price = 0.0;
    private Double lastminute_price = 0.0;
    private String currency = "VND";
    private Integer weekly_discount = 0;
    private Integer monthly_discount = 0;
    private Integer longturn_discount = 1;
    private List<ExtraCharge> list_extra_charges;

    private java.util.Date checkin;
    private java.util.Date checkout;

    private int reviewnum;
    private int reviewstar;
    private String hosting_date;
    private String last_update;
    private String state = "P";
    private String cover_url;
    private int step_num;
    private String step_name;

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
     * @return the property
     */
    public String getPropertytypeid() {
        return propertytypeid;
    }

    /**
     * @param propertytypeid the property to set
     */
    public void setPropertytypeid(String propertytypeid) {
        this.propertytypeid = propertytypeid;
    }

    /**
     * @return the room_type
     */
    public String getRoomtypeid() {
        return roomtypeid;
    }

    /**
     * @param roomtypeid the room_type to set
     */
    public void setRoomtypeid(String roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    /**
     * @return the adults_num
     */
    public int getAdults_num() {
        return adults_num;
    }

    /**
     * @param adults_num the adults_num to set
     */
    public void setAdults_num(int adults_num) {
        this.adults_num = adults_num;
    }

    /**
     * @return the bath_room_num
     */
    public int getBath_room_num() {
        return bath_room_num;
    }

    /**
     * @param bath_room_num the bath_room_num to set
     */
    public void setBath_room_num(int bath_room_num) {
        this.bath_room_num = bath_room_num;
    }

    /**
     * @return the bed_room_num
     */
    public int getBed_room_num() {
        return bed_room_num;
    }

    /**
     * @param bed_room_num the bed_room_num to set
     */
    public void setBed_room_num(int bed_room_num) {
        this.bed_room_num = bed_room_num;
    }

    /**
     * @return the single_bed_num
     */
    public int getSingle_bed_num() {
        return single_bed_num;
    }

    /**
     * @param single_bed_num the single_bed_num to set
     */
    public void setSingle_bed_num(int single_bed_num) {
        this.single_bed_num = single_bed_num;
    }

    /**
     * @return the double_bed_num
     */
    public int getDouble_bed_num() {
        return double_bed_num;
    }

    /**
     * @param double_bed_num the double_bed_num to set
     */
    public void setDouble_bed_num(int double_bed_num) {
        this.double_bed_num = double_bed_num;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longtitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the lattitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the address_full
     */
    public String getAddress_full() {
        return address_full;
    }

    /**
     * @param address_full the address_full to set
     */
    public void setAddress_full(String address_full) {
        this.address_full = address_full;
    }

    /**
     * @return the province_id
     */
    public String getProvince_id() {
        return province_id;
    }

    /**
     * @param province_id the province_id to set
     */
    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    /**
     * @return the district_id
     */
    public String getDistrict_id() {
        return district_id;
    }

    /**
     * @param district_id the district_id to set
     */
    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    /**
     * @return the ward_id
     */
    public String getWard_id() {
        return ward_id;
    }

    /**
     * @param ward_id the ward_id to set
     */
    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }

    /**
     * @return the amenities
     */
    public List<String> getAmenity_ids() {
        return amenity_ids;
    }

    /**
     * @param amenity_ids the amenities to set
     */
    public void setAmenity_ids(List<String> amenity_ids) {
        this.amenity_ids = amenity_ids;
    }

    /**
     * @return the spaceuse_ids
     */
    public List<String> getSpaceuse_ids() {
        return spaceuse_ids;
    }

    /**
     * @param spaceuse_ids the spaceuse_ids to set
     */
    public void setSpaceuse_ids(List<String> spaceuse_ids) {
        this.spaceuse_ids = spaceuse_ids;
    }

    /**
     * @return the homestay_image_ids
     */
    public List<String> getHomestay_image_url() {
        return homestay_image_url;
    }

    /**
     * @param homestay_image_url the homestay_image_url to set
     */
    public void setHomestay_image_url(List<String> homestay_image_url) {
        this.homestay_image_url = homestay_image_url;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the great_fors
     */
    public List<String> getGreat_for_ids() {
        return great_for_ids;
    }

    /**
     * @param great_for_ids the great_for_ids to set
     */
    public void setGreat_for_ids(List<String> great_for_ids) {
        this.great_for_ids = great_for_ids;
    }

    /**
     * @return the safety_amenity_ids
     */
    public List<String> getSafety_amenity_ids() {
        return safety_amenity_ids;
    }

    /**
     * @param safety_amenity_ids the safety_amenity_ids to set
     */
    public void setSafety_amenity_ids(List<String> safety_amenity_ids) {
        this.safety_amenity_ids = safety_amenity_ids;
    }

    /**
     * @return the homestay_cover_url
     */
    public List<String> getHomestay_cover_url() {
        return homestay_cover_url;
    }

    /**
     * @param homestay_cover_url the homestay_cover_url to set
     */
    public void setHomestay_cover_url(List<String> homestay_cover_url) {
        this.homestay_cover_url = homestay_cover_url;
    }

    /**
     * @return the homestay_house_rule_ids
     */
    public List<String> getHomestay_house_rule_ids() {
        return homestay_house_rule_ids;
    }

    /**
     * @param homestay_house_rule_ids the homestay_house_rule_ids to set
     */
    public void setHomestay_house_rule_ids(List<String> homestay_house_rule_ids) {
        this.homestay_house_rule_ids = homestay_house_rule_ids;
    }

    /**
     * @return the homestay_knowabout_ids
     */
    public List<String> getHomestay_knowabout_ids() {
        return homestay_knowabout_ids;
    }

    /**
     * @param homestay_knowabout_ids the homestay_knowabout_ids to set
     */
    public void setHomestay_knowabout_ids(List<String> homestay_knowabout_ids) {
        this.homestay_knowabout_ids = homestay_knowabout_ids;
    }

    /**
     * @return the guest_requirement_ids
     */
    public List<String> getGuest_requirement_ids() {
        return guest_requirement_ids;
    }

    /**
     * @param guest_requirement_ids the guest_requirement_ids to set
     */
    public void setGuest_requirement_ids(List<String> guest_requirement_ids) {
        this.guest_requirement_ids = guest_requirement_ids;
    }

    /**
     * @return the book_type_id
     */
    public String getBook_type_id() {
        return book_type_id;
    }

    /**
     * @param book_type_id the book_type_id to set
     */
    public void setBook_type_id(String book_type_id) {
        this.book_type_id = book_type_id;
    }

    /**
     * @return the cancel_policy_id
     */
    public String getCancel_policy_id() {
        return cancel_policy_id;
    }

    /**
     * @param cancel_policy_id the cancel_policy_id to set
     */
    public void setCancel_policy_id(String cancel_policy_id) {
        this.cancel_policy_id = cancel_policy_id;
    }

    /**
     * @return the before_time
     */
    public Integer getBefore_time() {
        return before_time;
    }

    /**
     * @param before_time the before_time to set
     */
    public void setBefore_time(Integer before_time) {
        this.before_time = before_time;
    }

    /**
     * @return the far_time
     */
    public Integer getFar_time() {
        return far_time;
    }

    /**
     * @param far_time the far_time to set
     */
    public void setFar_time(Integer far_time) {
        this.far_time = far_time;
    }

    /**
     * @return the stay_max
     */
    public Integer getStay_max() {
        return stay_max;
    }

    /**
     * @param stay_max the stay_max to set
     */
    public void setStay_max(Integer stay_max) {
        this.stay_max = stay_max;
    }

    /**
     * @return the stay_min
     */
    public Integer getStay_min() {
        return stay_min;
    }

    /**
     * @param stay_min the stay_min to set
     */
    public void setStay_min(Integer stay_min) {
        this.stay_min = stay_min;
    }

    /**
     * @return the was_price
     */
    public Double getWas_price() {
        return was_price;
    }

    /**
     * @param was_price the was_price to set
     */
    public void setWas_price(Double was_price) {
        this.was_price = was_price;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the weekly_discount
     */
    public Integer getWeekly_discount() {
        return weekly_discount;
    }

    /**
     * @param weekly_discount the weekly_discount to set
     */
    public void setWeekly_discount(Integer weekly_discount) {
        this.weekly_discount = weekly_discount;
    }

    /**
     * @return the monthly_discount
     */
    public Integer getMonthly_discount() {
        return monthly_discount;
    }

    /**
     * @param monthly_discount the monthly_discount to set
     */
    public void setMonthly_discount(Integer monthly_discount) {
        this.monthly_discount = monthly_discount;
    }

    /**
     * @return the list_extra_charges
     */
    public List<ExtraCharge> getList_extra_charges() {
        return list_extra_charges;
    }

    /**
     * @param list_extra_charges the list_extra_ExtraChargecharges to set
     */
    public void setList_extra_charges(List<ExtraCharge> list_extra_charges) {
        this.list_extra_charges = list_extra_charges;
    }

    /**
     * @return the isNewHomeStay
     */
    public Boolean getIsNewHomeStay() {
        return isNewHomeStay;
    }

    /**
     * @param isNewHomeStay the isNewHomeStay to set
     */
    public void setIsNewHomeStay(Boolean isNewHomeStay) {
        this.isNewHomeStay = isNewHomeStay;
    }

    /**
     * @return the children_num
     */
    public int getChildren_num() {
        return children_num;
    }

    /**
     * @param children_num the children_num to set
     */
    public void setChildren_num(int children_num) {
        this.children_num = children_num;
    }

    /**
     * @return the infants_num
     */
    public int getInfants_num() {
        return infants_num;
    }

    /**
     * @param infants_num the infants_num to set
     */
    public void setInfants_num(int infants_num) {
        this.infants_num = infants_num;
    }

    /**
     * @return the checkin
     */
    public java.util.Date getCheckin() {
        return checkin;
    }

    /**
     * @param checkin the checkin to set
     */
    public void setCheckin(java.util.Date checkin) {
        this.checkin = checkin;
    }

    /**
     * @return the checkout
     */
    public java.util.Date getCheckout() {
        return checkout;
    }

    /**
     * @param checkout the checkout to set
     */
    public void setCheckout(java.util.Date checkout) {
        this.checkout = checkout;
    }

    /**
     * @return the reviewnum
     */
    public int getReviewnum() {
        return reviewnum;
    }

    /**
     * @param reviewnum the reviewnum to set
     */
    public void setReviewnum(int reviewnum) {
        this.reviewnum = reviewnum;
    }

    /**
     * @return the reviewstar
     */
    public int getReviewstar() {
        return reviewstar;
    }

    /**
     * @param reviewstar the reviewstar to set
     */
    public void setReviewstar(int reviewstar) {
        this.reviewstar = reviewstar;
    }

    /**
     * @return the hosting_date
     */
    public String getHosting_date() {
        return hosting_date;
    }

    /**
     * @param hosting_date the hosting_date to set
     */
    public void setHosting_date(String hosting_date) {
        this.hosting_date = hosting_date;
    }

    /**
     * @return the last_update
     */
    public String getLast_update() {
        return last_update;
    }

    /**
     * @param last_update the last_update to set
     */
    public void setLast_update(String last_update) {
        this.last_update = last_update;
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
     * @return the cover_url
     */
    public String getCover_url() {
        return cover_url;
    }

    /**
     * @param cover_url the cover_url to set
     */
    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    /**
     * @return the step_num
     */
    public int getStep_num() {
        return step_num;
    }

    /**
     * @param step_num the step_num to set
     */
    public void setStep_num(int step_num) {
        this.step_num = step_num;
    }

    /**
     * @return the step_name
     */
    public String getStep_name() {
        return step_name;
    }

    /**
     * @param step_name the step_name to set
     */
    public void setStep_name(String step_name) {
        this.step_name = step_name;
    }

    /**
     * @return the is_longturn
     */
    public Boolean getIs_longturn() {
        return is_longturn;
    }

    /**
     * @param is_longturn the is_longturn to set
     */
    public void setIs_longturn(Boolean is_longturn) {
        this.is_longturn = is_longturn;
    }

    /**
     * @return the is_lastminute
     */
    public Boolean getIs_lastminute() {
        return is_lastminute;
    }

    /**
     * @param is_lastminute the is_lastminute to set
     */
    public void setIs_lastminute(Boolean is_lastminute) {
        this.is_lastminute = is_lastminute;
    }

    /**
     * @return the longturn_discount
     */
    public Integer getLongturn_discount() {
        return longturn_discount;
    }

    /**
     * @param longturn_discount the longturn_discount to set
     */
    public void setLongturn_discount(Integer longturn_discount) {
        this.longturn_discount = longturn_discount;
    }

    /**
     * @return the lastminute_price
     */
    public Double getLastminute_price() {
        return lastminute_price;
    }

    /**
     * @param lastminute_price the lastminute_price to set
     */
    public void setLastminute_price(Double lastminute_price) {
        this.lastminute_price = lastminute_price;
    }

    public void increaseAdultNum() {
        this.adults_num++;
        this.max_guest_num = this.adults_num + this.children_num + this.infants_num;
    }

    public void discreaseAdultNum() {
        this.adults_num--;
        if (this.adults_num < 0) {
            this.adults_num = 0;
        }
        this.max_guest_num = this.adults_num + this.children_num + this.infants_num;
    }

    public void increaseChildrenNum() {
        this.children_num++;
        this.max_guest_num = this.adults_num + this.children_num + this.infants_num;
    }

    public void decreaseChildrenNum() {
        this.children_num--;
        if (this.children_num < 0) {
            this.children_num = 0;
        }
        this.max_guest_num = this.adults_num + this.children_num + this.infants_num;
    }

    public void increaseInfantNum() {
        this.infants_num++;
        this.max_guest_num = this.adults_num + this.children_num + this.infants_num;
    }

    public void descreaseInfantNum() {
        this.infants_num--;
        if (this.infants_num < 0) {
            this.infants_num = 0;
        }
        this.max_guest_num = this.adults_num + this.children_num + this.infants_num;
    }

    public void increaseSingleBedNum() {
        this.single_bed_num++;
        this.max_guest_num = this.adults_num + this.children_num + this.infants_num;
    }

    public void descreaseSingleBedNum() {
        this.single_bed_num--;
        if (this.single_bed_num < 0) {
            this.single_bed_num = 0;
        }
    }

    public void increaseDoubleBedNum() {
        this.double_bed_num++;
    }

    public void descreaseDoubleBedNum() {
        this.double_bed_num--;
        if (this.double_bed_num < 0) {
            this.double_bed_num = 0;
        }
    }

    public void increaseBathRoom() {
        this.bath_room_num++;
    }

    public void decreaseBathRoom() {
        this.bath_room_num--;
        if (this.bath_room_num < 0) {
            this.bath_room_num = 0;
        }
    }

    /**
     * @return the standard_guest_num
     */
    public int getStandard_guest_num() {
        return standard_guest_num;
    }

    /**
     * @param standard_guest_num the standard_guest_num to set
     */
    public void setStandard_guest_num(int standard_guest_num) {
        this.standard_guest_num = standard_guest_num;
    }

    /**
     * @return the max_guest_num
     */
    public int getMax_guest_num() {
        return max_guest_num;
    }

    /**
     * @param max_guest_num the max_guest_num to set
     */
    public void setMax_guest_num(int max_guest_num) {
        this.max_guest_num = max_guest_num;
    }

    private Integer stay_max_week = 1;

    /**
     * @return the stay_max_week
     */
    public Integer getStay_max_week() {
        return stay_max_week;
    }

    /**
     * @param stay_max_week the stay_max_week to set
     */
    public void setStay_max_week(Integer stay_max_week) {
        this.stay_max_week = stay_max_week;
    }

    public void increaseStayMin() {
        this.stay_min++;
    }

    public void discreaseStayMin() {
        this.stay_min--;
        if (this.stay_min < 0) {
            this.stay_min = 0;
        }
    }

    public void increaseStayMaxWeek() {
        this.stay_max_week++;
    }

    public void discreaseStayMaxWeek() {
        this.stay_max_week--;
        if (this.stay_max_week < 0) {
            this.stay_max_week = 0;
        }
    }

    public boolean publishHomeStay() {
//        if (UserSessionBean.getUserSession().getUser().getVerified()) {
            JsonObject obj = Listing.update_homestay_state(UserSessionBean.getUserSession().getToken_key(), this.getHomestay_id(), "A");
            if (obj.get("message").getAsString().equals("200")) {
                return true;
            }
//        }
        return false;
    }
    public boolean unPublistHomeStay() {
//        if (UserSessionBean.getUserSession().getUser().getVerified()) {
            JsonObject obj = Listing.update_homestay_state(UserSessionBean.getUserSession().getToken_key(), this.getHomestay_id(), "P");
            if (obj.get("message").getAsString().equals("200")) {
                return true;
            }
//        }
        return false;
    }

}
