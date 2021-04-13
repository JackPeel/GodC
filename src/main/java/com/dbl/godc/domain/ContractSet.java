package com.dbl.godc.domain;

import java.io.Serializable;

public class ContractSet implements Serializable {
    private Integer id;
    private String contract_user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContract_user() {
        return contract_user;
    }

    public void setContract_user(String contract_user) {
        this.contract_user = contract_user;
    }

    public String getContract_zt() {
        return contract_zt;
    }

    public void setContract_zt(String contract_zt) {
        this.contract_zt = contract_zt;
    }

    public String getContract_purpose() {
        return contract_purpose;
    }

    public void setContract_purpose(String contract_purpose) {
        this.contract_purpose = contract_purpose;
    }

    public String getContract_time() {
        return contract_time;
    }

    public void setContract_time(String contract_time) {
        this.contract_time = contract_time;
    }

    private String contract_zt;
    private String contract_purpose;
    private String contract_time;
}
