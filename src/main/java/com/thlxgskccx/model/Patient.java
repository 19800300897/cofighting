package com.thlxgskccx.model;

public class Patient {
    public int id;
    public String society;
    public String latitude;
    public String longitude;
    public String confirmdate;
    public String address;

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getConfirmdate() {
        return confirmdate;
    }

    public void setConfirmdate(String confirmdate) {
        this.confirmdate = confirmdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
