package org.example;

public class User {

    private String code;
    private String name;
    private String administrativeDivision;
    private String countryCode;
    private String coordinates;


    public User() {
    }

    public User(String code, String name, String administrativeDivision, String countryCode, String coordinates) {
        this.code = code;
        this.name = name;
        this.administrativeDivision = administrativeDivision;
        this.countryCode = countryCode;
        this.coordinates = coordinates;
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "User{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", administrativeDivision='" + administrativeDivision + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", cordinates='" + coordinates + '\'' +
                '}';
    }
}
