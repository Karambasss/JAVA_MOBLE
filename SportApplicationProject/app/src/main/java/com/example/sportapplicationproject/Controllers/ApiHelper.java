package com.example.sportapplicationproject.Controllers;

import com.example.sportapplicationproject.Entities.Country;
import com.example.sportapplicationproject.Entities.Match;
import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ApiHelper{
    private static final String url_API = "https://app.sportdataapi.com/api/v1/soccer/";
    private static final String key_APi = "b18d9120-a11e-11ec-b0a2-5bc94415f5cf";

    public static HttpURLConnection getConnection(URL url){
        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection)url.openConnection();
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("apikey", key_APi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return http;
    }
    public static String getInfo(HttpURLConnection http){
        StringBuilder response = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    http.getInputStream(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseLine;
        try {
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return response.toString();
    }



    public static ArrayList<Country> getAllCountries() throws IOException {
        ArrayList<Country> countries = new ArrayList<>();
        URL url = new URL(url_API + "countries");
        HttpURLConnection connection = getConnection(url);
        if (connection != null) {
            String response = getInfo(connection);
            connection.disconnect();
            System.out.println(response);
            JSONParser parser = new JSONParser();
            org.json.simple.JSONObject jsonObj = null;
            try {
                jsonObj = (org.json.simple.JSONObject) parser.parse(response);
                JSONArray data_array = (JSONArray) jsonObj.get("data");

                Gson gson = new Gson();
                for (Object c: data_array) {
                    countries.add(gson.fromJson(c.toString(), Country.class));
                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return countries;
        }
    public static ArrayList<Match> getRussianPremierLeagueGames() throws IOException {
        ArrayList<Match> matches = new ArrayList<>();
        URL url = new URL(url_API + "matches?season_id=1982");
        HttpURLConnection httpURLConnection = getConnection(url);
        if (httpURLConnection != null){
            String response = getInfo(httpURLConnection);
            httpURLConnection.disconnect();
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
                JSONArray jsonElements = (JSONArray) jsonObject.get("data");
                //System.out.println(jsonElements);
                //System.out.println(jsonElements.get(2));
                Gson gson = new Gson();
                for (Object d: jsonElements) {
                    Match match = gson.fromJson(d.toString(), Match.class);
                    //System.out.println(match);
                    matches.add(match);
                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return matches;
    }
    public static ArrayList<Match> getEnglishPremierLeagueGames() throws IOException {
        ArrayList<Match> matches = new ArrayList<>();
        URL url = new URL(url_API + "matches?season_id=1980");
        HttpURLConnection httpURLConnection = getConnection(url);
        if (httpURLConnection != null){
            String response = getInfo(httpURLConnection);
            httpURLConnection.disconnect();
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
                JSONArray jsonElements = (JSONArray) jsonObject.get("data");
                //System.out.println(jsonElements);
                //System.out.println(jsonElements.get(2));
                Gson gson = new Gson();
                for (Object d: jsonElements) {
                    Match match = gson.fromJson(d.toString(), Match.class);
                    //System.out.println(match);
                    matches.add(match);
                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return matches;
    }
    public static ArrayList<Match> getLiveGames() throws IOException {
        ArrayList<Match> matches = new ArrayList<>();
        URL url = new URL(url_API + "matches?live=true");
        HttpURLConnection httpURLConnection = getConnection(url);
        if (httpURLConnection != null){
            String response = getInfo(httpURLConnection);
            httpURLConnection.disconnect();
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
                JSONArray jsonElements = (JSONArray) jsonObject.get("data");
                //System.out.println(jsonElements);
                //System.out.println(jsonElements.get(2));
                Gson gson = new Gson();
                for (Object d: jsonElements) {
                    Match match = gson.fromJson(d.toString(), Match.class);
                    //System.out.println(match);
                    matches.add(match);
                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return matches;
    }
}
