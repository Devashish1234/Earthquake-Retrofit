package com.example.earthquakeretrofit;

public class DataModel {
    private Double mag;
    private String place;
    private Long  time;

    public Double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public Long getTime() {
        return time;
    }

    public DataModel(Double mag, String place, Long time) {
        this.mag = mag;
        this.place = place;
        this.time = time;
    }
}
