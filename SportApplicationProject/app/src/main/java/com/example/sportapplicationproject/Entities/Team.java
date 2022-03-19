package com.example.sportapplicationproject.Entities;

public class Team {
    public Country country;
    public String name;

    public String toString(){
        if (country != null){
            return "country=" + country.name + "; Team name=" + name;
        }
        else {
            return "country=" + "null" + "; Team name=" + name;
        }
    }
}
