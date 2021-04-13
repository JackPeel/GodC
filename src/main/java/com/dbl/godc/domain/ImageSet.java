package com.dbl.godc.domain;

import java.io.Serializable;

public class ImageSet implements Serializable {
    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    private Integer image_id;
    private String image_url;
}
