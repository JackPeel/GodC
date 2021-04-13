package com.dbl.godc.domain;

import java.io.Serializable;

public class ReplySet implements Serializable {
    private Integer reply_id;
    private Integer comments_id;
    private String reply_goal;
    private String reply_user;

    public Integer getReply_id() {
        return reply_id;
    }

    @Override
    public String toString() {
        return "ReplySet{" +
                "reply_id=" + reply_id +
                ", comments_id=" + comments_id +
                ", reply_goal='" + reply_goal + '\'' +
                ", reply_user='" + reply_user + '\'' +
                ", reply_content='" + reply_content + '\'' +
                ", reply_agree='" + reply_agree + '\'' +
                ", reply_location='" + reply_location + '\'' +
                ", reply_creattime='" + reply_creattime + '\'' +
                ", reply_zt='" + reply_zt + '\'' +
                '}';
    }

    public void setReply_id(Integer reply_id) {
        this.reply_id = reply_id;
    }

    public Integer getComments_id() {
        return comments_id;
    }

    public void setComments_id(Integer comments_id) {
        this.comments_id = comments_id;
    }

    public String getReply_goal() {
        return reply_goal;
    }

    public void setReply_goal(String reply_goal) {
        this.reply_goal = reply_goal;
    }

    public String getReply_user() {
        return reply_user;
    }

    public void setReply_user(String reply_user) {
        this.reply_user = reply_user;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_agree() {
        return reply_agree;
    }

    public void setReply_agree(String reply_agree) {
        this.reply_agree = reply_agree;
    }

    public String getReply_location() {
        return reply_location;
    }

    public void setReply_location(String reply_location) {
        this.reply_location = reply_location;
    }

    public String getReply_creattime() {
        return reply_creattime;
    }

    public void setReply_creattime(String reply_creattime) {
        this.reply_creattime = reply_creattime;
    }

    public String getReply_zt() {
        return reply_zt;
    }

    public void setReply_zt(String reply_zt) {
        this.reply_zt = reply_zt;
    }

    private String reply_content;
    private String reply_agree;
    private String reply_location;
    private String reply_creattime;
    private String reply_zt;

}
