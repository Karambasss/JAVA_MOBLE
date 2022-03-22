package com.example.sportapplicationproject.Entities;

public class Overall {
    public Long games_played;
    public Long won;
    public Long draw;
    public Long lost;
    public Long goals_scored;
    public Long goals_against;

    @Override
    public String toString() {
        return "games_played=" + games_played +
                ", won=" + won +
                ", draw=" + draw +
                ", lost=" + lost +
                ", goals_scored=" + goals_scored +
                ", goals_against=" + goals_against;
    }
}
