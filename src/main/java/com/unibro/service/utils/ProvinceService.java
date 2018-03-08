/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibro.service.utils;

import com.unibro.api.Utils;
import com.unibro.model.District;
import com.unibro.model.Homestay;
import com.unibro.model.Province;
import com.unibro.model.Ward;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author THOND
 */
public class ProvinceService {

    private Homestay homestay;
    private List<Province> provinces;
    private String provinceid = "";
    private List<District> districts;
    private String districid = "";
    private List<Ward> wards;
    private String wardid = "";
    private String textSearch = "";

    public void loadProvinces() {
        this.provinces = Utils.getProvinces("0");
    }

    public void loadDistricts() {
        if (this.provinceid == null || this.provinceid.equals("")) {
            this.districts = new ArrayList();
            this.districid = "";
        } else {
            this.homestay.setProvince_id(provinceid);
            this.districts = Utils.getDistricts(this.provinceid);
        }
    }

    public void loadWards() {
        if (this.districid == null || this.districid.equals("")) {
            this.wards = new ArrayList();
            this.wardid = "";
        } else {
            this.homestay.setDistrict_id(this.getDistricid());
            this.wards = Utils.getWards(this.districid);
        }
    }

    /**
     * @return the provinces
     */
    public List<Province> getProvinces() {
        return provinces;
    }

    /**
     * @param provinces the provinces to set
     */
    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    /**
     * @return the provinceid
     */
    public String getProvinceid() {
        return provinceid;
    }

    /**
     * @param provinceid the provinceid to set
     */
    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
        for (Province p : provinces) {
            if (p.getId().equals(this.provinceid)) {
                this.textSearch = p.getName() + " ";
                break;
            }
        }
    }

    /**
     * @return the districts
     */
    public List<District> getDistricts() {
        return districts;
    }

    /**
     * @param districts the districts to set
     */
    public void setDistricts(List<District> districts) {
        this.districts = districts;
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
        if (this.districts != null && !this.districts.isEmpty()) {
            for (District p : districts) {
                if (p.getId().equals(this.districid)) {
                    this.textSearch += p.getName() + " ";
                    break;
                }
            }
        }
    }

    /**
     * @return the wards
     */
    public List<Ward> getWards() {
        return wards;
    }

    /**
     * @param wards the wards to set
     */
    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    /**
     * @return the wardid
     */
    public String getWardid() {
        return wardid;
    }

    /**
     * @param wardid the wardid to set
     */
    public void setWardid(String wardid) {
        this.wardid = wardid;
        this.homestay.setWard_id(this.getWardid());
        if (this.wards != null && !this.wards.isEmpty()) {
            for (Ward p : wards) {
                if (p.getId().equals(this.wardid)) {
                    this.textSearch += p.getName();
                    break;
                }
            }
        }
    }

    private MapModel geoModel = new DefaultMapModel();

    private Marker marker;

    private String centerGeoMap = "21.028511,105.804817";

    /**
     * @return the geoModel
     */
    public MapModel getGeoModel() {
        return geoModel;
    }

    /**
     * @param geoModel the geoModel to set
     */
    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }

    /**
     * @return the marker
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * @param marker the marker to set
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * @return the centerGeoMap
     */
    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    /**
     * @param centerGeoMap the centerGeoMap to set
     */
    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            setCenterGeoMap(center.getLat() + "," + center.getLng());

            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        this.homestay.setAddress_full(marker.getTitle());
        this.homestay.setLongitude(String.valueOf(marker.getLatlng().getLng()));
        this.homestay.setLatitude(String.valueOf(marker.getLatlng().getLat()));
    }

    /**
     * @return the textSearch
     */
    public String getTextSearch() {
        return textSearch;
    }

    /**
     * @param textSearch the textSearch to set
     */
    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
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
    private double lat;
    private double lng;
    private String title;
    
    public void addMarker(){
        Marker mk = new Marker(new LatLng(getLat(), getLng()), getTitle());        
        geoModel.addOverlay(mk);
        this.homestay.setLatitude(String.valueOf(lat));
        this.homestay.setLongitude(String.valueOf(lng));
        this.homestay.setAddress_full(title);
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(double lng) {
        this.lng = lng;
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
}
