package com.dbl.godc.domain;

import java.io.Serializable;

public class CommentSet implements Serializable {

    private Integer comments_id;

    public Integer getComments_id() {
        return comments_id;
    }

    public void setComments_id(Integer comments_id) {
        this.comments_id = comments_id;
    }

    public Integer getThing_id() {
        return thing_id;
    }

    public void setThing_id(Integer thing_id) {
        this.thing_id = thing_id;
    }

    public String getComments_content() {
        return comments_content;
    }

    public void setComments_content(String comments_content) {
        this.comments_content = comments_content;
    }

    public String getComments_agree() {
        return comments_agree;
    }

    public void setComments_agree(String comments_agree) {
        this.comments_agree = comments_agree;
    }

    public String getComments_location() {
        return comments_location;
    }

    public void setComments_location(String comments_location) {
        this.comments_location = comments_location;
    }

    public String getComments_creattime() {
        return comments_creattime;
    }

    public void setComments_creattime(String comments_creattime) {
        this.comments_creattime = comments_creattime;
    }

    public String getComments_zt() {
        return comments_zt;
    }

    public void setComments_zt(String comments_zt) {
        this.comments_zt = comments_zt;
    }

    public String getComments_user() {
        return comments_user;
    }

    public void setComments_user(String comments_user) {
        this.comments_user = comments_user;
    }

    private Integer thing_id;
    private String comments_content;
    private String comments_agree;
    private String comments_location;
    private String comments_creattime;
    private String comments_zt;
    private String comments_user;

    @Override
    public String toString() {
        return "CommentSet{" +
                "comments_id=" + comments_id +
                ", thing_id=" + thing_id +
                ", comments_content='" + comments_content + '\'' +
                ", comments_agree='" + comments_agree + '\'' +
                ", comments_location='" + comments_location + '\'' +
                ", comments_creattime='" + comments_creattime + '\'' +
                ", comments_zt='" + comments_zt + '\'' +
                ", comments_user='" + comments_user + '\'' +
                '}';
    }
}
