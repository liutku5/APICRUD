package org.example;

import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Gson gson;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        gson = new Gson();
        Place p1 = new Place("abromiskes","Abromiškės","Elektrėnų savivaldybė","LT",54.7825);
        Place p2 = new Place("acokavai","Acokavai","Radviliškio rajono savivaldybė","LT",55.72656);
        addPlace(p1);
    }


    public static void deletePlace(Place place){
        List<Place> places = getPlaces();
        places.stream()
                .filter(u -> u.equals(place))
                .findFirst()
                .map(u -> places.remove(place))
                .orElseThrow(() -> new IllegalArgumentException("Place with Code " + place.getCode() + " not found."));
        updateJson(places);
    }

    public static void updatePlace(Place place){
        List<Place> places = getPlaces();
        places.stream()
                .filter(u -> u.equals(place))
                .findFirst()
                .map(u -> places.set(places.indexOf(u), place))
                .orElseThrow(() -> new IllegalArgumentException("Place with code " + place.getCode() + " not found."));
        updateJson(places);
    }

    public static void addPlace(Place place){
        List<Place> places = getPlaces();
        places.add(place);
        updateJson(places);
    }

    public static void updateJson(List<Place> places){
        try(FileWriter writer = new FileReader("places.json",true)){
            gson.toJson(places,writer);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addPlaceOld(Place place){
        try(FileWriter writer = new FileReader("place.json",true)){
            gson.toJson(place,writer);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Place getPlace(String code) {
        List<Place> places = new ArrayList<>();
        try (FileReader reader = new FileReader("places.json")) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String placeCode = jsonObject.get("code").getAsString();
                if (code.equals(placeCode)) {
                    String name = jsonObject.get("name").getAsString();
                    String adminDivision = jsonObject.get("administrativeDivision").getAsString();
                    String countryCode = jsonObject.get("countryCode").getAsString();
                    double coordinates = jsonObject.get("coordinates").getAsDouble();

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
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String placeCode = jsonObject.get("code").getAsString();
                String name = jsonObject.get("name").getAsString();
                String adminDivision = jsonObject.get("administrativeDivision").getAsString();
                String countryCode = jsonObject.get("countryCode").getAsString();
                double coordinates = jsonObject.get("coordinates").getAsDouble();

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