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
                " home_team: " + home_team + "; " +
                "away_team: " + away_team + "; " +
                ", stats: " + stats +
                '\n';
    }
}
