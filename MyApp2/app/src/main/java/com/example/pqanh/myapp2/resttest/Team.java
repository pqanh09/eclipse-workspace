package com.example.pqanh.myapp2.resttest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pqanh on 19-03-18.
 */

public class Team {
    @SerializedName("ID")
    private long ID;
    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logo;
    @SerializedName("stadium")
    private String stadium;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    @Override
    public String toString() {
        return "Team{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", stadium='" + stadium + '\'' +
                '}';
    }
}
