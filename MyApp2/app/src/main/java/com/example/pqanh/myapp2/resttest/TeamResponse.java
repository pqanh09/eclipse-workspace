package com.example.pqanh.myapp2.resttest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pqanh on 19-03-18.
 */

public class TeamResponse {
    @SerializedName("results")
    private List<Team> results;

    public List<Team> getResults() {
        return results;
    }

    public void setResults(List<Team> results) {
        this.results = results;
    }
}
