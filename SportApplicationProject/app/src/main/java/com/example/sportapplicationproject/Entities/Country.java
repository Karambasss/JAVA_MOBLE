package com.example.sportapplicationproject.Entities;

public class Country {
    public String continent;
    public String name;
    public Long country_id;

    public String toString(){
        return continent + "," + name + "," + country_id + "\n";
    }
}
