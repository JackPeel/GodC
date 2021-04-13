package com.dbl.godc.domain;

import java.io.Serializable;

public class ThingSet implements Serializable {

    private Integer thing_id;
    private String thing_title;
    private String thing_content;

    public Integer getThing_id() {
        return thing_id;
    }

    public void setThing_id(Integer thing_id) {
        this.thing_id = thing_id;
    }

    public String getThing_title() {
        return thing_title;
    }

    public void setThing_title(String thing_title) {
        this.thing_title = thing_title;
    }

    public String getThing_content() {
        return thing_content;
    }

    public void setThing_content(String thing_content) {
        this.thing_content = thing_content;
    }

    public String getThing_url() {
        return thing_url;
    }

    public void setThing_url(String thing_url) {
        this.thing_url = thing_url;
    }

    public String getThing_fromurl() {
        return thing_fromurl;
    }

    public void setThing_fromurl(String thing_fromurl) {
        this.thing_fromurl = thing_fromurl;
    }

    public String getThing_creattime() {
        return thing_creattime;
    }

    public void setThing_creattime(String thing_creattime) {
        this.thing_creattime = thing_creattime;
    }

    public String getThing_zt() {
        return thing_zt;
    }

    public void setThing_zt(String thing_zt) {
        this.thing_zt = thing_zt;
    }

    private String thing_url;
    private String thing_fromurl;
    private String thing_creattime;
    private String thing_zt;

    public String getThing_look() {
        return thing_look;
    }

    public void setThing_look(String thing_look) {
        this.thing_look = thing_look;
    }

    private String thing_look;
}
