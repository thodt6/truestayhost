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
public class CalendarItem {

    private String title;
    private String start;
    private String end;
    private Boolean allDay = true;
    private String rendering = "background";
    private Boolean block=true;
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
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * @return the allDay
     */
    public Boolean getAllDay() {
        return allDay;
    }

    /**
     * @param allDay the allDay to set
     */
    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    /**
     * @return the rendering
     */
    public String getRendering() {
        return rendering;
    }

    /**
     * @param rendering the rendering to set
     */
    public void setRendering(String rendering) {
        this.rendering = rendering;
    }

    /**
     * @return the block
     */
    public Boolean getBlock() {
        return block;
    }

    /**
     * @param block the block to set
     */
    public void setBlock(Boolean block) {
        this.block = block;
    }
}
