package org.example;

import java.util.Objects;

public class Place {

    private String code;
    private String name;
    private String administrativeDivision;
    private String countryCode;
    private Coordinates coordinates;


    public Place() {
    }

    public Place(String code, String name, String administrativeDivision, String countryCode, String latitude,String longitude) {
        this.code = code;
        this.name = name;
        this.administrativeDivision = administrativeDivision;
        this.countryCode = countryCode;
        this.coordinates = new Coordinates(latitude,longitude);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdministrativeDivision() {
        return administrativeDivision;
    }

    public void setAdministrativeDivision(String administrativeDivision) {
        this.administrativeDivision = administrativeDivision;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Place place = (Place) obj;
        return Objects.equals(code, place.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Place{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", administrativeDivision='" + administrativeDivision + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
