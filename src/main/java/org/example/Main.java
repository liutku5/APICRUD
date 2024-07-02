package org.example;

import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Gson gson;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        gson = new Gson();
        Place p1 = new Place("abromiskes", "Abromiškės", "Elektrėnų savivaldybė", "LT", new Coordinates("54.7825,24.71032"));
        Place p2 = new Place("acokavai", "Acokavai", "Radviliškio rajono savivaldybė", "LT", new Coordinates("55.72656,23.34748"));
        Place p3 = new Place("adakavas", "Adakavas", "Tauragės rajono savivaldybė", "LT", new Coordinates("55.40348, 22.66207"));


//        addPlace(p2);
//        List<Place> users = getPlaces();
//         System.out.println(users);
//        Place place = getPlace("abromiskes");
//        System.out.println(place);
//        updatePlace(p2);
//         deletePlace(p2);

//        API();
////        System.out.println(new Place());
//        Scanner sc = new Scanner(System.in);
//        //  uzd1 istraukti miesta is API, sukonstruoti jo java objektą ir atspausdinti consolėje su .toString();
//        System.out.println("Pasirinkite miestą ir jo orus kuriuos norite matyti");
//        String city = sc.nextLine();
//        String url = "https://api.meteo.lt/v1/places/" + city ;
//        System.out.println(url);

        //  uzd2 pagal įvestą miestą surasti:
        //  patį miestą, (iš jo atvaizduosime "administrativeDivision")
        //  jo dabartinius orus, sukonstruoti forecastTimestamp objektų masyvą ir savo nuožiūra
        // gražiai ir aiškiai atvaizduoti.

//        url = "https://api.meteo.lt/v1/places/" + city + "/forcasts/long-term";

        Scanner sc = new Scanner(System.in);
        System.out.println("Pasirinkite miestą ir jo orus kuriuos norite matyti");
        String city = sc.nextLine();
        String url = "https://api.meteo.lt/v1/places/" + city;
//        System.out.println(url);

        try {
            String jsonResponse = getJsonResponse(url);
            Place place = parseJsonToPlace(jsonResponse);
            System.out.println(place);
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    private static String getJsonResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();

        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    private static Place parseJsonToPlace(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        String code = jsonObject.get("code").getAsString();
        String name = jsonObject.get("name").getAsString();
        String administrativeDivision = jsonObject.get("administrativeDivision").getAsString();
        String countryCode = jsonObject.get("countryCode").getAsString();
        JsonObject coordinatesObj = jsonObject.getAsJsonObject("coordinates");
        Double latitude = coordinatesObj.get("latitude").getAsDouble();
        Double longitude = coordinatesObj.get("longitude").getAsDouble();
        Coordinates coordinates = new Coordinates(latitude, longitude);

        return new Place(code, name, administrativeDivision, countryCode, coordinates);
    }

//    private static void API() {
//        try {
//            URL url = new URL("https://api.meteo.lt/v1/places");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
//            String response = "";
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response += line;
//            }
//            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
//            JsonArray usersArray = jsonResponse.getAsJsonArray("data");
//            Place[] places = gson.fromJson(usersArray, Place[].class);
//            System.out.println(places);
//
//            reader.close();
//        } catch (Exception e) {
//        }
//    }

    public static void deletePlace(Place place) {
        List<Place> places = getPlaces();
        places.stream()
                .filter(u -> u.equals(place))
                .findFirst()
                .map(u -> places.remove(place))
                .orElseThrow(() -> new IllegalArgumentException("Place with Code " + place.getCode() + " not found."));
        updateJson(places);
    }

    public static void updatePlace(Place place) {
        List<Place> places = getPlaces();
        places.stream()
                .filter(u -> u.equals(place))
                .findFirst()
                .map(u -> places.set(places.indexOf(u), place))
                .orElseThrow(() -> new IllegalArgumentException("Place with code " + place.getCode() + " not found."));
        updateJson(places);
    }

    public static void addPlace(Place place) {
        List<Place> places = getPlaces();
        places.add(place);
        updateJson(places);
    }

    public static void updateJson(List<Place> places) {
        try (FileWriter writer = new FileWriter("places.json")) {
            gson.toJson(places, writer);
        } catch (IOException e) {
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
                    JsonObject coordinatesObj = jsonObject.getAsJsonObject("coordinates");
                    Double latitude = coordinatesObj.get("latitude").getAsDouble();
                    Double longitude = coordinatesObj.get("longitude").getAsDouble();
                    Coordinates coordinates = new Coordinates(latitude, longitude);

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
                JsonObject coordinatesObj = jsonObject.getAsJsonObject("coordinates");
                Double latitude = coordinatesObj.get("latitude").getAsDouble();
                Double longitude = coordinatesObj.get("longitude").getAsDouble();
                Coordinates coordinates = new Coordinates(latitude, longitude);

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