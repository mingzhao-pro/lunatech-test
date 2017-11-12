package com.example.lunatech.model;

import java.util.List;

public class AirportOwner {
    int count;
    String maxOwner;
    List<String> minOwner;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMaxOwner() {
        return maxOwner;
    }

    public void setMaxOwner(String maxOwner) {
        this.maxOwner = maxOwner;
    }

    public List<String> getMinOwner() {
        return minOwner;
    }

    public void setMinOwner(List<String> minOwner) {
        this.minOwner = minOwner;
    }
}
