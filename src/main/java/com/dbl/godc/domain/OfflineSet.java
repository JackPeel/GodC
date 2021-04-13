package com.dbl.godc.domain;

import java.io.Serializable;

public class OfflineSet implements Serializable {
    private Integer offline_id;

    public Integer getOffline_id() {
        return offline_id;
    }

    public void setOffline_id(Integer offline_id) {
        this.offline_id = offline_id;
    }

    public String getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(String from_uid) {
        this.from_uid = from_uid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }

    public String getOffline_time() {
        return offline_time;
    }

    public void setOffline_time(String offline_time) {
        this.offline_time = offline_time;
    }

    public String getOffline_content() {
        return offline_content;
    }

    public void setOffline_content(String offline_content) {
        this.offline_content = offline_content;
    }

    public String getOffline_status() {
        return offline_status;
    }

    public void setOffline_status(String offline_status) {
        this.offline_status = offline_status;
    }

    private String from_uid;
    private String to_uid;
    private String offline_time;
    private String offline_content;
    private String offline_status;

}