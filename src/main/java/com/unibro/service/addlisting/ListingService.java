/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.addlisting;

import com.unibro.api.Listing;
import com.unibro.model.Homestay;
import com.unibro.model.HomestayList;
import com.unibro.service.UserSessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author THOND
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ListingService implements Serializable {

    private Homestay homestay;
    private String homestayid;

    Logger logger = Logger.getLogger(this.getClass().getName());

    private Listing0_HomestayTypeService listing0 = new Listing0_HomestayTypeService();
    private Listing1_BedroomService listing1;
    private Listing2_LocationService listing2;
    private Listing3_AmenitiesService listing3;
    private Listing4_SpacesService listing4;
    private Listing5_ImageService listing5;
    private Listing6_DescriptionService listing6;
//    private Listing7_UpdateContactService listing7;
//    private Listing8_ConfirmContactService listing8;
    private Listing7_HouseRuleService listing9;
    private Listing8_GuestRequirementsService listing10;
    private Listing9_HowToBookService listing11;
    private Listing10_DateTimeSettingService listing12;
    private Listing11_CalendarIntroService listing13;
    private Listing12_CalendarService listing14;
    private Listing13_PriceSettingService listing15;
    private Listing14_TaxService listing16;
    private int step=0;

    private void initHomestayService() {
        logger.info("Init homestay:" + this.getHomestayid());
//        if (homestayid == null || this.homestayid.equals("") || this.homestayid.equals("new-homestay")) {
        if (this.homestayid.equals("new-homestay")) {
            logger.info("New Homestay:" + homestayid);
            this.homestay = new Homestay();
            this.homestay.setIsNewHomeStay(true);
            this.homestay.setHomestay_image_url(new ArrayList());
            this.homestay.setHomestay_cover_url(new ArrayList());
        } else {
            if (this.homestayid.equals("default-homestay")) {
                List<HomestayList> objects = Listing.getHomestayList(UserSessionBean.getUserSession().getUser().getUsername(), "");
                if (objects != null && !objects.isEmpty()) {
                    for (HomestayList home : objects) {
                        if (home.getState().equals("A")) {
                            this.homestay = Listing.getHomestayDetail(home.getHomestay_id(), UserSessionBean.getUserSession().getUser().getLanguage_code());
                            if (homestay != null) {
                                this.homestay.setIsNewHomeStay(false);
                                break;
                            }
                        }
                    }
                }
            } else {
                logger.info("Load homestay from server:" + homestayid);
                //Load home stay from server
                this.homestay = Listing.getHomestayDetail(this.getHomestayid(), UserSessionBean.getUserSession().getUser().getLanguage_code());
                if (this.homestay != null) {
                    this.homestay.setIsNewHomeStay(false);
                    this.homestay.setStep_num(step);
                    this.homestay.setStay_max_week(this.homestay.getStay_max()/7);
                } else {
                    logger.info("Homestay null");
                    this.homestay = null;
                }
            }
        }
        if (homestay == null) {
            return;
        }
        if (getListing0() == null) {
            setListing0(new Listing0_HomestayTypeService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing0().setHomestay(this.getHomestay());
            getListing0().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing1() == null) {
            setListing1(new Listing1_BedroomService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing1().setHomestay(this.getHomestay());
            getListing1().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing2() == null) {
            setListing2(new Listing2_LocationService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing2().setHomestay(this.getHomestay());
            getListing2().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing3() == null) {
            setListing3(new Listing3_AmenitiesService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing3().setHomestay(this.getHomestay());
            getListing3().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing4() == null) {
            setListing4(new Listing4_SpacesService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing4().setHomestay(this.getHomestay());
            getListing4().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing5() == null) {
            setListing5(new Listing5_ImageService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing5().setHomestay(this.getHomestay());
            getListing5().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing6() == null) {
            setListing6(new Listing6_DescriptionService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing6().setHomestay(this.getHomestay());
            getListing6().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
//        if (getListing7() == null) {
//            setListing7(new Listing7_UpdateContactService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
//        } else {
//            getListing7().setHomestay(this.getHomestay());
//            getListing7().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
//        }
//        if (getListing8() == null) {
//            setListing8(new Listing8_ConfirmContactService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
//        } else {
//            getListing8().setHomestay(this.getHomestay());
//            getListing8().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
//        }
        if (getListing9() == null) {
            setListing9(new Listing7_HouseRuleService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing9().setHomestay(this.getHomestay());
            getListing9().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing10() == null) {
            setListing10(new Listing8_GuestRequirementsService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing10().setHomestay(this.getHomestay());
            getListing10().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing11() == null) {
            setListing11(new Listing9_HowToBookService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing11().setHomestay(this.getHomestay());
            getListing11().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing12() == null) {
            setListing12(new Listing10_DateTimeSettingService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing12().setHomestay(this.getHomestay());
            getListing12().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing13() == null) {
            setListing13(new Listing11_CalendarIntroService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing13().setHomestay(this.getHomestay());
            getListing13().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing14() == null) {
            setListing14(new Listing12_CalendarService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing14().setHomestay(this.getHomestay());
            getListing14().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing15() == null) {
            setListing15(new Listing13_PriceSettingService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing15().setHomestay(this.getHomestay());
            getListing15().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing16() == null) {
            setListing16(new Listing14_TaxService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing16().setHomestay(this.getHomestay());
            getListing16().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
        if (getListing1() == null) {
            setListing1(new Listing1_BedroomService(homestay, UserSessionBean.getUserSession().getUser().getUsername()));
        } else {
            getListing1().setHomestay(this.getHomestay());
            getListing1().setUsername(UserSessionBean.getUserSession().getUser().getUsername());
        }
    }

    public void initHomestay() {
        this.initHomestayService();
        if (this.homestay.getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/become-a-host/room.html");
        } else {
            //Redirect to the step
            switch (this.homestay.getStep_num()) {
                case 0:
                    this.redirectToPage("/portal/listing/become-a-host/bedroom.html");
                    break;
                case 1:
                    this.redirectToPage("/portal/listing/become-a-host/location.html");
                    break;
                case 2:
                    this.redirectToPage("/portal/listing/become-a-host/amenities.html");
                    break;
                case 3:
                    this.redirectToPage("/portal/listing/become-a-host/spaces.html");
                    break;
                case 4:
                    this.redirectToPage("/portal/listing/become-a-host/photos.html");
                    break;
                case 5:
                    this.redirectToPage("/portal/listing/become-a-host/description.html");
                    break;
                case 6:
//                    this.redirectToPage("/portal/listing/become-a-host/contact-info.html");
                    this.redirectToPage("/portal/listing/become-a-host/house-rule.html");
                    break;
                case 7:
//                    this.redirectToPage("/portal/listing/become-a-host/contact-confirm.html");
                    this.redirectToPage("/portal/listing/become-a-host/house-rule.html");
                    break;
                case 8:
                    this.redirectToPage("/portal/listing/become-a-host/house-rule.html");
                    break;
                case 9:
                    this.redirectToPage("/portal/listing/become-a-host/guest-requirements.html");
                    break;
                case 10:
                    this.redirectToPage("/portal/listing/become-a-host/howto-book.html");
                    break;
                case 11:
                    this.redirectToPage("/portal/listing/become-a-host/stay-setting.html");
                    break;
                case 12:
                    this.redirectToPage("/portal/listing/become-a-host/calendar-intro.html");
                    break;
                case 13:
                    this.redirectToPage("/portal/listing/become-a-host/calendar.html");
                    break;
                case 14:
                    this.redirectToPage("/portal/listing/become-a-host/price-setting.html");
                    break;
                case 15:
                    this.redirectToPage("/portal/listing/become-a-host/tax.html");
                    break;

                default:
                    this.redirectToPage("/portal/listing/become-a-host/room.html");
                    break;
            }

        }
    }

    public void updateHomestay() {
        this.initHomestayService();
        if (this.homestay.getIsNewHomeStay()) {
            this.redirectToPage("/portal/listing/become-a-host/room.html");
        } else {
            //Redirect to the step
            this.redirectToPage("/portal/listing/update-your-listing/room.html");
        }
    }

    private void redirectToPage(String uri) {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + uri);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    /**
     * @return the homestay
     */
    public Homestay getHomestay() {
        return homestay;
    }

    /**
     * @param homestay the homestay to set
     */
    public void setHomestay(Homestay homestay) {
        this.homestay = homestay;
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

    /**
     * @return the listing0
     */
    public Listing0_HomestayTypeService getListing0() {
        return listing0;
    }

    /**
     * @param listing0 the listing0 to set
     */
    public void setListing0(Listing0_HomestayTypeService listing0) {
        this.listing0 = listing0;
    }

    /**
     * @return the listing1
     */
    public Listing1_BedroomService getListing1() {
        return listing1;
    }

    /**
     * @param listing1 the listing1 to set
     */
    public void setListing1(Listing1_BedroomService listing1) {
        this.listing1 = listing1;
    }

    /**
     * @return the listing2
     */
    public Listing2_LocationService getListing2() {
        return listing2;
    }

    /**
     * @param listing2 the listing2 to set
     */
    public void setListing2(Listing2_LocationService listing2) {
        this.listing2 = listing2;
    }

    /**
     * @return the listing3
     */
    public Listing3_AmenitiesService getListing3() {
        return listing3;
    }

    /**
     * @param listing3 the listing3 to set
     */
    public void setListing3(Listing3_AmenitiesService listing3) {
        this.listing3 = listing3;
    }

    /**
     * @return the listing4
     */
    public Listing4_SpacesService getListing4() {
        return listing4;
    }

    /**
     * @param listing4 the listing4 to set
     */
    public void setListing4(Listing4_SpacesService listing4) {
        this.listing4 = listing4;
    }

    /**
     * @return the listing5
     */
    public Listing5_ImageService getListing5() {
        return listing5;
    }

    /**
     * @param listing5 the listing5 to set
     */
    public void setListing5(Listing5_ImageService listing5) {
        this.listing5 = listing5;
    }

    /**
     * @return the listing6
     */
    public Listing6_DescriptionService getListing6() {
        return listing6;
    }

    /**
     * @param listing6 the listing6 to set
     */
    public void setListing6(Listing6_DescriptionService listing6) {
        this.listing6 = listing6;
    }

//    /**
//     * @return the listing7
//     */
//    public Listing7_UpdateContactService getListing7() {
//        return listing7;
//    }
//
//    /**
//     * @param listing7 the listing7 to set
//     */
//    public void setListing7(Listing7_UpdateContactService listing7) {
//        this.listing7 = listing7;
//    }
//    /**
//     * @return the listing8
//     */
//    public Listing8_ConfirmContactService getListing8() {
//        return listing8;
//    }
//
//    /**
//     * @param listing8 the listing8 to set
//     */
//    public void setListing8(Listing8_ConfirmContactService listing8) {
//        this.listing8 = listing8;
//    }
    /**
     * @return the listing9
     */
    public Listing7_HouseRuleService getListing9() {
        return listing9;
    }

    /**
     * @param listing9 the listing9 to set
     */
    public void setListing9(Listing7_HouseRuleService listing9) {
        this.listing9 = listing9;
    }

    /**
     * @return the listing10
     */
    public Listing8_GuestRequirementsService getListing10() {
        return listing10;
    }

    /**
     * @param listing10 the listing10 to set
     */
    public void setListing10(Listing8_GuestRequirementsService listing10) {
        this.listing10 = listing10;
    }

    /**
     * @return the listing11
     */
    public Listing9_HowToBookService getListing11() {
        return listing11;
    }

    /**
     * @param listing11 the listing11 to set
     */
    public void setListing11(Listing9_HowToBookService listing11) {
        this.listing11 = listing11;
    }

    /**
     * @return the listing12
     */
    public Listing10_DateTimeSettingService getListing12() {
        return listing12;
    }

    /**
     * @param listing12 the listing12 to set
     */
    public void setListing12(Listing10_DateTimeSettingService listing12) {
        this.listing12 = listing12;
    }

    /**
     * @return the listing13
     */
    public Listing11_CalendarIntroService getListing13() {
        return listing13;
    }

    /**
     * @param listing13 the listing13 to set
     */
    public void setListing13(Listing11_CalendarIntroService listing13) {
        this.listing13 = listing13;
    }

    /**
     * @return the listing14
     */
    public Listing12_CalendarService getListing14() {
        return listing14;
    }

    /**
     * @param listing14 the listing14 to set
     */
    public void setListing14(Listing12_CalendarService listing14) {
        this.listing14 = listing14;
    }

    /**
     * @return the listing15
     */
    public Listing13_PriceSettingService getListing15() {
        return listing15;
    }

    /**
     * @param listing15 the listing15 to set
     */
    public void setListing15(Listing13_PriceSettingService listing15) {
        this.listing15 = listing15;
    }

    /**
     * @return the listing16
     */
    public Listing14_TaxService getListing16() {
        return listing16;
    }

    /**
     * @param listing16 the listing16 to set
     */
    public void setListing16(Listing14_TaxService listing16) {
        this.listing16 = listing16;
    }

    public void initCalendar() {
        this.initHomestayService();
        if (this.listing14 != null && this.listing14.getHomestay()!=null &&  !this.listing14.getHomestay().getIsNewHomeStay()) {
            this.listing14.initService();
        }
    }

    /**
     * @return the step
     */
    public int getStep() {
        return step;
    }

    /**
     * @param step the step to set
     */
    public void setStep(int step) {
        this.step = step;
    }
    
    
   

}
