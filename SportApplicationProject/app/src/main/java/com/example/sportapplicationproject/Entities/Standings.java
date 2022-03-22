package com.example.sportapplicationproject.Entities;

public class Standings {
    public Long team_id;
    public String points;
    public String status;
    public String result;
    public Overall overall;
    public String team_name=null;

    @Override
    public String toString() {
        return "Standings = " +
                "team_id=" + team_id +
                ", team_name= " + team_name +
                ", points= " + points +
                ", status= " + status +
                ", result= " + result +
                ", overall= " + overall + '\n';
    }
    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}
