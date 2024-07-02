package org.example;

public class Coordinates {
    private Double latitude;
    private Double longitude;

    public Coordinates() {
    }

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates(String latLong) {
        String[] parts = latLong.split(",");
        this.latitude = Double.parseDouble(parts[0]);
        this.longitude = Double.parseDouble(parts[1]);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'';
    }
}
