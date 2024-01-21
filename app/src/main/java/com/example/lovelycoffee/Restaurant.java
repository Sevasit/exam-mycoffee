package com.example.lovelycoffee;

public class Restaurant {
    public String id;
    public String name;
    public String location;
    public String openCloseTime;
    public String additionalDetails;

    public Restaurant() {
        // Default constructor required for calls to DataSnapshot.getValue(Restaurant.class)
    }

    public Restaurant(String id,String name, String location, String openCloseTime, String additionalDetails) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.openCloseTime = openCloseTime;
        this.additionalDetails = additionalDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpenCloseTime() {
        return openCloseTime;
    }

    public void setOpenCloseTime(String openCloseTime) {
        this.openCloseTime = openCloseTime;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }
}
