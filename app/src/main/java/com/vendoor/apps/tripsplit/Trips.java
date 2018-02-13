package com.vendoor.apps.tripsplit;

/**
 * Created by dell on 2/12/2018.
 */

public class Trips {
    String tripName,destination,toDate,endDate;

    public Trips(String tripName, String destination, String toDate, String endDate) {
        this.tripName = tripName;
        this.destination = destination;
        this.toDate = toDate;
        this.endDate = endDate;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
