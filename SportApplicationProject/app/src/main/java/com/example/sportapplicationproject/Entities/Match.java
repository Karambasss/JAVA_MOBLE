package com.example.sportapplicationproject.Entities;

public class Match {
    public Team away_team;
    public Team home_team;
    public Stats stats;
    public Venue venue;

    @Override
    public String toString() {
        return "Match " +
                venue +
                "away_team: " + away_team + "; " +
                " home_team: " + home_team +
                ", stats: " + stats +
                '\n';
    }
}
