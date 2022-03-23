package com.example.sportapplicationproject.Entities;

public class Players {
    public Long player_id;
    public String firstname;
    public String lastname;
    public Long age;
    public Country country;

    @Override
    public String toString() {
        return "player_id=" + player_id +
                ", firstname='" + firstname  +
                ", lastname='" + lastname +
                ", age=" + age +
                ", country=" + country +
                '\n';
    }
}
