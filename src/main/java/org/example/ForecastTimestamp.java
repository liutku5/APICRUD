package org.example;

public class ForecastTimestamp {

    private String name;
    private String forecastTimeUtc;
    private String airTemperature;
    private String windSpeed;
    private String relativeHumidity;
    private String conditionCode;

    public ForecastTimestamp() {
    }

    public ForecastTimestamp(String name, String forecastTimeUtc, String airTemperature, String windSpeed, String relativeHumidity, String conditionCode) {
        this.name = name;
        this.forecastTimeUtc = forecastTimeUtc;
        this.airTemperature = airTemperature;
        this.windSpeed = windSpeed;
        this.relativeHumidity = relativeHumidity;
        this.conditionCode = conditionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForecastTimeUtc() {
        return forecastTimeUtc;
    }

    public void setForecastTimeUtc(String forecastTimeUtc) {
        this.forecastTimeUtc = forecastTimeUtc;
    }

    public String getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(String airTemperature) {
        this.airTemperature = airTemperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    @Override
    public String toString() {
        return "ForecastTimestamp{" +
                "name: '" + name + '\'' +
                ", forecastTimeUtc: '" + forecastTimeUtc + '\'' +
                ", airTemperature: '" + airTemperature + '\'' +
                ", windSpeed: '" + windSpeed + '\'' +
                ", relativeHumidity: '" + relativeHumidity + '\'' +
                ", conditionCode: '" + conditionCode + '\'' +
                '}';
    }
}
