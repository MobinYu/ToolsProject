package com.yu.model;

public class MapCoordinate {
    
    private double longitude;
    private double latgitude;
    
    public MapCoordinate() {
        super();
    }

    public MapCoordinate(double lat, double lon) {
    	this.setLongitude(lon);
    	this.setLatgitude(lat);
    }
    
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatgitude() {
        return latgitude;
    }
    public void setLatgitude(double latgitude) {
        this.latgitude = latgitude;
    }
    
    @Override
    public String toString() {
        return longitude + ","+ latgitude;
    }
}
