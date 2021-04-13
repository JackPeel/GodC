package com.dbl.godc.domain;

import java.io.Serializable;

public class UserInstroSet implements Serializable {
    private Integer id;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    private String nick;
    private String uid;
    private String sex;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadbase64() {
        return headbase64;
    }

    public void setHeadbase64(String headbase64) {
        this.headbase64 = headbase64;
    }

    public String getInstro() {
        return instro;
    }

    public void setInstro(String instro) {
        this.instro = instro;
    }

    public String getItime() {
        return itime;
    }

    public void setItime(String itime) {
        this.itime = itime;
    }

    private String age;
    private String city;
    private String headbase64;
    private String instro;
    private String itime;
    private String focus;

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    private String fans;
}
