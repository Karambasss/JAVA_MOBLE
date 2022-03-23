package com.example.sportapplicationproject.Entities;

public class TopScores {
    public Long pos;
    public Player player;
    public Team1 team;
    public Long matches_played;
    public Goals goals;
    public Long penalties;

    @Override
    public String toString() {
        return "pos=" + pos +
                ", player=" + player +
                ", team=" + team +
                ", matches_played=" + matches_played +
                ", goals=" + goals +
                ", penalties=" + penalties +
                '\n';
    }
}
