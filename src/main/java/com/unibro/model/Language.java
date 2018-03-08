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
public class Language {

    private String language_code;
    private String country_name;
    private Boolean selected;

    /**
     * @return the language_code
     */
    public String getLanguage_code() {
        return language_code;
    }

    /**
     * @param language_code the language_code to set
     */
    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    /**
     * @return the country_name
     */
    public String getCountry_name() {
        return country_name;
    }

    /**
     * @param country_name the country_name to set
     */
    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    /**
     * @return the selected
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
