package org.example;

import com.google.gson.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Gson gson;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        gson = new Gson();
    }

    public static Place getPlace(String code) {
        List<Place> places = new ArrayList<>();
        try (FileReader reader = new FileReader("places.json")) {
            // Parse the JSON file
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            // Iterate through the JSON array
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String placeCode = jsonObject.get("code").getAsString();
                if (code.equals(placeCode)) {
                    String name = jsonObject.get("name").getAsString();
                    String adminDivision = jsonObject.get("administrativeDivision").getAsString();
                    String countryCode = jsonObject.get("countryCode").getAsString();
                    String coordinates = jsonObject.get("coordinates").getAsString();

                    Place place = new Place();
                    place.setCode(placeCode);
                    place.setName(name);
                    place.setAdministrativeDivision(adminDivision);
                    place.setCountryCode(countryCode);
                    place.setCoordinates(coordinates);
                    places.add(place);
                    return place;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Place();
    }

    public static List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        try (FileReader reader = new FileReader("places.json")) {
            // Parse the JSON file
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            // Iterate through the JSON array
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();

                String placeCode = jsonObject.get("code").getAsString();
                String name = jsonObject.get("name").getAsString();
                String adminDivision = jsonObject.get("administrativeDivision").getAsString();
                String countryCode = jsonObject.get("countryCode").getAsString();
                String coordinates = jsonObject.get("coordinates").getAsString();

                Place place = new Place();
                place.setCode(placeCode);
                place.setName(name);
                place.setAdministrativeDivision(adminDivision);
                place.setCountryCode(countryCode);
                place.setCoordinates(coordinates);
                places.add(place);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return places;
    }
}