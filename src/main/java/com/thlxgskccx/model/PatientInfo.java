package com.thlxgskccx.model;

import java.util.Date;

/**
 * @Classname PatientInfo
 * @Description TODO
 * @Data 2020/7/5   11:01
 * @Created by Amy
 */
public class PatientInfo {
    public String society;
    public String latitude;
    public String longitude;
    public Date confirmdate;
    public String address;

    @Override
    public String toString() {
        return "PatientInfo{" +
                "society='" + society + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", confirmdate='" + confirmdate + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longtitude) {
        this.longitude = longtitude;
    }

    public Date getConfirmdate() {
        return confirmdate;
    }

    public void setConfirmdate(Date confirmdate) {
        this.confirmdate = confirmdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
