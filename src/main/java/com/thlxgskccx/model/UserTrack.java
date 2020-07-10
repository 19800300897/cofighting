package com.thlxgskccx.model;

import java.util.Date;

/**
 * @Classname UserTrack
 * @Description TODO
 * @Data 2020/6/29   22:40
 * @Created by Amy
 */
public class UserTrack {
    public String userphone;
    public String name;
    public String position;
    public String enaw;
    public String information;
    public Date enawtime;

    public Date getEnawtime() {
        return enawtime;
    }

    public void setEnawtime(Date enawtime) {
        this.enawtime = enawtime;
    }
    @Override
    public String toString() {
        return "UserTrack{" +
                "userphone='" + userphone + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", enaw='" + enaw + '\'' +
                ", information='" + information + '\'' +
                '}';
    }

    public UserTrack(String userphone, String name, String position, String enaw, String information) {
        this.userphone = userphone;
        this.name = name;
        this.position = position;
        this.enaw = enaw;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEnaw() {
        return enaw;
    }

    public void setEnaw(String enaw) {
        this.enaw = enaw;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
